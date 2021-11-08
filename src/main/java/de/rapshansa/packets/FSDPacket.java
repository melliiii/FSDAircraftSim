package de.rapshansa.packets;

/** Interface for Packets sent to FSD Servers
 * @author Melli
 * @version 1.0
 */
public interface FSDPacket {
    public FSDPacket setParams(String[] params);
    public String asString();
}
