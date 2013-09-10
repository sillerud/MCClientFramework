package net.theunnameddude.mcclient.protocol;

import net.theunnameddude.mcclient.api.ProtocolStatus;
import net.theunnameddude.mcclient.client.MinecraftClientImpl;
import net.theunnameddude.mcclient.client.PlayerImpl;
import net.theunnameddude.mcclient.client.ServerInfo;
import net.theunnameddude.mcclient.encryption.EncryptionDecoder;
import net.theunnameddude.mcclient.encryption.EncryptionEncoder;
import net.theunnameddude.mcclient.encryption.EncryptionUtil;
import net.theunnameddude.mcclient.protocol.packets.*;
import org.json.JSONException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.*;

public class PacketHandler {
    MinecraftClientImpl client;
    public SecretKey secretKey;
    PublicKey serverPubKey;

    public PacketHandler(MinecraftClientImpl client) {
        this.client = client;
    }

    public void handle(Packet02Handshake packet) {

    }

    public void handle(PacketFCEncryptionResponse packet) {
        if ( packet.getSharedSecret().length == 0 && packet.getToken().length == 0 ) {
            try {
                Cipher decoder = Cipher.getInstance( "AES/CFB8/NoPadding" );
                decoder.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(secretKey.getEncoded()));
                client.getChannel().pipeline().addBefore("packet-decoder", "decrypter", new EncryptionDecoder(decoder));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (InvalidAlgorithmParameterException e) {
                e.printStackTrace();
            }
            client.sendPacket( new PacketCDClientStatus() );
        }
    }

    public void handle(PacketFDEncryptionRequest packet) {
        client.setProtocolStatus( ProtocolStatus.Authenticating );
        if ( packet.getServerId().equals("-")) {
            client.sendPacket( new PacketCDClientStatus() );
        } else {
            try {
                this.secretKey = EncryptionUtil.getSecret();
                serverPubKey = EncryptionUtil.getPubkey( packet.getPublicKey() );

                byte[] sharedKey = EncryptionUtil.encryptData(serverPubKey, secretKey.getEncoded());
                byte[] token = EncryptionUtil.encryptData(serverPubKey, packet.getToken());

                PacketFCEncryptionResponse response = new PacketFCEncryptionResponse( sharedKey, token );

                MessageDigest sha1 = MessageDigest.getInstance( "SHA-1" );
                sha1.update(packet.getServerId().trim().getBytes("ISO_8859_1"));
                sha1.update( secretKey.getEncoded() );
                sha1.update( serverPubKey.getEncoded() );

                String serverIdStr = new BigInteger( sha1.digest() ).toString( 16 );

                String sessionId = "token:" + client.getAuth().getAccessToken() + ":" + client.getAuth().getProfileId();

                StringBuilder stringurl = new StringBuilder();
                String urlStr = "http://session.minecraft.net/game/joinserver.jsp";
                stringurl.append( "http://session.minecraft.net/game/joinserver.jsp" )
                        .append( "?user=" ).append( URLEncoder.encode(client.getAuth().getUsername(), "UTF-8" ) )
                        .append( "&sessionId=" ).append( URLEncoder.encode( sessionId, "UTF-8" ) )
                        .append( "&serverId=" ).append(URLEncoder.encode(serverIdStr, "UTF-8"));

                try {
                    URL url = new URL( stringurl.toString() );
                    BufferedReader reader = new BufferedReader( new InputStreamReader( url.openConnection().getInputStream() ) );
                    String urlResponse = reader.readLine();

                    if ( !"OK".equals(urlResponse) ) {
                        client.getListenerHandler().onAuthFail(urlResponse);
                    } else {
                        client.sendPacket( new PacketFCEncryptionResponse(sharedKey, token) );

                    }

                    reader.close();
                    Cipher encoder = Cipher.getInstance( "AES/CFB8/NoPadding" );
                    encoder.init( Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec( secretKey.getEncoded() ) );
                    client.getChannel().pipeline().addBefore("packet-decoder", "encrypter", new EncryptionEncoder(encoder));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //ctx.pipeline().addBefore( "packet-decoder", "decrypt", new EncryptionEncoder( cipher ) );
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }
        }
    }

    public static String urlEnc( String str ) {
        try {
            return URLEncoder.encode( str, "UTF-8" );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void handle(PacketFFKick packet) {
        client.getListenerHandler().onKick( packet.getReason() );
        client.setProtocolStatus( ProtocolStatus.Disconnected );
    }

    public void handle(PacketCDClientStatus packet) {

    }

    public void handle(PacketFAPluginMessage packet) {
        client.getListenerHandler().onPluginMessage( packet );
    }

    public void handle(Packet00KeepAlive packet) {
        client.sendPacket( packet );
    }

    public void handle(Packet01LoginRequest packet) {
        client.player = new PlayerImpl( packet.getEntityId(), client );
        client.getListenerHandler().onServerInfo(new ServerInfo(packet.getEntityId(), packet.getDifficulty(), packet.getDimention(),
                packet.getGameMode(), packet.getLevelType(), packet.getMaxPlayers()));
        client.getListenerHandler().onConnected();
        client.setProtocolStatus( ProtocolStatus.Done );
    }

    public void handle(Packet06SpawnPosition packet) {

    }

    public void handle(Packet09Respawn packet) {
        client.getListenerHandler().onRespawn( packet );
    }

    public void handle(Packet0BPlayerPosition packet) {

    }

    public void handle(Packet0DPositionAndLook packet) {
        client.player.setLocation(packet.getZ(), packet.getY(), packet.getZ(), packet.getStance(), packet.getYaw(),
                packet.getPitch(), packet.isOnGround());
        // Packet0BPlayerPosition playerPosition = new Packet0BPlayerPosition( packet.getX(), packet.getY(), packet.getZ(), packet.getStance(), packet.isOnGround() );
        //client.sendPacket( new Packet06SpawnPosition( packet.getX(), packet.getY(), packet.getZ() ).getPacket( Unpooled.buffer() ), ctx );
        //client.sendPacket( packet.getPacket( Unpooled.buffer() ), ctx  );
    }

    public void handle(PacketD1Team packet) {
        client.getListenerHandler().onTeamPacket( packet );
    }

    public void handle(Packet03Chat packet) {
        try {
            String message = packet.getMessage().getString( "text" );
            if ( message.contains( "TheUnnamedDude logg av" ) ) {
                client.shutdown();
            }
            client.getListenerHandler().onChat( packet.getMessage() );
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void handle(PacketC9PlayerListItem packet) {
        client.pingLock.writeLock().lock();
        try {
            if ( packet.isOnline() ) {
                client.pingMap.put( packet.getName(), packet.getPing() );
            } else {
                client.pingMap.remove( packet.getName() );
            }
        } finally {
            client.pingLock.writeLock().unlock();
        }
        client.getListenerHandler().onPing();
    }
}
