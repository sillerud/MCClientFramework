package net.theunnameddude.mcclient.protocol.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.theunnameddude.mcclient.protocol.PacketHandler;

public class PacketD1Team extends BasePacket {
    String teamName;
    byte mode;
    String displayName;
    String prefix;
    String suffix;
    boolean friendlyFire;
    short playerCount;
    String[] players;
    public PacketD1Team() {
        super( 0xD1, "team" );
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {

        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        teamName = readString( buf );
        mode = buf.readByte();
        //( mode == 0 ) Create
        //( mode == 1 ) Remove
        //( mode == 2 ) Update
        //( mode == 3 ) Add player
        //( mode == 4 ) Player removed
        if ( mode == 0 || mode == 2) {
            displayName = readString( buf );
            prefix = readString( buf );
            suffix = readString( buf );
            friendlyFire = buf.readBoolean();
        }
        if ( mode == 0 || mode == 3 || mode == 4 ) {
            playerCount = buf.readShort();
            if ( playerCount > 0 ) {
                players = new String[playerCount];
                for ( int i = 0; i < playerCount; i ++ ) {
                    players[i] = readString( buf );
                }
            } else {
                players = new String[0];
            }
        }


    }

    @Override
    public void handle(PacketHandler handler, ChannelHandlerContext ctx) {
        handler.handle( this );
    }

    public String getTeamName() {
        return teamName;
    }

    public byte getMode() {
        return mode;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public boolean getFriendlyFire() {
        return friendlyFire;
    }

    public short getPlayerCount() {
        return playerCount;
    }

    public String[] getPlayers() {
        return players;
    }

}
