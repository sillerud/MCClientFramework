package net.theunnameddude.mcclient.protocol.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.theunnameddude.mcclient.protocol.PacketHandler;

public class Packet00KeepAlive extends BasePacket {
    int id;

    public Packet00KeepAlive() {
        super( 0x00 );
    }

    public Packet00KeepAlive(int id) {
        this();
        this.id = id;
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {
        buf.writeInt( id );
        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        id = buf.readInt();
    }

    @Override
    public void handle(PacketHandler handler, ChannelHandlerContext ctx) {
        handler.handle( this );
    }

    public int getID() {
        return id;
    }
}
