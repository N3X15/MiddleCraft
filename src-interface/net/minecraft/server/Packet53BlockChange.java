// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class Packet53BlockChange extends Packet {
	// FIELDS
	public int xPosition;
	public int yPosition;
	public int zPosition;
	public int type;
	public int metadata;
	
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

}
