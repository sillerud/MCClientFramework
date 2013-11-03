package net.theunnameddude.mcclient.api;

import net.theunnameddude.mcclient.protocol.PacketConstructor;
import net.theunnameddude.mcclient.protocol.ver1_6_4.PacketConstructor1_6_4;
import net.theunnameddude.mcclient.protocol.ver1_7_2.PacketConstructor1_7_2;

public class Version {
    public static PacketConstructor get1_6_4() {
        return new PacketConstructor1_6_4();
    }
    public static PacketConstructor get1_7_2() {
        return new PacketConstructor1_7_2();
    }
}
