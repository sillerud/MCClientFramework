package net.theunnameddude.mcclient.protocol.ver1_6_4.packets;

import io.netty.buffer.ByteBuf;
import net.theunnameddude.mcclient.protocol.base.PacketLoginRequestBase;

public class Packet01LoginRequest extends PacketLoginRequestBase {

    public Packet01LoginRequest() {
        super( 0x01 );
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {

        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        entityId = buf.readInt();
        levelType = readString( buf, false );
        gameMode = buf.readByte();
        dimention = buf.readByte();
        difficulty = buf.readByte();
        buf.readByte(); // Just ignore that please
        maxPlayers = buf.readByte();
    }

}
