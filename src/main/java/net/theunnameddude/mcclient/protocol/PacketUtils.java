package net.theunnameddude.mcclient.protocol;

import io.netty.buffer.ByteBuf;
import net.theunnameddude.mcclient.protocol.packets.*;
import net.theunnameddude.mcclient.protocol.values.ValueReader;

import java.util.HashMap;

public class PacketUtils {
    public static BasePacket readPacket( short packetId, ByteBuf buf ) {
        BasePacket packet;
        int id = (int)packetId;
        //System.out.println( "Send packet 0x" + Integer.toHexString( packetId & 0xFF ) );
        if ( packetId == 0x00 ) {
            packet = new Packet00KeepAlive();
        } else if ( packetId == 0x01 ) {
            packet = new Packet01LoginRequest();
        } else if ( packetId == 0x02 ) {
            packet = new Packet02Handshake();
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
}
