package net.theunnameddude.mcclient.protocol.ver1_7_2.packets;

import io.netty.buffer.ByteBuf;
import lombok.Getter;
import net.theunnameddude.mcclient.protocol.base.PacketHandshakeBase;

public class PacketHandshake extends PacketHandshakeBase {
    @Getter
    int state;

    public PacketHandshake(String host, int port, byte protocolVersion, int state) {
        super( null, host, port, protocolVersion, 0x00 );
        this.state = state;
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {
        setVarInt( buf, protocolVersion );
        setString( buf, host, true );
        buf.writeShort( port );
        setVarInt( buf, state );
        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        protocolVersion = (byte) readVarInt( buf );
        host = readString( buf, true );
        port = buf.readUnsignedShort();
        state = readVarInt( buf );
    }
}
