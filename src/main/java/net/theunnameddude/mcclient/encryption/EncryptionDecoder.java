package net.theunnameddude.mcclient.encryption;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import javax.crypto.Cipher;
import java.util.List;

public class EncryptionDecoder extends MessageToMessageDecoder<ByteBuf> {
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

    public EncryptionDecoder(Cipher cipher) {
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
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int readableBytes = in.readableBytes();
        byte[] heapIn = bufToByte( in );

        ByteBuf heapOut = ctx.alloc().heapBuffer(cipher.getOutputSize(readableBytes));
        heapOut.writerIndex( cipher.update( heapIn, 0, readableBytes, heapOut.array(), heapOut.arrayOffset() ) );
        out.add( heapOut );
    }
}
