package net.theunnameddude.mcclient.api;

public interface Player {
    public int getEntityId() ;
    public double getX();
    public double getY();
    public double getZ();
    public double getStance();
    public float getYaw();
    public float getPitch();
    public boolean isOnGround();
    public void sendLocation();
}
