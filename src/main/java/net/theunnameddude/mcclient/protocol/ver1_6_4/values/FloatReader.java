package net.theunnameddude.mcclient.protocol.ver1_6_4.values;

import io.netty.buffer.ByteBuf;

public class FloatReader extends ValueReader<Float> {

    public FloatReader(ValueReader parent) {
        super(parent);
    }

    public FloatReader() {
        super();
    }

    @Override
    Float read(ByteBuf buf) {
        return buf.readFloat();
    }
}
