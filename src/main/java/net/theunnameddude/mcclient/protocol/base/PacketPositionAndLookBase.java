package net.theunnameddude.mcclient.protocol.base;

import lombok.Getter;
import net.theunnameddude.mcclient.protocol.PacketHandler;

public abstract class PacketPositionAndLookBase extends BasePacket {

    @Getter
    public double x;
    @Getter
    public double stance;
    @Getter
    public double y;
    @Getter
    public double z;
    @Getter
    public float yaw;
    @Getter
    public float pitch;
    @Getter
    public boolean onGround;

    public PacketPositionAndLookBase(int packetId) {
        super( "PositionAndLook", packetId );
    }

    public PacketPositionAndLookBase(double x, double y, double z, double stance, float yaw, float pitch, boolean onGround, int packetId) {
        this( packetId );
        this.x = x;
        this.y = y;
        this.z = z;
        this.stance = stance;
        this.yaw = yaw;
        this.onGround = onGround;
    }

    @Override
    public void handle(PacketHandler handler) {
        handler.handle( this );
    }
}
