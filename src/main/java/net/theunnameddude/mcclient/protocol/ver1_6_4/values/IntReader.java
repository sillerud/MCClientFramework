package net.theunnameddude.mcclient.protocol.ver1_6_4.values;

import io.netty.buffer.ByteBuf;

public class IntReader extends ValueReader<Integer> {
    public IntReader(ValueReader parent) {
        super( parent );
    }

    public IntReader() {
        super();
    }

    @Override
    public Integer read(ByteBuf buf) {
        return buf.readInt();
    }
}
