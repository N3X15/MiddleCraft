// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class Packet50PreChunk extends Packet {
	// FIELDS
	public int xPosition;
	public int yPosition;
	public boolean mode;
	
	// METHODS
	
	/**
	 * 
	 */
	public abstract int MIDDLECRAFT_func_296_a();
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_295_a(NetHandler a);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_293_a(java.io.DataInputStream a);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_294_a(java.io.DataOutputStream a);

}
