package net.theunnameddude.mcclient.protocol.ver1_6_4.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import lombok.AllArgsConstructor;
import net.theunnameddude.mcclient.protocol.PacketConstructor;
import net.theunnameddude.mcclient.protocol.base.BasePacket;

import java.util.List;

@AllArgsConstructor
public class MinecraftPacketDecoder extends ReplayingDecoder<Void> {
    PacketConstructor version;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> objects) throws Exception {
        while ( true ) {
            int startIndex = buf.readerIndex();
            short packetId = buf.readUnsignedByte();
            // System.out.println( "Got packet 0x" + Integer.toHexString( ((byte)packetId) & 0xFF ).toUpperCase() );
            BasePacket packet = version.readPacket( packetId, buf );
            checkpoint();
            if ( packet != null ) {
                objects.add( packet );
            }
        }
    }
}
