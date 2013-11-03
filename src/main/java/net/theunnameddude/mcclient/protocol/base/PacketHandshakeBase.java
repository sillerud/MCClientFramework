package net.theunnameddude.mcclient.protocol.base;

import lombok.Getter;
import net.theunnameddude.mcclient.protocol.PacketHandler;

public abstract class PacketHandshakeBase extends BasePacket {
    @Getter
    public byte protocolVersion;
    @Getter
    public String username;
    @Getter
    public String host;
    @Getter
    public int port;

    public PacketHandshakeBase(byte protocolVersion, int packetId) {
        super( "Handshake", packetId );
        this.protocolVersion = protocolVersion;
    }

    public PacketHandshakeBase(String username, String host, int port, byte protocolVersion, int packetId) {
        this( protocolVersion, packetId );
        this.username = username;
        this.host = host;
        this.port = port;
    }

    @Override
    public void handle(PacketHandler handler) {}
}
