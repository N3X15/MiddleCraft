// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class Packet51MapChunk extends Packet {
	// FIELDS
	public int xPosition;
	public int yPosition;
	public int zPosition;
	public int xSize;
	public int ySize;
	public int zSize;
	public byte[] chunk;
	private int chunkSize;
	
	// METHODS
	
	/**
	 * 
	 */
	public abstract int MIDDLECRAFT_func_289_a();
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_288_a(NetHandler a);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_286_a(java.io.DataInputStream a);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_287_a(java.io.DataOutputStream a);

}
