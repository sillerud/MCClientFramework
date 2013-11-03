package net.theunnameddude.mcclient.protocol.base;

import lombok.Getter;
import net.theunnameddude.mcclient.protocol.PacketHandler;

public abstract class PacketPluginMessageBase extends BasePacket {

    @Getter
    public String channel;
    @Getter
    public byte[] content;

    public PacketPluginMessageBase(int packetId) {
        super( "PluginMessage", packetId );
    }

    public PacketPluginMessageBase(String channel, byte[] content, int packetId) {
        this( packetId );
        this.channel = channel;
        this.content = content;
    }

    @Override
    public void handle(PacketHandler handler) {
        handler.handle( this );
    }
}
