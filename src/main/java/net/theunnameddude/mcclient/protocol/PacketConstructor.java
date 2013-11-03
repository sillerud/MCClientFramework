package net.theunnameddude.mcclient.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import net.theunnameddude.mcclient.client.MinecraftClientImpl;
import net.theunnameddude.mcclient.protocol.base.*;
import org.json.JSONObject;

public interface PacketConstructor {

    public void disconnect(MinecraftClientImpl client);

    public void handshake(String user, String host, int port, MinecraftClientImpl client);

    PacketClientStatusBase packetClientStatus();

    PacketEncryptionResponseBase packetEncryptionResponse(byte[] sharedKey, byte[] token);

    PacketChatBase packetChat(JSONObject message);

    PacketPositionAndLookBase packetPositionAndLook(double x, double y, double z, double stance, float yaw, float pitch, boolean onGround);

    public BasePacket readPacket(short packetId, ByteBuf buf);

    public ChannelHandler createPacketDecoder();

    public ChannelHandler createPacketEncoder();

    PacketHandler createPacketHandler(MinecraftClientImpl client);
}
