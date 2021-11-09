package de.rapshansa.packets;

public class FSDPositionUpdatePacket implements FSDPacket{


    static final String command = "@";
    private String[] params;

    /**
     * Creates Packet with params
     * @param params Fields: TCAS Mode (S = Standby, N = Mode C, Y = Squawk Ident), Callsign, Squawk, Rating, lat, lon, altitude, groundspeed,
     */
    public FSDPositionUpdatePacket(String[] params){
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
