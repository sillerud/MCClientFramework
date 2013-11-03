package net.theunnameddude.mcclient.protocol.ver1_7_2.packets;

import io.netty.buffer.ByteBuf;
import net.theunnameddude.mcclient.protocol.base.PacketPluginMessageBase;

public class PacketPluginMessage extends PacketPluginMessageBase {

    public PacketPluginMessage(String channel, byte[] content) {
        super( channel, content, 0x3F );
    }

    public PacketPluginMessage() {
        super( 0x3F );
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {
        setString( buf, channel, true );
        setBytes( buf, content );
        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        channel = readString( buf, true );
        content = readBytes( buf );
    }
}
