package net.theunnameddude.mcclient.protocol.ver1_6_4.values;

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
