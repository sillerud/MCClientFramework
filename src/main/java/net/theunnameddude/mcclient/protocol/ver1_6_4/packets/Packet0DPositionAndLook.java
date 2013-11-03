package net.theunnameddude.mcclient.protocol.ver1_6_4.packets;

import io.netty.buffer.ByteBuf;
import net.theunnameddude.mcclient.protocol.base.PacketPositionAndLookBase;

public class Packet0DPositionAndLook extends PacketPositionAndLookBase {

    public Packet0DPositionAndLook() {
        super( 0x0D );
    }

    public Packet0DPositionAndLook(double x, double y, double z, double stance, float yaw, float pitch, boolean onGround) {
        super( x, y, z, stance, yaw, pitch, onGround, 0x0D );
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {
        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        x = buf.readDouble();
        stance = buf.readDouble();
        y = buf.readDouble();
        z = buf.readDouble();
        yaw = buf.readFloat();
        pitch = buf.readFloat();
        onGround = buf.readBoolean();
    }
}
