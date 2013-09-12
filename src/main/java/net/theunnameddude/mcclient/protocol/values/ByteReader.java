package net.theunnameddude.mcclient.protocol.values;

import io.netty.buffer.ByteBuf;

public class ByteReader extends ValueReader<java.lang.Byte> {

    public Byte(ValueReader<java.lang.Byte> parent) {
        super(parent);
    }

    @Override
    java.lang.Byte read(ByteBuf buf) {
        return buf.readByte();
    }
}
