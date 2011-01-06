// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class WorldGenFlowers extends BlockSoil{
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
	 * Called upon block activation (left or right click on the block.). The three integers represent x,y,z of the block.
	 */
	public boolean blockActivated(Packet17AddToInventory a, int b, int c, int d, BlockLog e)
	
	/**
	 * Ticks the block if it's been scheduled
	 */
	public void updateTick(Packet17AddToInventory a, int b, int c, int d, java.util.Random e)
	
	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	public void setBlockBoundsBasedOnState(ChunkCoordinates a, int b, int c, int d)
	
	/**
	 * How many world ticks before ticking
	 */
	public int tickRate()
	
	/**
	 * Called whenever the block is removed.
	 */
	public void onBlockRemoval(Packet17AddToInventory a, int b, int c, int d)
	
	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are their own) Args: x, y, z, blockID
	 */
	public void onNeighborBlockChange(Packet17AddToInventory a, int b, int c, int d, int e)
	
	/**
	 * Called when the block is clicked by a player. Args: x, y, z, entityPlayer
	 */
	public void onBlockClicked(Packet17AddToInventory a, int b, int c, int d, BlockLog e)
	
	/**
	 * Is this block powering the block on the specified side
	 */
	public boolean isPoweringTo(ChunkCoordinates a, int b, int c, int d, int e)
	
	/**
	 * Can this block provide power. Only wire currently seems to have this change based on its state.
	 */
	public boolean canProvidePower()
	
	/**
	 * Called when a block is placed using an item. Used often for taking the facing and figuring out how to position the item. Args: x, y, z, facing
	 */
	public void onBlockPlaced(Packet17AddToInventory a, int b, int c, int d, int e)
	
	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been cleared to be reused)
	 */
	public IUpdatePlayerListBox getCollisionBoundingBoxFromPool(Packet17AddToInventory a, int b, int c, int d)
	
	/**
	 * Is this block indirectly powering the block on the specified side
	 */
	public boolean isIndirectlyPoweringTo(Packet17AddToInventory a, int b, int c, int d, int e)
	
	/**
	 * Called whenever the block is added into the world. Args: world, x, y, z
	 */
	public void onBlockAdded(Packet17AddToInventory a, int b, int c, int d)
	
	/**
	 * 
	 */
	private boolean g(Packet17AddToInventory a, int b, int c, int d)

}
