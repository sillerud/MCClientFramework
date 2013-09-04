package net.theunnameddude.protocol;

import io.netty.buffer.ByteBuf;

public class Packet02Handshake extends BasePacket {

    public final static byte protocolVersion = 74;
    String username;
    String host;
    int port;

    public Packet02Handshake(String username, String host, int port) {
        super( 0x02 );
        this.username = username;
        this.host = host;
        this.port = port;
    }

    @Override
    public ByteBuf getPacket( ByteBuf buf ) {
        buf.writeByte( protocolVersion );
        setString( buf, username );
        setString( buf, host );
        buf.writeInt( port );

        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        buf.writeByte( protocolVersion );
        setString( buf, username );
        setString( buf, host );
        buf.writeInt( port );
    }
}
