package net.theunnameddude.mcclient.protocol.ver1_7_2.packets;

import io.netty.buffer.ByteBuf;
import net.theunnameddude.mcclient.protocol.base.PacketChatBase;
import org.json.JSONException;
import org.json.JSONObject;

public class PacketChat extends PacketChatBase {
    public PacketChat() {
        super( 0x01 );
    }

    public PacketChat(JSONObject message) {
        super( message, 0x01 );
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {
        try {
            setString( buf, message.getString( "text" ), true );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        try {
            message = new JSONObject( readString( buf, true ) );
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
