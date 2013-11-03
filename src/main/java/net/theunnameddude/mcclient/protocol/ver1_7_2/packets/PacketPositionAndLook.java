package net.theunnameddude.mcclient.protocol.ver1_7_2.packets;

import io.netty.buffer.ByteBuf;
import net.theunnameddude.mcclient.protocol.base.PacketPositionAndLookBase;

public class PacketPositionAndLook extends PacketPositionAndLookBase {

    public PacketPositionAndLook(double x, double y, double z, double stance, float yaw, float pitch, boolean onGround) {
        super( x, y, z, stance, yaw, pitch, onGround, 0x08 );
    }

    public PacketPositionAndLook() {
        super( 0x08 );
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {
        buf.writeDouble( x );
        buf.writeDouble( y );
        buf.writeDouble( z );
        buf.writeFloat( yaw );
        buf.writeFloat( pitch );
        buf.writeBoolean( onGround );
        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        x = buf.readDouble();
        y = buf.readDouble();
        z = buf.readDouble();
        yaw = buf.readFloat();
        pitch = buf.readFloat();
        onGround = buf.readBoolean();
    }
}
