// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class BlockPortal extends BlockSoil{
	// FIELDS
	
	// METHODS
	
	/**
	 * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
	 */
	public void onEntityCollidedWithBlock(Packet17AddToInventory a, int b, int c, int d, IMobs e)
	
	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been cleared to be reused)
	 */
	public IUpdatePlayerListBox getCollisionBoundingBoxFromPool(Packet17AddToInventory a, int b, int c, int d)

}
