package net.theunnameddude.mcclient.protocol.base;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import lombok.Getter;
import net.theunnameddude.mcclient.protocol.PacketHandler;

import java.nio.charset.Charset;

public abstract class BasePacket {

    String packetName;
    @Getter
    int packetId;

    public BasePacket(String packetName, int packetId) {
        this.packetName = packetName;
        this.packetId = packetId;
    }

    public abstract ByteBuf getPacket(ByteBuf buf);
    public abstract void onPacket(ByteBuf buf);
    public abstract void handle(PacketHandler handler);

    public void setString(ByteBuf buf, String string, boolean v17format) {
        if ( v17format ) {
            byte[] content = string.getBytes( Charset.forName( "UTF-8" ) );
            setVarInt( buf, content.length );
            buf.writeBytes( content );
        } else {
            buf.writeShort( string.length() );
            for ( char c : string.toCharArray() ) {
                buf.writeChar( c );
            }
        }
    }

    public String readString(ByteBuf buf, boolean v17format) {
        if ( v17format ) {
            int length = readVarInt( buf );
            byte[] content = new byte[ length ];
            buf.readBytes( content );
            return new String( content, Charset.forName( "UTF-8" ) );
        } else {
            int length = buf.readShort();
            char[] chars = new char[ length ];
            for ( int i = 0; i < length; i++ ) {
                chars[i] = buf.readChar();
            }
            return new String( chars );
        }
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

    public String getPacketName() {
        return packetName;
    }

    public static int readVarInt(ByteBuf buf) {
        int result = 0;
        int bytes = 0;
        byte in;
        while ( true ) {
            in = buf.readByte();
            result |= ( in & 0x7F ) << ( bytes++ * 7 );

            if ( bytes > 32 ) {
                throw new RuntimeException( "VarInt too big" );
            }
            if ( ( in & 0x80 ) != 0x80 ) {
                break;
            }
        }

        return result;
    }

    public static void setVarInt(ByteBuf buf, int value) {
        int part;
        while ( true ) {
            part = value & 0x7F;
            value >>>= 7;
            if ( value != 0 ) {
                part |= 0x80;
            }

            buf.writeByte( part );

            if ( value == 0 ) {
                break;
            }
        }
    }
}
