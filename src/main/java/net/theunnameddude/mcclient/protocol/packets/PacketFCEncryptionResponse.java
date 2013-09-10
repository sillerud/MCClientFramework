package net.theunnameddude.mcclient.protocol.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.theunnameddude.mcclient.protocol.PacketHandler;

public class PacketFCEncryptionResponse extends BasePacket {

    private byte[] sharedSecret;
    private byte[] token;

    public PacketFCEncryptionResponse() {
        super( 0xFC );
    }

    public PacketFCEncryptionResponse( byte[] sharedSecret, byte[] token ) {
        this();
        this.sharedSecret = sharedSecret;
        this.token = token;
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {
        setBytes( buf, sharedSecret );
        setBytes( buf, token );
        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        this.sharedSecret = readBytes( buf );
        this.token = readBytes( buf );
    }

    public byte[] getSharedSecret() {
        return sharedSecret;
    }

    public byte[] getToken() {
        return token;
    }

    @Override
    public void handle(PacketHandler handler, ChannelHandlerContext ctx) {
        handler.handle( this );
    }
}
