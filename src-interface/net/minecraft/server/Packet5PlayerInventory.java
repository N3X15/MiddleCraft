// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class Packet5PlayerInventory extends Packet {
	// FIELDS
	public int type;
	public int stacks;
	public int field_20028;
	
	// METHODS
	
	/**
	 * 
	 */
	public abstract int MIDDLECRAFT_func_1950_a();
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_1949_a(NetHandler a);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_1947_a(java.io.DataInputStream a);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_1948_a(java.io.DataOutputStream a);

}
