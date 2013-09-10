package net.theunnameddude.mcclient.protocol.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.theunnameddude.mcclient.protocol.PacketHandler;
import org.json.JSONException;
import org.json.JSONObject;

public class Packet03Chat extends BasePacket {
    JSONObject message;
    public Packet03Chat() {
        super( 0x03 );
    }

    public Packet03Chat(JSONObject message) {
        this();
        this.message = message;
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {
        try {
            setString( buf, message.getString( "text" ) );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        try {
            message = new JSONObject( readString( buf ) );
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(PacketHandler handler, ChannelHandlerContext ctx) {
        handler.handle( this );
    }

    public JSONObject getMessage() {
        return message;
    }
}
