package net.theunnameddude.mcclient.protocol.values;

import io.netty.buffer.ByteBuf;

public abstract class ValueReader<T extends Object> {
    ValueReader parent;
    public ValueReader(ValueReader parent) {
        this.parent = parent;
    }

    public ValueReader() {
        this.parent = null;
    }

    public void handle(ByteBuf buf) {
        if ( parent == null ) {
            read( buf );
        } else {
            Object value = parent.read( buf );
            int count = 0;
            if ( value instanceof Integer ) count = (Integer)value;
            else if ( value instanceof Byte ) count = (Byte)value;
            else if ( value instanceof Short ) count = (Short)value;

            for ( int i = 0; i < count; i++ ) {
                read( buf );
            }
        }
    }

    abstract T read(ByteBuf buf);
}
