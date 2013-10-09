package net.theunnameddude.mcclient.protocol.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.theunnameddude.mcclient.protocol.PacketHandler;

public class Packet01LoginRequest extends BasePacket {
    int entityId;
    String levelType;
    byte gameMode;
    byte dimention;
    byte difficulty;
    byte notUsedPreBuildheight;
    byte maxPlayers;

    public Packet01LoginRequest() {
        super( 0x01, "login-request" );
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {

        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        entityId = buf.readInt();
        levelType = readString( buf );
        gameMode = buf.readByte();
        dimention = buf.readByte();
        difficulty = buf.readByte();
        notUsedPreBuildheight = buf.readByte();
        maxPlayers = buf.readByte();
    }

    @Override
    public void handle(PacketHandler handler, ChannelHandlerContext ctx) {
        handler.handle( this );
    }

    public int getEntityId() {
        return entityId;
    }

    public String getLevelType() {
        return levelType;
    }

    public byte getGameMode() {
        return gameMode;
    }

    public byte getDimention() {
        return dimention;
    }

    public byte getDifficulty() {
        return difficulty;
    }

    public byte getMaxPlayers() {
        return maxPlayers;
    }
}
