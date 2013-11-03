package net.theunnameddude.mcclient.client;

import net.theunnameddude.mcclient.api.Player;
import net.theunnameddude.mcclient.protocol.ver1_6_4.packets.Packet0DPositionAndLook;

public class PlayerImpl implements Player {

    int entityId;
    double x;
    double y;
    double z;
    double stance;
    float yaw;
    float pitch;
    boolean onGround;
    MinecraftClientImpl client;

    public PlayerImpl(int entityId, MinecraftClientImpl client) {
        this.entityId = entityId;
        this.client = client;
    }

    public void setLocation(double x, double y, double z, double stance, float yaw, float pitch, boolean onGround) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.stance = stance;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
    }

    @Override
    public int getEntityId() {
        return entityId;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getZ() {
        return z;
    }

    @Override
    public double getStance() {
        return stance;
    }

    @Override
    public float getYaw() {
        return yaw;
    }

    @Override
    public float getPitch() {
        return pitch;
    }

    @Override
    public boolean isOnGround() {
        return onGround;
    }

    @Override
    public void sendLocation() {
        client.sendPacket( client.pc.packetPositionAndLook( x, y, z, stance, yaw, pitch, onGround) );
    }
}
