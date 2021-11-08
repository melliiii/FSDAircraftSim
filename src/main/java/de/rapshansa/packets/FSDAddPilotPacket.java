package de.rapshansa.packets;

/**
 * Adds a pilot to the server / login
 * @author Melli
 * @version 1.0
 * @see FSDPacket
 */
public class FSDAddPilotPacket implements FSDPacket{

    static final String command = "#AP";
    private String[] params;

    /**
     * Creates Packet with params
     * @param params Fields: MSG FROM, MSG TO, CERTIFICATE ID ,PASSWORD, RATING (num between 0 and 5), PROTOCOL REVISION LETTER, SIM LETTER
     */
    public FSDAddPilotPacket(String[] params){
        this.params = params;
    }

    /**
     * Sets params for packet
     * @param params Fields: MSG FROM, MSG TO, CERTIFICATE ID ,PASSWORD, RATING (num between 0 and 5), PROTOCOL REVISION LETTER, SIM LETTER
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
        StringBuilder line = new StringBuilder(command);
        for(int i = 0; i < params.length; i++){
            if(i != 0){
                line.append(":");
            }
            line.append(params[i]);
        }
        line.append("\r\n");
        return line.toString();
    }
}
