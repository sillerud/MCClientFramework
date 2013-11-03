package net.theunnameddude.mcclient.protocol.base;

import lombok.Getter;
import net.theunnameddude.mcclient.protocol.PacketHandler;

public abstract class PacketLoginRequestBase extends BasePacket {
    @Getter
    public int entityId;
    @Getter
    public String levelType;
    @Getter
    public short gameMode;
    @Getter
    public byte dimention;
    @Getter
    public short difficulty;
    @Getter
    public short maxPlayers;

    public PacketLoginRequestBase(int packetId) {
        super( "LoginRequest", packetId );
    }

    @Override
    public void handle(PacketHandler handler) {
        handler.handle( this );
    }
}
