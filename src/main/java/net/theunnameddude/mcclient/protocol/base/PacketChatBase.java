package net.theunnameddude.mcclient.protocol.base;

import lombok.Getter;
import net.theunnameddude.mcclient.protocol.PacketHandler;
import org.json.JSONObject;

public abstract class PacketChatBase extends BasePacket {
    @Getter
    public JSONObject message;

    public PacketChatBase(int packetId) {
        super( "Chat", packetId );
    }

    public PacketChatBase(JSONObject message, int packetId) {
        this(packetId);
        this.message = message;
    }

    @Override
    public void handle(PacketHandler handler) {
        handler.handle( this );
    }
}
