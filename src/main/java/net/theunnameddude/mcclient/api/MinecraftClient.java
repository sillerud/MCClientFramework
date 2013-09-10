package net.theunnameddude.mcclient.api;

import net.theunnameddude.mcclient.api.auth.AuthenticationResponse;
import net.theunnameddude.mcclient.protocol.packets.BasePacket;

public interface MinecraftClient {
    public Player getPlayer();
    public short getPing(String user);
    public void sendPacket(BasePacket packet);
    public AuthenticationResponse getAuth();
    public void shutdown();
    public int getPort();
    public String[] getUsers();
    public void connect(String host, int port, AuthenticationResponse auth);
    public void addListener(ClientListener listener);
    public void sendMessage(String message);
    public ProtocolStatus getProtocolStatus();
}
