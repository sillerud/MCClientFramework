package net.theunnameddude.mcclient.protocol.ver1_7_2.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.theunnameddude.mcclient.protocol.base.BasePacket;

public class MinecraftPacketEncoder extends MessageToByteEncoder<BasePacket> {
    @Override
    protected void encode(ChannelHandlerContext ctx, BasePacket packet, ByteBuf out) throws Exception {
        // Convert the java's to the bits and bytes
        ByteBuf packetContent = packet.getPacket( Unpooled.buffer() );

        int packetSize = packetContent.readableBytes() + varintSize( packet.getPacketId() ); // Size of the packet
        int varintSize = varintSize( packetSize ); // Size of the packet size

        // Start writing the packet to the bytebuf
        out.ensureWritable( varintSize + packetSize );
        BasePacket.setVarInt( out, packetSize ); // Write the packet size
        BasePacket.setVarInt( out, packet.getPacketId() ); // Write the packet ID
        out.writeBytes( packetContent ); // Write the packet content
    }


    private static int varintSize(int paramInt) {
        if ( ( paramInt & 0xFFFFFF80 ) == 0 ) {
            return 1;
        } else if ( ( paramInt & 0xFFFFC000 ) == 0 ) {
            return 2;
        } else if ( ( paramInt & 0xFFE00000 ) == 0 ) {
            return 3;
        } else if ( ( paramInt & 0xF0000000 ) == 0 ) {
            return 4;
        }
        return 5;
    }
}
