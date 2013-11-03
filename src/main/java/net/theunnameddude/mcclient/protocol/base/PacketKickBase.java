package net.theunnameddude.mcclient.protocol.base;

import lombok.Getter;
import net.theunnameddude.mcclient.protocol.PacketHandler;

public abstract class PacketKickBase extends BasePacket {
    @Getter
    public String reason;

    public PacketKickBase(int packetId) {
        super( "Kick", packetId );
    }

    public PacketKickBase(String reason, int packetId) {
        this( packetId );
        this.reason = reason;
    }

    @Override
    public void handle(PacketHandler handler) {
        handler.handle( this );
    }
}
