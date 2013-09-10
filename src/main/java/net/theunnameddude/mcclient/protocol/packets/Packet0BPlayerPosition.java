package net.theunnameddude.mcclient.protocol.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.theunnameddude.mcclient.protocol.PacketHandler;

public class Packet0BPlayerPosition extends BasePacket {
    double x;
    double y;
    double stance;
    double z;
    boolean onGround;

    public Packet0BPlayerPosition() {
        super( 0x0B );
    }

    public Packet0BPlayerPosition( double x, double y, double z, double stance, boolean onGround) {
        this();
        this.x = x;
        this.y = y;
        this.z = z;
        this.stance = stance;
        this.onGround = onGround;
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

    @Override
    public void handle(PacketHandler handler, ChannelHandlerContext ctx) {
        handler.handle( this );
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getStance() {
        return stance;
    }

    public double getZ() {
        return z;
    }

    public boolean isOnGround() {
        return onGround;
    }
}
