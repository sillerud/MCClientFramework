package net.theunnameddude.mcclient.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import net.theunnameddude.mcclient.api.ClientListener;
import net.theunnameddude.mcclient.api.MinecraftClient;
import net.theunnameddude.mcclient.api.Player;
import net.theunnameddude.mcclient.api.ProtocolStatus;
import net.theunnameddude.mcclient.api.auth.AuthenticationResponse;
import net.theunnameddude.mcclient.netty.MinecraftPacketHandler;
import net.theunnameddude.mcclient.protocol.PacketConstructor;
import net.theunnameddude.mcclient.protocol.base.BasePacket;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MinecraftClientImpl implements MinecraftClient, Runnable {

    private SocketChannel channel;
    private MinecraftPacketHandler packetHandler;
    private ClientListenerHandler listenerHandler = new ClientListenerHandler();
    private AuthenticationResponse auth;
    private ProtocolStatus status = ProtocolStatus.NotStarted;
    public PlayerImpl player;
    public ReentrantReadWriteLock pingLock = new ReentrantReadWriteLock();
    public HashMap<String, Short> pingMap = new HashMap<String, Short>();
    public int port;
    public String host;
    public PacketConstructor pc;

    @Override
    public void connect(String host, int port, AuthenticationResponse auth, PacketConstructor version) {
        this.port = port;
        this.host = host;
        this.auth = auth;
        this.pc = version;
        new Thread( this, "MinecraftClientThread" ).start();
    }

    @Override
    public AuthenticationResponse getAuth() {
        return auth;
    }

    public SocketChannel getChannel() {
        return channel;
    }

    public ClientListenerHandler getListenerHandler() {
        return listenerHandler;
    }

    @Override
    public void sendPacket(BasePacket packet) {
        getChannel().writeAndFlush( packet );
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public short getPing( String user ) {
        pingLock.readLock().lock();
        try {
            Short ping = pingMap.get( user );
            return ping == null ? 9999 : ping;
        } finally {
            pingLock.readLock().unlock();
        }
    }

    @Override
    public String[] getUsers() {
        pingLock.readLock().lock();
        try {
            Set<String> users = pingMap.keySet();
            return users.toArray( new String[ users.size() ] );
        } finally {
            pingLock.readLock().unlock();
        }
    }

    public class MinecraftChannelInitializer extends ChannelInitializer<SocketChannel> {
        MinecraftPacketHandler packetHandler;
        MinecraftClientImpl client;
        PacketConstructor version;
        public MinecraftChannelInitializer(MinecraftPacketHandler packetHandler, MinecraftClientImpl client, PacketConstructor version) {
            this.packetHandler = packetHandler;
            this.client = client;
            this.version = version;
        }
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast( "timeout", new ReadTimeoutHandler( 100000, TimeUnit.MILLISECONDS ) );
            ch.pipeline().addLast( "packet-decoder", version.createPacketDecoder() );
            ch.pipeline().addLast( "packet-encoder", version.createPacketEncoder() );
            ch.pipeline().addLast( "mc-packet-handler",  packetHandler );
            client.channel = ch;
        }
    }

    public void shutdown() {
        pc.disconnect( this );
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public void run() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            packetHandler = new MinecraftPacketHandler( this, host );
            Bootstrap b = new Bootstrap();
            b.channel(NioSocketChannel.class)
                    .group( group )
                    .option( ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000 )
                    .handler(new MinecraftChannelInitializer( packetHandler, this, pc ) )
                    .connect( host, port ).channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                group.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        getListenerHandler().onDisconnected();
    }

    @Override
    public void addListener(ClientListener listener) {
        getListenerHandler().addListener( listener );
    }

    @Override
    public void sendMessage(String message) {
        try {
            sendPacket( pc.packetChat( new JSONObject().put( "text", message ) ) );
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setProtocolStatus(ProtocolStatus status) {
        this.status = status;
        getListenerHandler().onStatusChange( status );
    }

    @Override
    public ProtocolStatus getProtocolStatus() {
        return status;
    }
}
