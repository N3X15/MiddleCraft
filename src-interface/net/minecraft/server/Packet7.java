// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class Packet7 extends Packet {
	// FIELDS
	public int field_9019;
	public int prevHealth;
	public int field_9020;
	
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