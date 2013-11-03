package net.theunnameddude.mcclient.protocol.base;

import lombok.Getter;
import net.theunnameddude.mcclient.protocol.PacketHandler;

public abstract class PacketTeamBase extends BasePacket {

    @Getter
    public String teamName;
    @Getter
    public byte mode;
    @Getter
    public String displayName;
    @Getter
    public String prefix;
    @Getter
    public String suffix;
    @Getter
    public boolean friendlyFire;
    @Getter
    public short playerCount;
    @Getter
    public String[] players;

    public PacketTeamBase(int packetId) {
        super( "Team", packetId );
    }

    @Override
    public void handle(PacketHandler handler) {
        handler.handle( this );
    }
}
