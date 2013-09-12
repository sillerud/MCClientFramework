package net.theunnameddude.mcclient.protocol.values;

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
