package net.theunnameddude.mcclient.protocol.ver1_7_2.packets;

import io.netty.buffer.ByteBuf;
import net.theunnameddude.mcclient.protocol.base.PacketKickBase;

public class PacketKick extends PacketKickBase {
    public PacketKick(String reason) {
        super( reason, 0x40 );
    }

    public PacketKick() {
        super( 0x40 );
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {
        setString( buf, reason, true );
        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        reason = readString( buf, true );
    }
}
