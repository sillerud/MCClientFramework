package net.theunnameddude.mcclient.protocol.base;

import lombok.Getter;
import net.theunnameddude.mcclient.protocol.PacketHandler;

public abstract class PacketSpawnPositionBase extends BasePacket {

    @Getter
    public int x;
    @Getter
    public int y;
    @Getter
    public int z;

    public PacketSpawnPositionBase(int packetId) {
        super( "SpawnPosition", packetId );
    }

    @Override
    public void handle(PacketHandler handler) {
        handler.handle( this );
    }
}
