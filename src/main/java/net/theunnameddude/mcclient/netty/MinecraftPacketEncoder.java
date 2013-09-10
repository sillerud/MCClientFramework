package net.theunnameddude.mcclient.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.theunnameddude.mcclient.protocol.packets.BasePacket;

public class MinecraftPacketEncoder extends MessageToByteEncoder<BasePacket> {
    @Override
    protected void encode(ChannelHandlerContext ctx, BasePacket packet, ByteBuf buf) throws Exception {
        buf.writeByte( packet.getPacketId() );
        packet.getPacket( buf );
    }
}
