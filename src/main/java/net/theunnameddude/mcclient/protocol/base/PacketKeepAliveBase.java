package net.theunnameddude.mcclient.protocol.base;

import lombok.Getter;
import net.theunnameddude.mcclient.protocol.PacketHandler;

public abstract class PacketKeepAliveBase extends BasePacket {

    @Getter
    public int id;

    public PacketKeepAliveBase(int packetId) {
        super( "KeepAlive", packetId );
    }

    public PacketKeepAliveBase(int id, int packetId) {
        this( packetId );
        this.id = id;
    }

    @Override
    public void handle(PacketHandler handler) {
        handler.handle( this );
    }
}
