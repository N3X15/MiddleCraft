// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class WorldGenMinable{
	// FIELDS
	private int a;
	private int b;
	private kx[][] c;
	private Packet17AddToInventory d;
	
	// METHODS
	
	/**
	 * Returns the block ID at coords x,y,z
	 */
	public int getBlockId(int a, int b, int c)
	
	/**
	 * Returns the block metadata at coords x,y,z
	 */
	public int getBlockMetadata(int a, int b, int c)
	
	/**
	 * Returns the block's material.
	 */
	public la getBlockMaterial(int a, int b, int c)
	
	/**
	 * Returns if the block at the specified coordinates allow attachment Args: x, y, z
	 */
	public boolean doesBlockAllowAttachment(int a, int b, int c)

}
