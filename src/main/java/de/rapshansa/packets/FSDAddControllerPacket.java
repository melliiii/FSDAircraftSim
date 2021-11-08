package de.rapshansa.packets;

/**
 * USED TO ADD A CONTROLLER TO THE SYSTEM
 * @author Melli
 * @version 1.0
 * @see FSDPacket
 */
public class FSDAddControllerPacket implements FSDPacket{

    static final String command = "#AA";
    private final String[] params;

    /**
     * Creates Packet with params
     * @param params Fields: MSG FROM, MSG TO, REAL NAME, CERTIFICATE ID, PASSWORD, RATING (num between 0 and 12), PROTOCOL REVISION LETTER
     */
    public FSDAddControllerPacket(String[] params){
        this.params = params;
    }

    /**
     * Sets params for packet
     * @param params Fields: MSG FROM, MSG TO, REAL NAME, CERTIFICATE ID, PASSWORD, RATING (num between 0 and 12), PROTOCOL REVISION LETTER
     */
    @Override
    public FSDPacket setParams(String[] params) {
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
