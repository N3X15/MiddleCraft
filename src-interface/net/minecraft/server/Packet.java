// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class Packet {
	// FIELDS
	private static java.util.Map packetIdToClassMap;
	private static java.util.Map packetClassToIdMap;
	public long field_20009;
	public boolean isChunkDataPacket;
	
	// METHODS
	
	/**
	 * 
	 */
	public abstract int a();
	
	/**
	 * 
	 */
	public abstract void a(NetHandler a);
	
	/**
	 * 
	 */
	public abstract void a(java.io.DataInputStream a);
	
	/**
	 * 
	 */
	public abstract void a(java.io.DataOutputStream a);
	
	/**
	 * 
	 */
	public abstract int b();

}
