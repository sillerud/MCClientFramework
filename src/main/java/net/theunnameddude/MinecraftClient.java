package net.theunnameddude;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import net.theunnameddude.auth.Authenticator;
import net.theunnameddude.netty.MinecraftPacketHandler;

public class MinecraftClient {
    public static void main(String[] args) throws InterruptedException {
        final Authenticator auth = new Authenticator("mojangemail", "password");
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.channel(NioSocketChannel.class)
                .group( group )
                .option( ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000 )
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast( new MinecraftPacketHandler( auth.sendRequest() ) );
                    }
                }).connect("localhost", 25565).channel().closeFuture().sync();

    }
}
