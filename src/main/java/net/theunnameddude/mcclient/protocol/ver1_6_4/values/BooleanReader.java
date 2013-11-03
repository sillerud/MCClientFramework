package net.theunnameddude.mcclient.protocol.ver1_6_4.values;

import io.netty.buffer.ByteBuf;

public class BooleanReader extends ValueReader<Boolean> {

    public BooleanReader(ValueReader parent) {
        super( parent );
    }

    public BooleanReader() {
        super();
    }

    @Override
    Boolean read(ByteBuf buf) {
        return buf.readBoolean();
    }
}
