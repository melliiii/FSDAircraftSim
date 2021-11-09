package de.rapshansa.packets;

/**
 * Removes Controller from the Server / Logout
 * @author Melli
 * @version 1.0
 * @see FSDPacket
 */
public class FSDRemoveController implements FSDPacket{

    static final String command = "#DA";
    private String[] params;

    /**
     * Creates Packet with params
     * @param params Fields: CALLSIGN
     */
    public FSDRemoveController(String[] params){
        this.params = params;
    }

    /**
     * Sets params for packet
     * @param params Fields: CALLSIGN
     */
    @Override
    public FSDPacket setParams(String[] params) {
        this.params = params;
        return this;
    }

    /**
     * Creates a packet
     * @return packet as string
     */
    @Override
    public String asString() {
        return command + params[0] + ":" + params[1] + "\r\n";
    }
}
