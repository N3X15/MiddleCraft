// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class ChunkLoader {
	// FIELDS
	private java.io.File field_945;
	private boolean field_944;
	
	// METHODS
	
	/**
	 * 
	 */
	public abstract void a();
	
	/**
	 * 
	 */
	public abstract Chunk a(World a, int b, int c);
	
	/**
	 * 
	 */
	public abstract static Chunk a(World a, NBTTagCompound b);
	
	/**
	 * 
	 */
	public abstract void a(World a, Chunk b);
	
	/**
	 * 
	 */
	public abstract void a(Chunk a, World b, NBTTagCompound c);
	
	/**
	 * 
	 */
	public abstract void b();
	
	/**
	 * 
	 */
	public abstract void b(World a, Chunk b);

}
