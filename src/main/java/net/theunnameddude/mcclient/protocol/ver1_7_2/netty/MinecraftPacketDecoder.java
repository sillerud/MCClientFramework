package net.theunnameddude.mcclient.protocol.ver1_7_2.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.theunnameddude.mcclient.protocol.base.BasePacket;
import net.theunnameddude.mcclient.protocol.ver1_7_2.PacketConstructor1_7_2;

import java.util.List;

@RequiredArgsConstructor
public class MinecraftPacketDecoder extends ByteToMessageDecoder {
    @NonNull
    PacketConstructor1_7_2 pc;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        in.markReaderIndex();
        byte[] sizeContent = new byte[ 3 ];
        for ( int i = 0; i < sizeContent.length; i++ ) {
            if ( !in.isReadable() ) {
                in.resetReaderIndex();
                return;
            }
            sizeContent[ i ] = in.readByte();
            if ( sizeContent[ i ] >= 0 ) {
                int size = BasePacket.readVarInt( Unpooled.wrappedBuffer( sizeContent ) ); // Read the packet size
                if ( in.readableBytes() < size ) { // If there is not enough readable bytes ignore them
                    in.resetReaderIndex();
                } else {
                    ByteBuf buf = in.readBytes( size );
                    int packetId = BasePacket.readVarInt( buf );
                    BasePacket packet = pc.readPacket( (short)packetId, buf );
                    if ( packet == null ) {
                        buf.skipBytes( buf.readableBytes() );
                    } else {
                        out.add( packet );
                    }
                }
                return;
            }
        }
    }
}
