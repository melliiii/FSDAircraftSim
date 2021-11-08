package de.rapshansa;

import de.rapshansa.packets.FSDAddPilotPacket;

import java.io.IOException;
import java.net.InetSocketAddress;

public class FSDAircraftSim {
    public static void main(String[] args) throws IOException {
        FSDClient fsdClient = new FSDClient("123456", "EinPasswort", new InetSocketAddress("127.0.0.1", 6809));
        fsdClient.connect();
        String[] params = {"RAH69M", "SERVER", "Melli", "111111", "password", "1", "9", "0"};
        fsdClient.sendPacket(new FSDAddPilotPacket(params));
    }
}
