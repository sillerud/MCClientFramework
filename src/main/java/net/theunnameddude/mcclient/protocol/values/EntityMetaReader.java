package net.theunnameddude.mcclient.protocol.values;

import io.netty.buffer.ByteBuf;

public class EntityMetaReader extends ValueReader {
    StringReader STRING = new StringReader();
    ItemReader ITEM = new ItemReader();
    @Override
    Object read(ByteBuf buf) {
        int itemId;
        while ( (itemId = buf.readByte()) != 127 ) {
            int index = itemId & 0x1F;
            int type = itemId >> 5;
            if (type == 0) buf.readByte();
            if (type == 1) buf.readShort();
            if (type == 2) buf.readInt();
            if (type == 3) buf.readFloat();
            if (type == 4) STRING.read( buf );
            if (type == 5) ITEM.read( buf );
            if (type == 6) {
                buf.readInt();
                buf.readInt();
                buf.readInt();
            }
        }
        return null;
    }
}
