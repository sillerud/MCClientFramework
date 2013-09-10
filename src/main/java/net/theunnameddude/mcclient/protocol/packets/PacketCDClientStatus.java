package net.theunnameddude.mcclient.protocol.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.theunnameddude.mcclient.protocol.PacketHandler;

public class PacketCDClientStatus extends BasePacket {

    byte payload;

    public PacketCDClientStatus() {
        super( 0xCD );
        payload = 0;
    }

    public PacketCDClientStatus( byte payload ) {
        this();
        this.payload = payload;
    }

    @Override
    public ByteBuf getPacket(ByteBuf buf) {
        buf.writeByte( payload );
        return buf;
    }

    @Override
    public void onPacket(ByteBuf buf) {
        payload = buf.readByte();
    }

    @Override
    public void handle(PacketHandler handler, ChannelHandlerContext ctx) {
        handler.handle( this );
    }
}
