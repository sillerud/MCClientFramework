package net.theunnameddude.mcclient.protocol.values;

import io.netty.buffer.ByteBuf;

public class EntityPropertiesReader extends ValueReader {
    StringReader STRING = new StringReader();
    @Override
    Object read(ByteBuf buf) {
        int recordCount = buf.readInt();
        for ( int i = 0; i < recordCount; i++ ) {
            STRING.read( buf );
            buf.readDouble();
            short size = buf.readShort();
            for ( short s = 0; s < size; s++ ) {
                buf.skipBytes( 25 ); // long, long, double, byte - Almost directly stolen from md_5
            }
        }
        return null;
    }
}
