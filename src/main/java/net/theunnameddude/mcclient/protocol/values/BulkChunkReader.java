package net.theunnameddude.mcclient.protocol.values;

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
