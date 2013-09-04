package net.theunnameddude.protocol;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import net.theunnameddude.netty.MinecraftPacketHandler;
import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;

public class PacketHandler {
    MinecraftPacketHandler main;
    KeyPair keys;

    public PacketHandler(MinecraftPacketHandler main) {
        this.main = main;
        try
        {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);
            keys =  generator.generateKeyPair();
        }
        catch ( NoSuchAlgorithmException var1 ) {
        }
    }

    public void handle(Packet02Handshake packet, ChannelHandlerContext ctx) {

    }

    public void handle(PacketFCEncryptionResponse packet, ChannelHandlerContext ctx) {

    }

    public void handle(PacketFDEncryptionRequest packet, ChannelHandlerContext ctx) {
        if ( packet.getServerId().equals("-")) {
            System.out.println( "Offline mode!" );
        } else {
            System.out.println(packet.getServerId());
            try {

                byte[] token = encryptData( keys.getPublic(), packet.getToken() );
                byte[] serverId = encryptData(keys.getPublic(), packet.getServerId().getBytes(Charset.forName("UTF-8")));
                PacketFCEncryptionResponse response = new PacketFCEncryptionResponse( serverId, token );
                ctx.write( response.getPacket( Unpooled.buffer().writeByte( 0xFD ) ) );

                MessageDigest sha1 = MessageDigest.getInstance( "SHA-1" );
                sha1.update( packet.getServerId().trim().getBytes( "ISO_8859_1" ) );
                sha1.update( keys.getPublic().getEncoded() );
                sha1.update( createNewSharedKey().getEncoded() );

                String serverIdStr = new BigInteger( sha1.digest() ).toString( 16 );

                System.out.println( serverIdStr );

                StringBuilder stringurl = new StringBuilder();
                stringurl.append( "http://session.minecraft.net/game/joinserver.jsp?" )
                        .append( "user=" ).append( URLEncoder.encode( main.auth.getUsername(), "UTF-8" ) )
                        .append( "&sessionId=" ).append( URLEncoder.encode("token:" + main.auth.getAccessToken(), "UTF-8" ) )
                        .append( "&serverId=" ).append( URLEncoder.encode( serverIdStr, "UTF-8" ) );
                System.out.println( stringurl );
                try {
                    URL url = new URL( stringurl.toString() );
                    BufferedReader reader = new BufferedReader( new InputStreamReader( url.openConnection().getInputStream() ) );
                    String line;
                    while ( (line = reader.readLine()) != null ) {
                        System.out.println( line );
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //"http://session.minecraft.net/game/joinserver.jsp?user=username&sessionId=user_session&serverId=hash"

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    public static byte[] encryptData( Key key, byte[] toEncrypt ) {
        try {
            Cipher cipher = Cipher.getInstance( key.getAlgorithm() );
            cipher.init( Cipher.ENCRYPT_MODE, key );
            return cipher.doFinal( toEncrypt );
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public void handle(PacketFFKick packet, ChannelHandlerContext ctx) {
    }

    public static SecretKey createNewSharedKey()
    {
        CipherKeyGenerator generator = new CipherKeyGenerator();
        generator.init(new KeyGenerationParameters(new SecureRandom(), 128));
        return new SecretKeySpec(generator.generateKey(), "AES");
    }
}
