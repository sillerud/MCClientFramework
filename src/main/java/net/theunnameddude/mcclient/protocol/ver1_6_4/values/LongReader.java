package net.theunnameddude.mcclient.protocol.ver1_6_4.values;

import io.netty.buffer.ByteBuf;

public class LongReader extends ValueReader<Long> {

    public LongReader(ValueReader parent) {
        super(parent);
    }

    public LongReader() {
        super();
    }

    @Override
    Long read(ByteBuf buf) {
        return buf.readLong();
    }
}
