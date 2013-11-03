package net.theunnameddude.mcclient.protocol;

import net.theunnameddude.mcclient.protocol.base.*;

public abstract class PacketHandler {
    public abstract void handle(PacketEncryptionResponseBase packet);
    public abstract void handle(PacketEncryptionRequestBase packet);
    public abstract void handle(PacketKickBase packet);
    public abstract void handle(PacketClientStatusBase packet);
    public abstract void handle(PacketPluginMessageBase packet);
    public abstract void handle(PacketKeepAliveBase packet);
    public abstract void handle(PacketLoginRequestBase packet);
    public abstract void handle(PacketSpawnPositionBase packet);
    public abstract void handle(PacketRespawnBase packet);
    public abstract void handle(PacketPlayerPositionBase packet);
    public abstract void handle(PacketPositionAndLookBase packet);
    public abstract void handle(PacketTeamBase packet);
    public abstract void handle(PacketChatBase packet);
    public abstract void handle(PacketPlayerListItemBase packet);
}
