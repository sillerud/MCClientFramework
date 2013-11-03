package net.theunnameddude.mcclient.protocol.ver1_7_2.packets;

import io.netty.buffer.ByteBuf;
import net.theunnameddude.mcclient.protocol.base.PacketEncryptionRequestBase;

public class PacketEncryptionRequest extends PacketEncryptionRequestBase {

    public PacketEncryptionRequest() {
        super( 0x01 );
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {
        setString( buf, serverId, true );
        setBytes( buf, publicKey );
        setBytes( buf, token );
        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        serverId = readString( buf, true );
        publicKey = readBytes( buf );
        token = readBytes( buf );
    }
}
