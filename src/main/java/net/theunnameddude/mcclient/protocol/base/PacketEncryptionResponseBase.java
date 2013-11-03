package net.theunnameddude.mcclient.protocol.base;

import lombok.Getter;
import net.theunnameddude.mcclient.protocol.PacketHandler;

public abstract class PacketEncryptionResponseBase extends BasePacket {

    @Getter
    public byte[] sharedSecret;
    @Getter
    public byte[] token;

    public PacketEncryptionResponseBase(int packetId) {
        super( "EncryptionResponse", packetId );
    }

    public PacketEncryptionResponseBase(byte[] sharedSecret, byte[] token, int packetId) {
        this( packetId );
        this.sharedSecret = sharedSecret;
        this.token = token;
    }

    @Override
    public void handle(PacketHandler handler) {
        handler.handle( this );
    }
}
