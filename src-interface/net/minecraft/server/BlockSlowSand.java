// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class BlockSlowSand extends Block{
	// FIELDS
	
	// METHODS
	
	/**
	 * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
	 */
	 void onEntityCollidedWithBlock(World a, int b, int c, int d, Entity e);
	
	/**
	 * 
	 */
	public void a(World a, int b, int c, int d, Entity e);
	
	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been cleared to be reused)
	 */
	 AxisAlignedBB getCollisionBoundingBoxFromPool(World a, int b, int c, int d);
	
	/**
	 * 
	 */
	public AxisAlignedBB d(World a, int b, int c, int d);

}
