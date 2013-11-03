package net.theunnameddude.mcclient.protocol.ver1_6_4.packets;

import io.netty.buffer.ByteBuf;
import net.theunnameddude.mcclient.protocol.base.PacketEncryptionRequestBase;

public class PacketFDEncryptionRequest extends PacketEncryptionRequestBase {
    public PacketFDEncryptionRequest() {
        super( 0xFD );
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {

        setString( buf, serverId, false );
        setBytes( buf, publicKey );
        setBytes( buf, token );

        return buf;
    }

    @Override
    public void onPacket( ByteBuf buf ) {
        serverId = readString( buf, false );
        publicKey = readBytes( buf );
        token = readBytes( buf );
    }

}
