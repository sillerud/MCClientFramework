package net.theunnameddude.mcclient.api;

import net.theunnameddude.mcclient.api.auth.AuthenticationResponse;
import net.theunnameddude.mcclient.client.MinecraftClientImpl;
import net.theunnameddude.mcclient.protocol.PacketConstructor;

public class MinecraftClientConnector {
    public static MinecraftClient connect(String host, int port, AuthenticationResponse auth, PacketConstructor version) {
        MinecraftClientImpl client = new MinecraftClientImpl();
        client.connect( host, port, auth, version );
        return client;
    }
}
