// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class BlockSign extends BlockContainer {
	// FIELDS
	private java.lang.Class field_654;
	private boolean field_653;
	
	// METHODS
	
	/**
	 * 
	 */
	public abstract boolean a();
	
	/**
	 * 
	 */
	public abstract int a(int a, java.util.Random b);
	
	/**
	 * 
	 */
	public abstract void a(IBlockAccess a, int b, int c, int d);
	
	/**
	 * 
	 */
	protected abstract TileEntity a_();
	
	/**
	 * 
	 */
	public abstract void b(World a, int b, int c, int d, int e);
	
	/**
	 * 
	 */
	public abstract AxisAlignedBB d(World a, int b, int c, int d);

}