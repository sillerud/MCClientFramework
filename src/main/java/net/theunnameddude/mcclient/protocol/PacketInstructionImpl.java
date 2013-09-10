package net.theunnameddude.mcclient.protocol;

import io.netty.buffer.ByteBuf;

public interface PacketInstructionImpl {
    public void read(ByteBuf buf);
}
