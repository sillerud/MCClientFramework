package net.theunnameddude.mcclient.protocol.values;

import io.netty.buffer.ByteBuf;

public class OptionalWindowReader extends ValueReader {
    StringReader STRING = new StringReader();
    @Override
    Object read(ByteBuf buf) {
        buf.readByte();
        byte type = buf.readByte();
        STRING.read( buf );
        buf.readByte();
        buf.readBoolean();
        if ( type == 11 ) {
            buf.readInt();
        }
        return null;
    }
}
