// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class Packet20NamedEntitySpawn extends Packet {
	// FIELDS
	public int entityId;
	public java.lang.String name;
	public int xPosition;
	public int yPosition;
	public int zPosition;
	public byte rotation;
	public byte pitch;
	public int currentItem;
	
	// METHODS
	
	/**
	 * 
	 */
	public abstract int MIDDLECRAFT_func_209_a();
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_208_a(NetHandler a);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_206_a(java.io.DataInputStream a);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_207_a(java.io.DataOutputStream a);

}
