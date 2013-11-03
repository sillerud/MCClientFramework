package net.theunnameddude.mcclient.protocol.ver1_6_4.packets;

import io.netty.buffer.ByteBuf;
import net.theunnameddude.mcclient.protocol.base.PacketChatBase;
import org.json.JSONException;
import org.json.JSONObject;

public class Packet03Chat extends PacketChatBase {
    public Packet03Chat() {
        super( 0x03 );
    }

    public Packet03Chat(JSONObject message) {
        super( message, 0x03 );
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {
        try {
            setString( buf, message.getString( "text" ), false );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        try {
            message = new JSONObject( readString( buf , false) );
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
