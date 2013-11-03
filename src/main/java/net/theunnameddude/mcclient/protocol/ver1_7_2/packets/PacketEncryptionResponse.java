package net.theunnameddude.mcclient.protocol.ver1_7_2.packets;

import io.netty.buffer.ByteBuf;
import net.theunnameddude.mcclient.protocol.base.PacketEncryptionResponseBase;

public class PacketEncryptionResponse extends PacketEncryptionResponseBase {

    public PacketEncryptionResponse() {
        super( 0x01 );
    }

    public PacketEncryptionResponse(byte[] sharedSecret, byte[] token) {
        super( sharedSecret, token, 0x01 );
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {
        setBytes( buf, sharedSecret );
        setBytes( buf, token );
        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        sharedSecret = readBytes( buf );
        token = readBytes( buf );
    }
}
