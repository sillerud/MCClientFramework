package net.theunnameddude.mcclient.protocol.values;

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
