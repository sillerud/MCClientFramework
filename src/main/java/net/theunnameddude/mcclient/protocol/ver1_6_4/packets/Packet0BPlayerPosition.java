package net.theunnameddude.mcclient.protocol.ver1_6_4.packets;

import io.netty.buffer.ByteBuf;
import net.theunnameddude.mcclient.protocol.base.PacketPlayerPositionBase;

public class Packet0BPlayerPosition extends PacketPlayerPositionBase {

    public Packet0BPlayerPosition() {
        super( 0x0B );
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {
        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        x = buf.readDouble();
        y = buf.readDouble();
        stance = buf.readDouble();
        z = buf.readDouble();
        onGround = buf.readBoolean();
    }
}
