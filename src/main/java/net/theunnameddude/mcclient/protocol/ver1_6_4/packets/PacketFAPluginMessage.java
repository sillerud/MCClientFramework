package net.theunnameddude.mcclient.protocol.ver1_6_4.packets;

import io.netty.buffer.ByteBuf;
import net.theunnameddude.mcclient.protocol.base.PacketPluginMessageBase;

public class PacketFAPluginMessage extends PacketPluginMessageBase {

    public PacketFAPluginMessage() {
        super( 0xFA );
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {
        setString( buf, channel, false );
        setBytes( buf, content );
        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        channel = readString( buf, false );
        content = readBytes( buf );
    }

    public String getChannel() {
        return channel;
    }

    public byte[] getContent() {
        return content;
    }
}
