package net.theunnameddude.mcclient.protocol.ver1_7_2.packets;

import io.netty.buffer.ByteBuf;
import net.theunnameddude.mcclient.protocol.PacketHandler;
import net.theunnameddude.mcclient.protocol.base.BasePacket;
import net.theunnameddude.mcclient.protocol.base.PacketKeepAliveBase;

public class PacketKeepAlive extends PacketKeepAliveBase {

    public PacketKeepAlive() {
        super( 0x00 );
    }

    public PacketKeepAlive(int id) {
        super( id, 0x00 );
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
}
