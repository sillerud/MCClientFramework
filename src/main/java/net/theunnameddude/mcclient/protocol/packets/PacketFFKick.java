package net.theunnameddude.mcclient.protocol.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.theunnameddude.mcclient.protocol.PacketHandler;

public class PacketFFKick extends BasePacket {
    String reason;

    public PacketFFKick() {
        super( 0xFF );
    }

    public PacketFFKick(String reason) {
        this();
        this.reason = reason;
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {
        setString( buf, reason );
        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        this.reason = readString( buf );
    }

    public String getReason() {
        return reason;
    }

    @Override
    public void handle(PacketHandler handler, ChannelHandlerContext ctx) {
        handler.handle( this );
    }
}
