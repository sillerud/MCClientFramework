package net.theunnameddude.mcclient.protocol;

import io.netty.buffer.ByteBuf;

public interface IPacketInstruction {
    public void read(ByteBuf buf);
}
