package net.theunnameddude.mcclient.protocol.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.theunnameddude.mcclient.protocol.PacketHandler;

public class Packet0DPositionAndLook extends BasePacket {
    double x;
    double stance;
    double y;
    double z;
    float yaw;
    float pitch;
    boolean onGround;

    public boolean isOnGround() {
        return onGround;
    }

    public Packet0DPositionAndLook() {
        super( 0x0D, "position-and-look" );
    }

    public Packet0DPositionAndLook( double x, double y, double z, double stance, float yaw, float pitch, boolean onGround) {
        this();
        this.x = x;
        this.y = y;
        this.z = z;
        this.stance = stance;
        this.yaw = yaw;
        this.onGround = onGround;
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

    @Override
    public void handle(PacketHandler handler, ChannelHandlerContext ctx) {
        handler.handle( this );
    }

    public double getX() {
        return x;
    }

    public double getStance() {
        return stance;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }
}
