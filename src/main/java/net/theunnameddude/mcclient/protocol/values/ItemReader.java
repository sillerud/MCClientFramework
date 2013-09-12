package net.theunnameddude.mcclient.protocol.values;

import io.netty.buffer.ByteBuf;

public class ItemReader extends ValueReader<Object> {

    public ItemReader(ValueReader parent) {
        super(parent);
    }

    public ItemReader() {
        super();
    }

    @Override
    Object read(ByteBuf buf) {
        short itemId = buf.readShort();
        if ( itemId != -1 ) {
            buf.readByte();
            buf.readShort();
            short nbtLength = buf.readShort();
            if ( nbtLength != -1 ) {
                buf.skipBytes( nbtLength );
                //buf.readBytes( new byte[nbtLength] );
            }
        }
        return null;
    }
}
