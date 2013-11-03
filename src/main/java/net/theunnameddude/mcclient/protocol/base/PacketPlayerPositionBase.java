package net.theunnameddude.mcclient.protocol.base;

import lombok.Getter;
import net.theunnameddude.mcclient.protocol.PacketHandler;

public abstract class PacketPlayerPositionBase extends BasePacket {
    @Getter
    public double x;
    @Getter
    public double y;
    @Getter
    public double stance;
    @Getter
    public double z;
    @Getter
    public boolean onGround;

    public PacketPlayerPositionBase(int packetId) {
        super( "PlayerPosition", packetId );
    }

    public PacketPlayerPositionBase(double x, double y, double z, double stance, boolean onGround, int packetId) {
        this( packetId );
        this.x = x;
        this.y = y;
        this.z = z;
        this.stance = stance;
        this.onGround = onGround;
    }

    @Override
    public void handle(PacketHandler handler) {
        handler.handle( this );
    }
}
