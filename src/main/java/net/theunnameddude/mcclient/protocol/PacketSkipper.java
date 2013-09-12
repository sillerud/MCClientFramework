package net.theunnameddude.mcclient.protocol;

import net.theunnameddude.mcclient.protocol.values.*;

public class PacketSkipper {
    public static final ValueReader BOOLEAN = new BooleanReader();


    public static final ValueReader BYTE_INT = new IntReader( new ByteReader() );
    public static final ValueReader INT = new IntReader();

    public static final ValueReader INT_UNSIGNEDBYTE = new UnsignedByteReader( new IntReader() );

    public static final ValueReader BYTE = new ByteReader();
    public static final ValueReader INT_BYTE = new ByteReader( new IntReader() );
    public static final ValueReader SHORT_BYTE = new ByteReader( new ShortReader() );
    public static final ValueReader UNSIGNEDSHORT_BYTE = new ByteReader( new UnsignedShortReader() );

    public static final ValueReader UNSIGNEDSHORT = new UnsignedShortReader();
    public static final ValueReader SHORT = new ShortReader();

    public static final ValueReader STRING = new StringReader();
    public static final ValueReader SHORT_STRING = new StringReader( new ShortReader() );

    public static final ValueReader LONG = new LongReader();

    public static final ValueReader FLOAT = new FloatReader();

    public static final ValueReader ITEM = new ItemReader();
    public static final ValueReader SHORT_ITEM = new ItemReader( new ShortReader() );

    public static final ValueReader OPTIONALWINDOW = new OptionalWindowReader();
    public static final ValueReader ENTITYMETA = new EntityMetaReader();
    public static final ValueReader ENTITYPROPERTIES = new EntityPropertiesReader();
    public static final ValueReader BULKCHUNK = new BulkChunkReader();
    public static final ValueReader DOUBLE = new DoubleReader();

