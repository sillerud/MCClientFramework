package net.theunnameddude.mcclient.protocol.ver1_7_2.packets;

import io.netty.buffer.ByteBuf;
import net.theunnameddude.mcclient.protocol.base.PacketEncryptionRequestBase;

public class PacketEncryptionRequest extends PacketEncryptionRequestBase {

    public PacketEncryptionRequest() {
        super( 0x01 );
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {
        setBytes( buf, publicKey );
        setBytes( buf, token );
        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        publicKey = readBytes( buf );
        token = readBytes( buf );
    }
}
