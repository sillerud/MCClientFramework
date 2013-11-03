package net.theunnameddude.mcclient.protocol.ver1_7_2.packets;

import io.netty.buffer.ByteBuf;
import net.theunnameddude.mcclient.protocol.base.PacketPlayerListItemBase;

public class PacketPlayerListItem extends PacketPlayerListItemBase {


    public PacketPlayerListItem() {
        super( 0x38 );
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {
        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        name = readString( buf, true );
        online = buf.readBoolean();
        ping = buf.readShort();
    }
}
