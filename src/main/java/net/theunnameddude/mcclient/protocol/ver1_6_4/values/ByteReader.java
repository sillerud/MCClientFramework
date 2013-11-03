package net.theunnameddude.mcclient.protocol.ver1_6_4.values;

import io.netty.buffer.ByteBuf;

public class ByteReader extends ValueReader<Byte> {

    public ByteReader(ValueReader parent) {
        super(parent);
    }

    public ByteReader() {
        super();
    }

    @Override
    Byte read(ByteBuf buf) {
        return buf.readByte();
    }
}
