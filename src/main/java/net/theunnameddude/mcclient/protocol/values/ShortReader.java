package net.theunnameddude.mcclient.protocol.values;

import io.netty.buffer.ByteBuf;

public class ShortReader extends ValueReader<Short> {

    public ShortReader(ValueReader parent) {
        super(parent);
    }

    public ShortReader() {
        super();
    }

    @Override
    Short read(ByteBuf buf) {
        return buf.readShort();
    }
}
