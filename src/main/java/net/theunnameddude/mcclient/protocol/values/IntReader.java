package net.theunnameddude.mcclient.protocol.values;

import io.netty.buffer.ByteBuf;

public class IntReader extends ValueReader<Integer> {
    public Int(ValueReader<Integer> parent) {
        super(parent);
    }

    @Override
    public Integer read(ByteBuf buf) {
        return buf.readInt();
    }
}
