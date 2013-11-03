package net.theunnameddude.mcclient.protocol.ver1_7_2.packets;

import io.netty.buffer.ByteBuf;
import net.theunnameddude.mcclient.protocol.base.PacketRespawnBase;

public class PacketRespawn extends PacketRespawnBase {
    public PacketRespawn() {
        super( 0x07 );
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {
        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        dimention = buf.readInt();
        difficulty = buf.readUnsignedByte();
        gamemode = buf.readUnsignedByte();
        levelType = readString( buf, true );
    }
}
