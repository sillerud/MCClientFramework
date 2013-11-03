package net.theunnameddude.mcclient.protocol.ver1_6_4.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.theunnameddude.mcclient.protocol.base.BasePacket;

public class MinecraftPacketEncoder extends MessageToByteEncoder<BasePacket> {
    @Override
    protected void encode(ChannelHandlerContext ctx, BasePacket packet, ByteBuf buf) throws Exception {
        buf.writeByte( packet.getPacketId() );
        packet.getPacket( buf );
    }
}
