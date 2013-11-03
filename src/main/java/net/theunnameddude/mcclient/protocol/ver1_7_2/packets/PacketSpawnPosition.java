package net.theunnameddude.mcclient.protocol.ver1_7_2.packets;

import io.netty.buffer.ByteBuf;
import net.theunnameddude.mcclient.protocol.base.PacketSpawnPositionBase;

public class PacketSpawnPosition extends PacketSpawnPositionBase {
    public PacketSpawnPosition() {
        super( 0x05 );
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {
        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
    }
}
