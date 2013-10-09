package net.theunnameddude.mcclient.protocol.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.theunnameddude.mcclient.protocol.PacketHandler;

public class PacketC9PlayerListItem extends BasePacket {

    String name;
    boolean online;
    short ping;

    public PacketC9PlayerListItem() {
        super( 0xC9, "player-list-item" );
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {
        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        name = readString( buf );
        online = buf.readBoolean();
        ping = buf.readShort();

    }

    @Override
    public void handle(PacketHandler handler, ChannelHandlerContext ctx) {
        handler.handle( this );
    }

    public String getName() {
        return name;
    }

    public boolean isOnline() {
        return online;
    }

    public short getPing() {
        return ping;
    }
}
