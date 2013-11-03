package net.theunnameddude.mcclient.protocol.ver1_6_4.packets;

import io.netty.buffer.ByteBuf;
import net.theunnameddude.mcclient.protocol.base.PacketKeepAliveBase;

public class Packet00KeepAlive extends PacketKeepAliveBase {

    public Packet00KeepAlive() {
        super( 0x00 );
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
