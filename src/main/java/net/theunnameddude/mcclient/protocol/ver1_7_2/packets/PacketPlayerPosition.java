package net.theunnameddude.mcclient.protocol.ver1_7_2.packets;

import io.netty.buffer.ByteBuf;
import net.theunnameddude.mcclient.protocol.base.PacketPlayerPositionBase;

public class PacketPlayerPosition extends PacketPlayerPositionBase {
    public PacketPlayerPosition() {
        super( 0x05 );
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {
        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        x = buf.readDouble();
        y = buf.readDouble();
        z = buf.readDouble();
        stance = buf.readDouble();
        onGround = buf.readBoolean();
    }
}
