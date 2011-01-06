// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class EntityFlying extends BlockStep{
	// FIELDS
	public double a;
	public double b;
	public double c;
	public double d;
	public float e;
	public float f;
	public boolean g;
	public boolean h;
	public boolean i;
	
	// METHODS
	
	/**
	 * Abstract. Return the size of the packet (not counting the header).
	 */
	public int getPacketSize()
	
	/**
	 * Passes this Packet on to the NetHandler for processing.
	 */
	public void processPacket(Packet28 a)
	
	/**
	 * Abstract. Reads the raw packet data from the data stream.
	 */
	public void readPacketData(java.io.DataInputStream a)
	
	/**
	 * Abstract. Writes the raw packet data to the data stream.
	 */
	public void writePacketData(java.io.DataOutputStream a)

}
