package net.theunnameddude.mcclient.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import net.theunnameddude.mcclient.api.ProtocolStatus;
import net.theunnameddude.mcclient.client.MinecraftClientImpl;
import net.theunnameddude.mcclient.protocol.PacketHandler;
import net.theunnameddude.mcclient.protocol.packets.BasePacket;
import net.theunnameddude.mcclient.protocol.packets.Packet02Handshake;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class MinecraftPacketHandler extends ChannelInboundHandlerAdapter {
    KeyPair keys;
    PacketHandler packetHandler;
    public MinecraftClientImpl client;
    private String host;

    public MinecraftPacketHandler(MinecraftClientImpl client, String host) {
        this.client = client;
        packetHandler = new PacketHandler( client );
        try {
            keys = KeyPairGenerator.getInstance( "RSA" ).generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        this.host = host;
    }

    public void sendPacket( BasePacket packet ) {
        client.sendPacket( packet );
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        BasePacket packet = (BasePacket)msg;
        packet.handle(packetHandler, ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        client.setProtocolStatus( ProtocolStatus.Initial );
        if ( client.getAuth() == null ) {
            sendPacket( new Packet02Handshake( client.getAuth().getUsername(), host, client.port ) );
        } else {
            sendPacket( new Packet02Handshake( client.getAuth().getUsername(), host, 25565 ) );
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        try {
            client.shutdown();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        client.getListenerHandler().onDisconnect();
        ctx.close();
    }
}
