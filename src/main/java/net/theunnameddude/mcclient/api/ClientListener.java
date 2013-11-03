package net.theunnameddude.mcclient.api;

import net.theunnameddude.mcclient.client.ServerInfo;
import net.theunnameddude.mcclient.protocol.base.PacketPluginMessageBase;
import net.theunnameddude.mcclient.protocol.base.PacketRespawnBase;
import net.theunnameddude.mcclient.protocol.base.PacketTeamBase;
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

    public void onTeamPacket(PacketTeamBase packet) {

    }

    public void onChat(JSONObject message) {

    }

    public void onPluginMessage(PacketPluginMessageBase packet) {

    }

    public void onRespawn(PacketRespawnBase packet) {

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