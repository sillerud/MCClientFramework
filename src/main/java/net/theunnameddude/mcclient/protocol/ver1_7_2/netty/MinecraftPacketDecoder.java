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
        int size = 0;
        for ( int i = 0; i < 3; i++ ) {
            if ( !in.isReadable() ) {
                in.resetReaderIndex();
                return;
            }
            byte b = in.readByte();
            size |= ( b & 0x7F ) << ( i * 7 );
            if ( ( b & 0x80 ) != 0x80 ) {
                if ( in.readableBytes() < size ) {
                    in.resetReaderIndex();
                } else {
                    int packetId = BasePacket.readVarInt( in );
                    int packetIdSize = MinecraftPacketEncoder.varintSize( packetId );
                    BasePacket packet = pc.readPacket( (short)packetId, null );
                    if ( packet != null ) {
                        packet.onPacket( in.readBytes( size - packetIdSize ) );
                        out.add( packet );
                    } else {
                        in.skipBytes( size - packetIdSize );
                    }
                }
                return;
            }
        }
    }
}
