package de.rapshansa;

import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;

public class FSDAircraftSim {
    public static void main(String[] args) throws MalformedURLException {
        FSDClient fsdClient = new FSDClient("", "", new InetSocketAddress("127.0.0.1", 6809));
    }
}
