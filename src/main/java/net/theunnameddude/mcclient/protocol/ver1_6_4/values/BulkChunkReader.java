package net.theunnameddude.mcclient.protocol.ver1_6_4.values;

import io.netty.buffer.ByteBuf;

public class BulkChunkReader extends ValueReader {
    @Override
    Object read(ByteBuf buf) {
        short count = buf.readShort();
        int size = buf.readInt();
        buf.readBoolean();
        buf.skipBytes( size + count * 12 );
        return null;
    }
}
