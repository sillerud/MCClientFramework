package net.theunnameddude.mcclient.protocol.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.theunnameddude.mcclient.protocol.PacketHandler;

public class PacketFAPluginMessage extends BasePacket {
    String channel;
    byte[] content;

    public PacketFAPluginMessage() {
        super( 0xFA );
    }

    public PacketFAPluginMessage(String channel, byte[] content) {
        this();
        this.channel = channel;
        this.content = content;
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {
        setString( buf, channel );
        setBytes( buf, content );
        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        channel = readString( buf );
        content = readBytes( buf );
    }

    @Override
    public void handle(PacketHandler handler, ChannelHandlerContext ctx) {
        handler.handle( this );
    }

    public String getChannel() {
        return channel;
    }

    public byte[] getContent() {
        return content;
    }
}