    public static ValueReader[][] packets = new ValueReader[0xFF][];
    static {
        // Time update
        packets[ 0x04 ] = toPacket( LONG, LONG );

        // Entity equipment
        packets[ 0x05 ] = toPacket( INT, SHORT, ITEM );

        // Update health
        packets[ 0x08 ] = toPacket( FLOAT, SHORT, FLOAT );

        // Player
        packets[ 0x0A ] = toPacket( BOOLEAN );

        // Player digging
        packets[ 0x0E ] = toPacket( BYTE, INT, BYTE, INT, BYTE );

        // Item held change
        packets[ 0x10 ] = toPacket( SHORT );

        // Use bed
        packets[ 0x11 ] = toPacket( INT, BYTE, INT, BYTE, INT );

        // Animation
        packets[ 0x12 ] = toPacket( INT, BYTE );

        // Spawn named entity
        packets[ 0x14 ] = toPacket( INT, STRING, INT, INT, INT, BYTE, BYTE, SHORT, ENTITYMETA );

        // Collect item
        packets[ 0x16 ] = toPacket( INT, INT );

        // Spawn object/vehicle
        packets[ 0x17 ] = toPacket( INT, BYTE, INT, INT, INT, BYTE, BYTE, INT_BYTE );

        // Spawn mob
        packets[ 0x18 ] = toPacket( INT, BYTE, INT, INT, INT, BYTE, BYTE, BYTE, SHORT, SHORT, SHORT, ENTITYMETA );

        // Spawn painting
        packets[ 0x19 ] = toPacket( INT, STRING, INT, INT, INT, INT );

        // Spawn exp orb
        packets[ 0x1A ] = toPacket( INT, INT, INT, INT, SHORT );

        // Entity velocity
        packets[ 0x1C ] = toPacket( INT, SHORT, SHORT, SHORT );

        // Destroy entities
        packets[ 0x1D ] = toPacket( BYTE_INT );

        // Entity relative move
        packets[ 0x1F ] = toPacket( INT, BYTE, BYTE, BYTE );

        // Entity look
        packets[ 0x20 ] = toPacket( INT, BYTE, BYTE );

        // Entity look and relative move
        packets[ 0x21 ] = toPacket( INT, BYTE, BYTE, BYTE, BYTE, BYTE );

        // Entity teleport
        packets[ 0x22 ] = toPacket( INT, INT, INT, INT, BYTE, BYTE );

        // Entity look
        packets[ 0x23 ] = toPacket( INT, BYTE );

        // Entity status
        packets[ 0x26 ] = toPacket( INT, BYTE );

        // Attach entity
        packets[ 0x27 ] = toPacket( INT, INT, BOOLEAN );

        // Entity metadata
        packets[ 0x28 ] = toPacket( INT, ENTITYMETA );

        // Entity effect
        packets[ 0x29 ] = toPacket( INT, BYTE, BYTE, SHORT );

        // Entity properties
        packets[ 0x2C ] = toPacket( INT, ENTITYPROPERTIES );

        // Remove entity effect
        packets[ 0x2A ] = toPacket( INT, BYTE );

        // Set experience
        packets[ 0x2B ] = toPacket( FLOAT, SHORT, SHORT );

        // Chunk data
        packets[ 0x33 ] = toPacket( INT, INT, BOOLEAN, UNSIGNEDSHORT, UNSIGNEDSHORT, INT_UNSIGNEDBYTE );

        // Multi block change
        packets[ 0x34 ] = toPacket( INT, INT, SHORT, INT_BYTE );

        // Block change
        packets[ 0x35 ] = toPacket( INT, BYTE, INT, SHORT, BYTE );

        // Block action
        packets[ 0x36 ] = toPacket( INT, SHORT, INT, SHORT, BYTE );

        // Block break animation
        packets[ 0x37 ] = toPacket( INT, INT, INT, INT, BYTE );

        // Read bulk chunk
        packets[ 0x38 ] = toPacket( BULKCHUNK );

        // Explosion
        packets[ 0x3C ] = toPacket( DOUBLE, DOUBLE, DOUBLE, FLOAT, FLOAT, INT, INT, INT, FLOAT, FLOAT, FLOAT );

        // Sound or particle effect
        packets[ 0x3D ] = toPacket( INT, INT, BYTE, INT, INT, BOOLEAN );

        // Named sound effect
        packets[ 0x3E ] = toPacket( STRING, INT, INT, INT, FLOAT, BYTE );

        // Particle
        packets[ 0x3F ] = toPacket( STRING, FLOAT, FLOAT, FLOAT, FLOAT, FLOAT, FLOAT, FLOAT, INT );

        // Game state change
        packets[ 0x46 ] = toPacket( BYTE, BYTE );

        // Thunderbolt
        packets[ 0x47 ] = toPacket( INT, INT, INT, BYTE, INT );

        // Open window
        packets[ 0x64 ] = toPacket( OPTIONALWINDOW );

        // Close window
        packets[ 0x65 ] = toPacket( BYTE );

        // Click window
        packets[ 0x66 ] = toPacket( BYTE, SHORT, BYTE, SHORT, BOOLEAN, ITEM );

        // Set item
        packets[ 0x67 ] = toPacket( BYTE, SHORT, ITEM );

        // Window
        packets[ 0x68 ] = toPacket( BYTE, SHORT_ITEM );

        // Update window property
        packets[ 0x69 ] = toPacket( BYTE, SHORT, SHORT );

        // Confirm transaction
        packets[ 0x6A ] = toPacket( BYTE, SHORT, BOOLEAN );

        // Creative inventory action
        packets[ 0x6B ] = toPacket( SHORT, ITEM );

        // Update sign
        packets[ 0x82 ] = toPacket( INT, SHORT, INT, STRING, STRING, STRING, STRING );

        // Item data
        packets[ 0x83 ] = toPacket( SHORT, SHORT, UNSIGNEDSHORT_BYTE );

        // Update tileentity
        packets[ 0x84 ] = toPacket( INT, SHORT, INT, BYTE, SHORT_BYTE );

        // Tile editor open
        packets[ 0x85 ] = toPacket( BYTE, INT, INT, INT );

        // Wut, not documented? D:
        packets[ 0xC3 ] = toPacket( SHORT, SHORT, INT_BYTE );

        // Increment statistic
        packets[ 0xC8 ] = toPacket( INT, INT );

        // Player abilities
        packets[ 0xCA ] = toPacket( BYTE, FLOAT, FLOAT );

        // Scoreboard objective
        packets[ 0xCE ] = toPacket( STRING, STRING, BYTE );

        // Update score
        packets[ 0xCF ] = toPacket( STRING, BYTE, STRING, INT );

        // Display scoreboard
        packets[ 0xD0 ] = toPacket( BYTE, STRING );

        // Team
        packets[ 0xD1 ] = toPacket( STRING, BYTE, STRING, STRING, STRING, BYTE, SHORT_STRING );
    }

    public static ValueReader[] toPacket( ValueReader... readers ) {
        return readers;
    }
}
