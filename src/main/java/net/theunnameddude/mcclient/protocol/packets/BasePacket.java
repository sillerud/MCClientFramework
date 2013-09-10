package net.theunnameddude.mcclient.protocol.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.theunnameddude.mcclient.protocol.PacketHandler;

public abstract class BasePacket {

    int packetId;

    BasePacket( int packetId ) {
        this.packetId = packetId;
    }

    public int getPacketId() {
        return packetId;
    }

    public abstract ByteBuf getPacket(ByteBuf buf);
    public abstract void onPacket( ByteBuf buf );
    public abstract void handle( PacketHandler handler, ChannelHandlerContext ctx );

    public void setString( ByteBuf buf, String string ) {
        buf.writeShort( string.length() );
        for ( char c : string.toCharArray() ) {
            buf.writeChar( c );
        }
    }

    public String readString( ByteBuf buf ) {
        short length = buf.readShort();
        char[] chars = new char[ length ];

        for ( int i = 0; i < length; i++ ) {
            chars[i] = buf.readChar();
        }

        return new String( chars );
    }

    public void setBytes( ByteBuf buf, byte[] bytes ) {
        buf.writeShort( bytes.length );
        buf.writeBytes( bytes );
    }

    public byte[] readBytes( ByteBuf buf ) {
        short length = buf.readShort();
        byte[] bytes = new byte[length];
        buf.readBytes( bytes );
        return bytes;
    }
}
