package net.theunnameddude.mcclient.protocol.ver1_6_4.packets;

import io.netty.buffer.ByteBuf;
import net.theunnameddude.mcclient.protocol.base.PacketEncryptionResponseBase;

public class PacketFCEncryptionResponse extends PacketEncryptionResponseBase {

    public PacketFCEncryptionResponse() {
        super( 0xFC );
    }

    public PacketFCEncryptionResponse(byte[] sharedKey, byte[] token) {
        super( sharedKey, token, 0xFC );
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {
        setBytes( buf, sharedSecret );
        setBytes( buf, token );
        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        this.sharedSecret = readBytes( buf );
        this.token = readBytes( buf );
    }

    public byte[] getSharedSecret() {
        return sharedSecret;
    }

    public byte[] getToken() {
        return token;
    }
}
