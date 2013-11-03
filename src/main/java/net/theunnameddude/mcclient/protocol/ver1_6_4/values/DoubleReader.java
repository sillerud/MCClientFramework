package net.theunnameddude.mcclient.protocol.ver1_6_4.values;

import io.netty.buffer.ByteBuf;

public class DoubleReader extends ValueReader<Double> {
    @Override
    Double read(ByteBuf buf) {
        return buf.readDouble();
    }
}
