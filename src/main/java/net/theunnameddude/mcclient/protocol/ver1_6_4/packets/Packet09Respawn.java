package net.theunnameddude.mcclient.protocol.ver1_6_4.packets;

import io.netty.buffer.ByteBuf;
import net.theunnameddude.mcclient.protocol.base.PacketRespawnBase;

public class Packet09Respawn extends PacketRespawnBase {

    public Packet09Respawn() {
        super( 0x09 );
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {
        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        dimention = buf.readInt();
        difficulty = buf.readByte();
        gamemode = buf.readByte();
        worldHeight = buf.readShort();
        levelType = readString( buf, true );
    }

}
