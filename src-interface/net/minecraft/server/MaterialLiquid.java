// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class MaterialLiquid extends BlockSoil{
	// FIELDS
	
	// METHODS
	
	/**
	 * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	 */
	public boolean isOpaqueCube()
	
	/**
	 * Returns the block texture based on the side being looked at.  Args: side
	 */
	public int getBlockTextureFromSide(int a)
	
	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	public int idDropped(int a, java.util.Random b)
	
	/**
	 * Returns whether this block is collideable based on the arguments passed in Args: blockMetaData, unknownFlag
	 */
	public boolean canCollideCheck(int a, boolean b)
	
	/**
	 * Can add to the passed in vector for a movement vector to be applied to the entity. Args: x, y, z, entity, vec3d
	 */
	public void velocityToAddToEntity(Packet17AddToInventory a, int b, int c, int d, IMobs e, NoiseGenerator2 f)
	
	/**
	 * Ticks the block if it's been scheduled
	 */
	public void updateTick(Packet17AddToInventory a, int b, int c, int d, java.util.Random e)
	
	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(java.util.Random a)
	
	/**
	 * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given coordinates.  Args: blockAccess, x, y, z, side
	 */
	public boolean shouldSideBeRendered(ChunkCoordinates a, int b, int c, int d, int e)
	
	/**
	 * How many world ticks before ticking
	 */
	public int tickRate()
	
	/**
	 * Calculates the height of a fluid from its metadata
	 */
	public static float setFluidHeight(int a)
	
	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are their own) Args: x, y, z, blockID
	 */
	public void onNeighborBlockChange(Packet17AddToInventory a, int b, int c, int d, int e)
	
	/**
	 * 
	 */
	protected int b(ChunkCoordinates a, int b, int c, int d)
	
	/**
	 * 
	 */
	private NoiseGenerator2 c(ChunkCoordinates a, int b, int c, int d)
	
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
	protected int g(Packet17AddToInventory a, int b, int c, int d)
	
	/**
	 * 
	 */
	protected void h(Packet17AddToInventory a, int b, int c, int d)
	
	/**
	 * 
	 */
	private void i(Packet17AddToInventory a, int b, int c, int d)

}
