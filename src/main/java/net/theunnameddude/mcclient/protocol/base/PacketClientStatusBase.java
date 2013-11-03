package net.theunnameddude.mcclient.protocol.base;

import lombok.Getter;
import net.theunnameddude.mcclient.protocol.PacketHandler;

public abstract class PacketClientStatusBase extends BasePacket {

    @Getter
    public byte payload = 0;

    public PacketClientStatusBase(int packetId) {
        super( "ClientStatus", packetId );
    }

    public PacketClientStatusBase(byte payload, int packetId) {
        this( packetId );
        this.payload = payload;
    }

    @Override
    public void handle(PacketHandler handler) {
        handler.handle( this );
    }
}
