package net.theunnameddude.mcclient.protocol.ver1_7_2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import net.theunnameddude.mcclient.client.MinecraftClientImpl;
import net.theunnameddude.mcclient.protocol.PacketConstructor;
import net.theunnameddude.mcclient.protocol.base.*;
import net.theunnameddude.mcclient.protocol.ver1_7_2.netty.*;
import net.theunnameddude.mcclient.protocol.ver1_7_2.packets.*;
import org.json.JSONObject;

public class PacketConstructor1_7_2 implements PacketConstructor {

    public static byte protocolVersion = (byte)4;
    boolean initDone = false;

    @Override
    public PacketClientStatusBase packetClientStatus() {
        return new PacketClientStatus();
    }

    @Override
    public PacketEncryptionResponseBase packetEncryptionResponse(byte[] sharedKey, byte[] token) {
        return new PacketEncryptionResponse( sharedKey, token );
    }

    @Override
    public void disconnect(MinecraftClientImpl client) {
        try {
            client.getChannel().eventLoop().shutdownGracefully().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PacketChatBase packetChat(JSONObject message) {
        return new PacketChat( message );
    }

    @Override
    public void handshake(String user, String host, int port, MinecraftClientImpl client) {
        client.sendPacket( new PacketHandshake( host, port, protocolVersion, 2 ) );
        client.sendPacket( new LoginStartPacket( user ) );
    }

    @Override
    public PacketPositionAndLookBase packetPositionAndLook(double x, double y, double z, double stance, float yaw, float pitch, boolean onGround) {
        return new PacketPositionAndLook( x, y, x, stance, yaw, pitch, onGround );
    }

    @Override
    public BasePacket readPacket(short packetId, ByteBuf buf) {
        BasePacket p = null;
        if ( initDone ) {
            if ( packetId == 0x00 ) {
                p = new PacketKeepAlive();
            } else if ( packetId == 0x01 ) {
                p = new PacketJoinGame();
            } else if ( packetId == 0x02 ) {
                p = new PacketChat();
            } else if ( packetId == 0x05 ) {
                p = new PacketSpawnPosition();
            } else if ( packetId == 0x07 ) {
                p = new PacketRespawn();
            } else if ( packetId == 0x08) {
                p = new PacketPositionAndLook();
            } else if ( packetId == 0x16 ) {
                p = new PacketClientStatus();
            } else if ( packetId == 0x38 ) {
                p = new PacketPlayerListItem();
            } else if ( packetId == 0x3E ) {
                p = new PacketTeam();
            } else if ( packetId == 0x3F ) {
                p = new PacketPluginMessage();
            } else if ( packetId == 0x40 ) {
                p = new PacketKick();
            }
        } else {
            if ( packetId == 0x00 ) {
                p = new PacketKick();
            } else if ( packetId == 0x01 ) {
                p = new PacketEncryptionRequest();
            } else if ( packetId == 0x02 ) {
                // Wat to do?
                initDone = true;
            }
        }
        if ( p != null ) {
            p.onPacket( buf );
        }
        return p;
    }

    @Override
    public ChannelHandler createPacketDecoder() {
        return new MinecraftPacketDecoder( this );
    }

    @Override
    public ChannelHandler createPacketEncoder() {
        return new MinecraftPacketEncoder();
    }

    @Override
    public PacketHandler1_7_2 createPacketHandler(MinecraftClientImpl client) {
        return new PacketHandler1_7_2( client );
    }
}
