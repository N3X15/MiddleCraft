// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class ICommandListener extends EntityFlying{
	// FIELDS
	
	// METHODS
	
	/**
	 * Abstract. Return the size of the packet (not counting the header).
	 */
	public int getPacketSize()
	
	/**
	 * Abstract. Reads the raw packet data from the data stream.
	 */
	public void readPacketData(java.io.DataInputStream a)
	
	/**
	 * Abstract. Writes the raw packet data to the data stream.
	 */
	public void writePacketData(java.io.DataOutputStream a)

}
