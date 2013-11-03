package net.theunnameddude.mcclient.protocol.ver1_7_2.packets;

import io.netty.buffer.ByteBuf;
import net.theunnameddude.mcclient.protocol.PacketHandler;
import net.theunnameddude.mcclient.protocol.base.BasePacket;

public class LoginStartPacket extends BasePacket {
    String name;

    public LoginStartPacket(String name) {
        super( "LoginStart", 0x00 );
        this.name = name;
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {
        setString( buf, name, true );
        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
    }

    @Override
    public void handle(PacketHandler handler) {
    }
}
