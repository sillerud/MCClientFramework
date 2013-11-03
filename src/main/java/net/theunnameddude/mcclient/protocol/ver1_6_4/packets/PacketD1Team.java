package net.theunnameddude.mcclient.protocol.ver1_6_4.packets;

import io.netty.buffer.ByteBuf;
import net.theunnameddude.mcclient.protocol.base.PacketTeamBase;

public class PacketD1Team extends PacketTeamBase {
    public PacketD1Team() {
        super( 0xD1 );
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {

        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        teamName = readString( buf, false );
        mode = buf.readByte();
        //( mode == 0 ) Create
        //( mode == 1 ) Remove
        //( mode == 2 ) Update
        //( mode == 3 ) Add player
        //( mode == 4 ) Player removed
        if ( mode == 0 || mode == 2) {
            displayName = readString( buf, false );
            prefix = readString( buf, false );
            suffix = readString( buf, false );
            friendlyFire = buf.readBoolean();
        }
        if ( mode == 0 || mode == 3 || mode == 4 ) {
            playerCount = buf.readShort();
            if ( playerCount > 0 ) {
                players = new String[playerCount];
                for ( int i = 0; i < playerCount; i ++ ) {
                    players[i] = readString( buf, false );
                }
            } else {
                players = new String[0];
            }
        }
    }
}
