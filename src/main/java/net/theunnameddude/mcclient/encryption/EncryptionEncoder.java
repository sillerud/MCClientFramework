package net.theunnameddude.mcclient.encryption;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import javax.crypto.Cipher;
import java.util.List;

public class EncryptionEncoder extends MessageToByteEncoder<ByteBuf> {
    Cipher cipher;

    private ThreadLocal<byte[]> heapInLocal = new ThreadLocal<byte[]>(){
        @Override
        protected byte[] initialValue() {
            return new byte[0];
        }
    };
    private ThreadLocal<byte[]> heapOutLocal = new ThreadLocal<byte[]>(){
        @Override
        protected byte[] initialValue() {
            return new byte[0];
        }
    };

    public EncryptionEncoder(Cipher cipher) {
        this.cipher = cipher;
    }

    private byte[] bufToByte(ByteBuf in) {
        byte[] heapIn = heapInLocal.get();
        int readableBytes = in.readableBytes();
        if ( heapIn.length < readableBytes ) {
            heapIn = new byte[ readableBytes ];
            heapInLocal.set( heapIn );
        }
        in.readBytes( heapIn, 0, readableBytes );
        return heapIn;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf in, ByteBuf out) throws Exception {
        int readableBytes = in.readableBytes();
        byte[] heapIn = bufToByte( in );

        byte[] heapOut = heapOutLocal.get();
        int outputSize = cipher.getOutputSize( readableBytes );
        if ( heapOut.length < outputSize ) {
            heapOut = new byte[ outputSize ];
            heapOutLocal.set( heapOut );
        }
        out.writeBytes( heapOut, 0, cipher.update( heapIn, 0, readableBytes, heapOut ) );
    }

    //@Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int readableBytes = in.readableBytes();
        byte[] heapIn = bufToByte( in );

        ByteBuf heapOut = ctx.alloc().heapBuffer(cipher.getOutputSize(readableBytes));
        heapOut.writerIndex( cipher.update( heapIn, 0, readableBytes, heapOut.array(), heapOut.arrayOffset() ) );
        out.add( heapOut );
    }
}
