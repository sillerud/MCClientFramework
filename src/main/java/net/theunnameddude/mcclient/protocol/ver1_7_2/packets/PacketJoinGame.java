package net.theunnameddude.mcclient.protocol.ver1_7_2.packets;

import io.netty.buffer.ByteBuf;
import net.theunnameddude.mcclient.protocol.base.PacketLoginRequestBase;

public class PacketJoinGame extends PacketLoginRequestBase {


    public PacketJoinGame() {
        super( 0x01 );
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {
        // Just one way
        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        entityId = buf.readInt();
        gameMode = buf.readUnsignedByte();
        dimention = buf.readByte();
        difficulty = buf.readUnsignedByte();
        maxPlayers = buf.readUnsignedByte();
        levelType = readString( buf, true );
    }
}
