package net.theunnameddude.protocol;

import io.netty.buffer.ByteBuf;

public class PacketFFKick extends BasePacket {
    String reason;

    public PacketFFKick() {
        super( 0xFF );
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
}
