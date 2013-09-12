package net.theunnameddude.mcclient.protocol.values;

import io.netty.buffer.ByteBuf;

public class UnsignedByteReader extends ValueReader<Short> {

    public UnsignedByteReader(ValueReader parent) {
        super( parent );
    }

    public UnsignedByteReader() {
        super();
    }

    @Override
    Short read(ByteBuf buf) {
        return buf.readUnsignedByte();
    }
}
