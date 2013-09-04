package net.theunnameddude.protocol;

import io.netty.buffer.ByteBuf;

public class PacketFCEncryptionResponse extends BasePacket {

    public byte[] sharedSecret;
    public byte[] token;

    PacketFCEncryptionResponse() {
        super( 0xFC );
    }

    public PacketFCEncryptionResponse( byte[] sharedSecret, byte[] token ) {
        this();
        this.sharedSecret = sharedSecret;
        this.token = token;
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {
        setBytes( buf, sharedSecret );
        setBytes( buf, token );
        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {

    }
}
