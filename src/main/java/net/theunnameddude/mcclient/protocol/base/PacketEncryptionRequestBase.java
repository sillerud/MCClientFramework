package net.theunnameddude.mcclient.protocol.base;

import lombok.Getter;
import net.theunnameddude.mcclient.protocol.PacketHandler;

public abstract class PacketEncryptionRequestBase extends BasePacket {

    @Getter
    public String serverId;
    @Getter
    public byte[] publicKey;
    @Getter
    public byte[] token;

    public PacketEncryptionRequestBase(int packetId) {
        super( "EncryptionRequest", packetId );
    }

    @Override
    public void handle(PacketHandler handler) {
        handler.handle( this );
    }
}
