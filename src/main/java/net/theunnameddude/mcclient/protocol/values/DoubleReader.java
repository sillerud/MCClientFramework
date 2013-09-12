package net.theunnameddude.mcclient.protocol.values;

import io.netty.buffer.ByteBuf;

public class DoubleReader extends ValueReader<Double> {
    @Override
    Double read(ByteBuf buf) {
        return buf.readDouble();
    }
}
