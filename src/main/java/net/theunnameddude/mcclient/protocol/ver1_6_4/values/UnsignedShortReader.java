package net.theunnameddude.mcclient.protocol.ver1_6_4.values;

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
