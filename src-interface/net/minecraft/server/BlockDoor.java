// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class BlockDoor extends Block {
	// FIELDS
	
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
	public abstract boolean a(World a, int b, int c, int d);
	
	/**
	 * 
	 */
	public abstract MovingObjectPosition a(World a, int b, int c, int d, Vec3D e, Vec3D f);
	
	/**
	 * 
	 */
	public abstract boolean a(World a, int b, int c, int d, EntityPlayer e);
	
	/**
	 * 
	 */
	public abstract void a(World a, int b, int c, int d, boolean e);
	
	/**
	 * 
	 */
	public abstract void a(IBlockAccess a, int b, int c, int d);
	
	/**
	 * 
	 */
	public abstract void b(int a);
	
	/**
	 * 
	 */
	public abstract void b(World a, int b, int c, int d, int e);
	
	/**
	 * 
	 */
	public abstract void b(World a, int b, int c, int d, EntityPlayer e);
	
	/**
	 * 
	 */
	public abstract int d(int a);
	
	/**
	 * 
	 */
	public abstract AxisAlignedBB d(World a, int b, int c, int d);

}