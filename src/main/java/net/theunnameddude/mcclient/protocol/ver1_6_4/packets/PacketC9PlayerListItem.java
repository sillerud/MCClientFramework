package net.theunnameddude.mcclient.protocol.ver1_6_4.packets;

import io.netty.buffer.ByteBuf;
import net.theunnameddude.mcclient.protocol.base.PacketPlayerListItemBase;

public class PacketC9PlayerListItem extends PacketPlayerListItemBase {

    public PacketC9PlayerListItem() {
        super( 0xC9 );
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {
        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        name = readString( buf, false );
        online = buf.readBoolean();
        ping = buf.readShort();

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
