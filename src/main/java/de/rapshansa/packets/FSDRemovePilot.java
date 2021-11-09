package de.rapshansa.packets;

/**
 * Removes pilot from the server / logout
 * @author Melli
 * @version 1.0
 * @see FSDPacket
 */
public class FSDRemovePilot implements FSDPacket{

    static final String command = "#DP";
    private String[] params;

    /**
     * Creates Packet with params
     * @param params Fields: CALLSIGN
     */
    public FSDRemovePilot(String[] params){
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
