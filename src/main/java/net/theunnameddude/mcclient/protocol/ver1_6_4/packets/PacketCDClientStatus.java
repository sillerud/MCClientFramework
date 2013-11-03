package net.theunnameddude.mcclient.protocol.ver1_6_4.packets;

import io.netty.buffer.ByteBuf;
import net.theunnameddude.mcclient.protocol.base.PacketClientStatusBase;

public class PacketCDClientStatus extends PacketClientStatusBase {

    public PacketCDClientStatus() {
        super( 0xCD );
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
