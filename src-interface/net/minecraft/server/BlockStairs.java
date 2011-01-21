// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class BlockStairs extends Block {
	// FIELDS
	private Block field_651;
	
	// METHODS
	
	/**
	 * 
	 */
	public abstract boolean MIDDLECRAFT_func_341_a();
	
	/**
	 * 
	 */
	public abstract int MIDDLECRAFT_func_347_a(int a);
	
	/**
	 * 
	 */
	public abstract int MIDDLECRAFT_func_345_a(int a, java.util.Random b);
	
	/**
	 * 
	 */
	public abstract boolean MIDDLECRAFT_func_350_a(int a, boolean b);
	
	/**
	 * *
	 */
	public abstract float getExplosionResistance(Entity a);
	
	/**
	 * 
	 */
	public abstract boolean MIDDLECRAFT_func_351_a(World a, int b, int c, int d);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_344_a(World a, int b, int c, int d, int e);
	
	/**
	 * Drops the block items with a specified chance of dropping the specified items
	 */
	public abstract void dropBlockAsItemWithChance(World a, int b, int c, int d, int e, float f);
	
	/**
	 * Adds to the supplied array any colliding bounding boxes with the passed in bounding box. Args: world, x, y, z, axisAlignedBB, arrayList
	 */
	public abstract void getCollidingBoundingBoxes(World a, int b, int c, int d, AxisAlignedBB e, java.util.ArrayList f);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_349_a(World a, int b, int c, int d, Entity e, Vec3D f);
	
	/**
	 * 
	 */
	public abstract boolean MIDDLECRAFT_func_356_a(World a, int b, int c, int d, EntityPlayer e);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_355_a(World a, int b, int c, int d, java.util.Random e);
	
	/**
	 * Called when a block is using an item and passed in who placed it. Args: x, y, z, entityLiving
	 */
	public abstract void onBlockPlacedBy(World a, int b, int c, int d, EntityLiving e);
	
	/**
	 * 
	 */
	public abstract int MIDDLECRAFT_func_346_a(java.util.Random a);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_339_a(IBlockAccess a, int b, int c, int d);
	
	/**
	 * 
	 */
	public abstract boolean MIDDLECRAFT_func_342_a(IBlockAccess a, int b, int c, int d, int e);
	
	/**
	 * Drops the specified block items
	 */
	public abstract void dropBlockAsItem(World a, int b, int c, int d, int e);
	
	/**
	 * 
	 */
	public abstract int MIDDLECRAFT_func_348_b();
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_353_b(World a, int b, int c, int d);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_354_b(World a, int b, int c, int d, Entity e);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_343_b(World a, int b, int c, int d, EntityPlayer e);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_357_c(World a, int b, int c, int d);
	
	/**
	 * Returns if this block is collidable (only used by Fire). Args: x, y, z
	 */
	public abstract boolean isCollidable();
	
	/**
	 * 
	 */
	public abstract AxisAlignedBB MIDDLECRAFT_func_340_d(World a, int b, int c, int d);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_352_e(World a, int b, int c, int d);

}
