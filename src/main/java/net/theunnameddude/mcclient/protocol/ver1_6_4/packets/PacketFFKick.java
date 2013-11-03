package net.theunnameddude.mcclient.protocol.ver1_6_4.packets;

import io.netty.buffer.ByteBuf;
import net.theunnameddude.mcclient.protocol.base.PacketKickBase;

public class PacketFFKick extends PacketKickBase {

    public PacketFFKick() {
        super( 0xFF );
    }

    public PacketFFKick(String reason) {
        super( reason, 0xFF );
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {
        setString( buf, reason, false );
        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        this.reason = readString( buf, false );
    }
}
