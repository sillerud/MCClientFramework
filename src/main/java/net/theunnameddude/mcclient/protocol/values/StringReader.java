package net.theunnameddude.mcclient.protocol.values;

import io.netty.buffer.ByteBuf;

public class StringReader extends ValueReader<String> {

    public StringReader() {
        super();
    }

    public StringReader(ShortReader parent) {
        super( parent );
    }

    @Override
    String read(ByteBuf buf) {
        short length = buf.readShort();
        char[] chars = new char[ length ];
        for( int i = 0; i < chars.length; i++ ) {
            chars[ i ] = buf.readChar();
        }
        return new String( chars );
    }
}
