package net.theunnameddude.mcclient.protocol.values;

import io.netty.buffer.ByteBuf;

public class UnsignedShortReader extends ValueReader<Integer> {

    public UnsignedShortReader(ValueReader parent) {
        super( parent );
    }

    public UnsignedShortReader() {
        super();
    }

    @Override
    Integer read(ByteBuf buf) {
        return buf.readUnsignedShort();
    }
}
