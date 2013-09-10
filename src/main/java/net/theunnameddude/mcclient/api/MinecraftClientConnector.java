package net.theunnameddude.mcclient.api;

import net.theunnameddude.mcclient.api.auth.AuthenticationResponse;
import net.theunnameddude.mcclient.client.MinecraftClientImpl;

public class MinecraftClientConnector {
    public static MinecraftClient connect(String host, int port, AuthenticationResponse auth) {
        MinecraftClientImpl client = new MinecraftClientImpl();
        client.connect( host, port, auth );
        return client;
    }
}
