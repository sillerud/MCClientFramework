package net.theunnameddude.mcclient.protocol.ver1_7_2.packets;

import io.netty.buffer.ByteBuf;
import net.theunnameddude.mcclient.protocol.base.PacketClientStatusBase;

public class PacketClientStatus extends PacketClientStatusBase {

    public PacketClientStatus(byte payload) {
        super( payload, 0x16 );
    }

    public PacketClientStatus() {
        super( 0x16 );
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {
        buf.writeByte( payload );
        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        payload = buf.readByte();
    }
}
