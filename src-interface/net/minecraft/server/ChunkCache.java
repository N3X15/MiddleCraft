// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class ChunkCache {
	// FIELDS
	private int field_823;
	private int field_822;
	private Chunk[][] field_825;
	private World worldObj;
	
	// METHODS
	
	/**
	 * 
	 */
	public abstract int a(int a, int b, int c);
	
	/**
	 * 
	 */
	public abstract int b(int a, int b, int c);
	
	/**
	 * 
	 */
	public abstract Material c(int a, int b, int c);
	
	/**
	 * 
	 */
	public abstract boolean d(int a, int b, int c);

}