package net.theunnameddude.mcclient.protocol.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.theunnameddude.mcclient.protocol.PacketHandler;

public class Packet02Handshake extends BasePacket {

    public final static byte protocolVersion = 78;
    String username;
    String host;
    int port;

    public Packet02Handshake() {
        super( 0x02, "handshake" );
    }

    public Packet02Handshake(String username, String host, int port) {
        this();
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
        //buf.writeByte( protocolVersion );
        //setString( buf, username );
        //setString( buf, host );
        //buf.writeInt( port );
    }

    @Override
    public void handle(PacketHandler handler, ChannelHandlerContext ctx) {
        handler.handle( this );
    }
}
