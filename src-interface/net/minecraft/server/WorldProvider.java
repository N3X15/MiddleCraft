// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class WorldProvider {
	// FIELDS
	public World worldObj;
	public WorldChunkManager worldChunkMgr;
	public boolean field_6167;
	public boolean field_6166;
	public boolean field_4306;
	public float[] lightBrightnessTable;
	public int worldType;
	private float[] field_6164;
	
	// METHODS
	
	/**
	 * 
	 */
	protected abstract void a();
	
	/**
	 * 
	 */
	public abstract static WorldProvider a(int a);
	
	/**
	 * 
	 */
	public abstract boolean a(int a, int b);
	
	/**
	 * 
	 */
	public abstract float a(long a, float b);
	
	/**
	 * 
	 */
	public abstract void a(World a);
	
	/**
	 * 
	 */
	public abstract IChunkLoader a(java.io.File a);
	
	/**
	 * 
	 */
	protected abstract void b();
	
	/**
	 * 
	 */
	public abstract IChunkProvider c();

}
