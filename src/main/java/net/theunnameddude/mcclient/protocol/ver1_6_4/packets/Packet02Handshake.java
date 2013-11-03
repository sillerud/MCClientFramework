package net.theunnameddude.mcclient.protocol.ver1_6_4.packets;

import io.netty.buffer.ByteBuf;
import net.theunnameddude.mcclient.protocol.base.PacketHandshakeBase;

public class Packet02Handshake extends PacketHandshakeBase {

    public Packet02Handshake(byte protocolVersion) {
        super( protocolVersion, 0x02 );
    }

    public Packet02Handshake(String username, String host, int port, byte protocolVersion) {
        super( username, host, port, protocolVersion, 0x02 );
    }

    @Override
    public ByteBuf getPacket( ByteBuf buf ) {
        buf.writeByte( protocolVersion );
        setString( buf, username, false );
        setString( buf, host, false );
        buf.writeInt( port );

        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {

    }
}
