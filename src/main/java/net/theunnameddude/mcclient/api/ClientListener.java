package net.theunnameddude.mcclient.api;

import net.theunnameddude.mcclient.client.ServerInfo;
import net.theunnameddude.mcclient.protocol.packets.Packet09Respawn;
import net.theunnameddude.mcclient.protocol.packets.PacketD1Team;
import net.theunnameddude.mcclient.protocol.packets.PacketFAPluginMessage;
import org.json.JSONObject;

public abstract class ClientListener {

    public void onPing() {

    }

    public void onDisconnect() {

    }

    public void onDisconnected() {

    }

    public void onAuthComplete() {

    }

    public void onKick(String reason) {

    }

    public void onTeamPacket(PacketD1Team packet) {

    }

    public void onChat(JSONObject message) {

    }

    public void onPluginMessage(PacketFAPluginMessage packet) {

    }

    public void onRespawn(Packet09Respawn packet) {

    }

    public void onConnected() {

    }

    public void onAuthFail(String response) {

    }

    public void onServerInfo(ServerInfo info) {

    }

    public void onStatusChange(ProtocolStatus status) {

    }
}
