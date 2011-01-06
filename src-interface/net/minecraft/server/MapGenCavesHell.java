// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class MapGenCavesHell extends BlockSoil{
	// FIELDS
	
	// METHODS
	
	/**
	 * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	 */
	public boolean isOpaqueCube()
	
	/**
	 * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
	 */
	public boolean canPlaceBlockAt(Packet17AddToInventory a, int b, int c, int d)
	
	/**
	 * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world, x, y, z, startVec, endVec
	 */
	public BlockFlower collisionRayTrace(Packet17AddToInventory a, int b, int c, int d, NoiseGenerator2 e, NoiseGenerator2 f)
	
	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(java.util.Random a)
	
	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	public void setBlockBoundsBasedOnState(ChunkCoordinates a, int b, int c, int d)
	
	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are their own) Args: x, y, z, blockID
	 */
	public void onNeighborBlockChange(Packet17AddToInventory a, int b, int c, int d, int e)
	
	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been cleared to be reused)
	 */
	public IUpdatePlayerListBox getCollisionBoundingBoxFromPool(Packet17AddToInventory a, int b, int c, int d)
	
	/**
	 * Called whenever the block is added into the world. Args: world, x, y, z
	 */
	public void onBlockAdded(Packet17AddToInventory a, int b, int c, int d)
	
	/**
	 * 
	 */
	private void g(Packet17AddToInventory a, int b, int c, int d)

}
