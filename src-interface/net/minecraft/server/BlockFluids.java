// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class BlockFluids extends Block {
	// FIELDS
	
	// METHODS
	
	/**
	 * 
	 */
	public abstract boolean a();
	
	/**
	 * 
	 */
	public abstract int a(int a);
	
	/**
	 * 
	 */
	public abstract int a(int a, java.util.Random b);
	
	/**
	 * 
	 */
	public abstract boolean a(int a, boolean b);
	
	/**
	 * 
	 */
	public abstract void a(World a, int b, int c, int d, Entity e, Vec3D f);
	
	/**
	 * 
	 */
	public abstract void a(World a, int b, int c, int d, java.util.Random e);
	
	/**
	 * 
	 */
	public abstract int a(java.util.Random a);
	
	/**
	 * 
	 */
	public abstract boolean a(IBlockAccess a, int b, int c, int d, int e);
	
	/**
	 * 
	 */
	public abstract int b();
	
	/**
	 * 
	 */
	public abstract void b(World a, int b, int c, int d, int e);
	
	/**
	 * 
	 */
	protected abstract int b(IBlockAccess a, int b, int c, int d);
	
	/**
	 * 
	 */
	public abstract AxisAlignedBB d(World a, int b, int c, int d);
	
	/**
	 * 
	 */
	public abstract void e(World a, int b, int c, int d);
	
	/**
	 * 
	 */
	protected abstract int g(World a, int b, int c, int d);
	
	/**
	 * 
	 */
	protected abstract void h(World a, int b, int c, int d);

}
