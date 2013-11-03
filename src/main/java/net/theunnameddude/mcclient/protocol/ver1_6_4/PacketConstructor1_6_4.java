package net.theunnameddude.mcclient.protocol.ver1_6_4;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import net.theunnameddude.mcclient.client.MinecraftClientImpl;
import net.theunnameddude.mcclient.protocol.PacketConstructor;
import net.theunnameddude.mcclient.protocol.PacketHandler;
import net.theunnameddude.mcclient.protocol.base.*;
import net.theunnameddude.mcclient.protocol.ver1_6_4.netty.MinecraftPacketDecoder;
import net.theunnameddude.mcclient.protocol.ver1_6_4.netty.MinecraftPacketEncoder;
import net.theunnameddude.mcclient.protocol.ver1_6_4.packets.*;
import net.theunnameddude.mcclient.protocol.ver1_6_4.values.ValueReader;
import org.json.JSONObject;

public class PacketConstructor1_6_4 implements PacketConstructor {
    public static final byte protocolVersion = (byte)78;

    @Override
    public void disconnect(MinecraftClientImpl client) {
        client.sendPacket( new PacketFFKick( "Quitting." ) );
    }

    @Override
    public PacketClientStatusBase packetClientStatus() {
        return new PacketCDClientStatus();
    }

    @Override
    public PacketEncryptionResponseBase packetEncryptionResponse(byte[] sharedKey, byte[] token) {
        return new PacketFCEncryptionResponse( sharedKey, token );
    }

    @Override
    public PacketChatBase packetChat(JSONObject message) {
        return new Packet03Chat( message );
    }

    @Override
    public void handshake(String user, String host, int port, MinecraftClientImpl client) {
        client.sendPacket( new Packet02Handshake( user, host, port, protocolVersion ) );
    }

    @Override
    public PacketPositionAndLookBase packetPositionAndLook(double x, double y, double z, double stance, float yaw, float pitch, boolean onGround) {
        return new Packet0DPositionAndLook( x, y, x, stance, yaw, pitch, onGround );
    }

    @Override
    public BasePacket readPacket( short packetId, ByteBuf buf ) {
        BasePacket packet;
        int id = (int)packetId;
        //System.out.println( "Send packet 0x" + Integer.toHexString( packetId & 0xFF ) );
        if ( packetId == 0x00 ) {
            packet = new Packet00KeepAlive();
        } else if ( packetId == 0x01 ) {
            packet = new Packet01LoginRequest();
        } else if ( packetId == 0x02 ) {
            packet = new Packet02Handshake( protocolVersion );
        } else if ( packetId == 0x03 ) {
            packet = new Packet03Chat();
        } else if ( packetId == 0x06 ) {
            packet = new Packet06SpawnPosition();
        } else if ( packetId == 0x09 ) {
            packet = new Packet09Respawn();
        } else if ( packetId == 0x0B ) {
            packet = new Packet0BPlayerPosition();
        } else if ( packetId == 0x0D ) {
            packet = new Packet0DPositionAndLook();
        } else if ( packetId == 0xC9 ) {
            packet = new PacketC9PlayerListItem();
        } else if ( packetId == 0xCD ) {
            packet = new PacketCDClientStatus();
        } else if ( packetId == 0xD1 ) {
            packet = new PacketD1Team();
        } else if ( packetId == 0xFA ) {
            packet = new PacketFAPluginMessage();
        } else if ( packetId == 0xFC ) {
            packet = new PacketFCEncryptionResponse();
        } else if ( packetId == 0xFD ) {
            packet = new PacketFDEncryptionRequest();
        } else if ( packetId == 0xFF ) {
            packet = new PacketFFKick();
        } else {

            ValueReader[] readers = PacketSkipper.packets[ packetId ];
            if ( readers == null ) {
                System.out.println( "Unknown packet: 0x" + Integer.toHexString( ((byte)packetId) & 0xFF ).toUpperCase() );
            } else {
                for ( ValueReader reader : readers ) {
                    reader.handle( buf );
                }
            }

            return null;
        }
        packet.onPacket( buf );
        return packet;
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
    public PacketHandler createPacketHandler(MinecraftClientImpl client) {
        return new PacketHandler1_6_4( client );
    }
}
