package de.rapshansa;

import de.rapshansa.helpers.ClientState;
import de.rapshansa.packets.FSDPacket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class FSDClient {

    // User Defined Variables
    private final InetSocketAddress fsd_host;
    private final String vid;
    private final String password;

    // Program Variables
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private ClientState state = ClientState.NONE;


    // ## Constructor <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    /**
     * Creates Instance of FSDClient
     * @param vid VID/Username
     * @param password Password
     * @param fsd_host Host to connect to
     */
    public FSDClient(String vid, String password, InetSocketAddress fsd_host){
        this.vid = vid;
        this.password = password;
        this.fsd_host = fsd_host;
    }

    // ## Functions <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    /**
     * Connect Socket to FSD Server
     * @return Instance of FSDClient
     * @throws IOException Socket IOException
     */
    public FSDClient connect() throws IOException {
        socket = new Socket(fsd_host.getAddress(), fsd_host.getPort());
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream());
        state = ClientState.CONNECTED;
        return this;
    }

    /**
     * Sends Packet to the Server
     * @param packet Packet to be sent to server
     */
    public void sendPacket(FSDPacket packet){
        sendLine(packet.asString());
    }

    /**
     * Sends Packet to the Server
     * @param line Packet to be sent to server as String
     * @throws NullPointerException Writer and Socket have to be connected
     */
    public void sendLine(String line){
        if(socket == null || writer == null)
            throw new NullPointerException("Writer or Socket cant be null");

        writer.write(line);
        writer.flush();
    }

    // ## Getters <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    /**
     * @return FSD Host Url
     */
    public InetSocketAddress getFsd_host() {
        return fsd_host;
    }
    /**
     * @return User VID
     */
    public String getVid() {
        return vid;
    }
    /**
     * @return User Password
     */
    public String getPassword() {
        return password;
    }
    /**
     * @return Current State of the Client
     */
    public ClientState getState() {
        return state;
    }
}
