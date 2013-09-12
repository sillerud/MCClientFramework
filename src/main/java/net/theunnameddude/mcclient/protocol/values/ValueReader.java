package net.theunnameddude.mcclient.protocol.values;

import io.netty.buffer.ByteBuf;

public abstract class ValueReader<T extends Object> {
    ValueReader<T> parent;
    public ValueReader(ValueReader<T> parent) {
        this.parent = parent;
    }

    public void handle(ByteBuf buf) {
        if ( parent == null ) {
            read( buf );
        } else {
            T value = parent.read( buf );
            for ( int i = 0; i < ((Integer)value); i++ ) {
                read( buf );
            }
        }
    }

    abstract T read(ByteBuf buf);
}
