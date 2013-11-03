package net.theunnameddude.mcclient.protocol.base;

import lombok.Getter;
import net.theunnameddude.mcclient.protocol.PacketHandler;

public abstract class PacketRespawnBase extends BasePacket {
    @Getter
    public int dimention;
    @Getter
    public short difficulty;
    @Getter
    public short gamemode;
    @Getter
    public short worldHeight;
    @Getter
    public String levelType;

    public PacketRespawnBase(int packetId) {
        super( "Respawn", packetId );
    }

    @Override
    public void handle(PacketHandler handler) {
        handler.handle( this );
    }
}
