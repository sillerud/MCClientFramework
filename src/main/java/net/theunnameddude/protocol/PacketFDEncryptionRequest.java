package net.theunnameddude.protocol;

import io.netty.buffer.ByteBuf;

public class PacketFDEncryptionRequest extends BasePacket {

    String serverId;
    byte[] pubKey;
    byte[] verifyToken;

    public PacketFDEncryptionRequest() {
        super( 0xFD );
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {

        setString( buf, serverId );
        setBytes( buf, pubKey );
        setBytes( buf, verifyToken );

        return buf;
    }

    @Override
    public void onPacket( ByteBuf buf ) {
        serverId = readString( buf );
        pubKey = readBytes( buf );
        verifyToken = readBytes( buf );
    }

    public String getServerId() {
        return serverId;
    }

    public byte[] getToken() {
        return verifyToken;
    }

    public byte[] getPublicKey() {
        return pubKey;
    }
}
