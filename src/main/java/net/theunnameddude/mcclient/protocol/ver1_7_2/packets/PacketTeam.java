package net.theunnameddude.mcclient.protocol.ver1_7_2.packets;

import io.netty.buffer.ByteBuf;
import net.theunnameddude.mcclient.protocol.base.PacketTeamBase;

public class PacketTeam extends PacketTeamBase {

    public PacketTeam() {
        super( 0x3E );
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {
        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        teamName = readString( buf, true );
        mode = buf.readByte();
        //( mode == 0 ) Create
        //( mode == 1 ) Remove
        //( mode == 2 ) Update
        //( mode == 3 ) Add player
        //( mode == 4 ) Player removed
        if ( mode == 0 || mode == 2) {
            displayName = readString( buf, true );
            prefix = readString( buf, true );
            suffix = readString( buf, true );
            friendlyFire = buf.readBoolean();
        }
        if ( mode == 0 || mode == 3 || mode == 4 ) {
            playerCount = buf.readShort();
            if ( playerCount > 0 ) {
                players = new String[playerCount];
                for ( int i = 0; i < playerCount; i ++ ) {
                    players[i] = readString( buf, true );
                }
            } else {
                players = new String[0];
            }
        }
    }
}
