package net.theunnameddude.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import net.theunnameddude.auth.AuthenticationResponse;
import net.theunnameddude.protocol.*;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;

public class MinecraftPacketHandler extends ChannelInboundHandlerAdapter {
    public AuthenticationResponse auth;
    KeyPair keys;
    PacketHandler packetHandler = new PacketHandler( this );

    public MinecraftPacketHandler(AuthenticationResponse auth) {
        this.auth = auth;
        try {
            keys = KeyPairGenerator.getInstance( "RSA" ).generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        System.out.println( buf );
        byte packetType = buf.readByte();
        if ( packetType == (byte)0xFF ) {
            PacketFFKick packet = new PacketFFKick();
            packet.onPacket( buf );
            packetHandler.handle( packet, ctx );
        } else if ( packetType == (byte)0xFD ) {
            PacketFDEncryptionRequest packet = new PacketFDEncryptionRequest();
            packet.onPacket( buf );
            packetHandler.handle( packet, ctx );
        } else {
            System.out.println( "Unhandled packet 0x" + Integer.toHexString( packetType & 0xFF ) );
        }
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf buf = new Packet02Handshake( "TheUnnamedDude", "localhost", 25565 ).getPacket( Unpooled.buffer().writeByte( 0x02 ) );
        ctx.writeAndFlush( buf );
    }
}
