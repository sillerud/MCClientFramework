package net.theunnameddude.mcclient.protocol.base;

import lombok.Getter;
import net.theunnameddude.mcclient.protocol.PacketHandler;

public abstract class PacketPlayerListItemBase extends BasePacket {

    @Getter
    public String name;
    @Getter
    public boolean online;
    @Getter
    public short ping;

    public PacketPlayerListItemBase(int packetId) {
        super( "PlayerListItem", packetId );
    }

    @Override
    public void handle(PacketHandler handler) {
        handler.handle( this );
    }
}
