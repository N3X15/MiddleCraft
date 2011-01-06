// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class BlockChest extends AxisAlignedBB{
	// FIELDS
	private java.util.Random a;
	
	// METHODS
	
	/**
	 * Returns the block texture based on the side being looked at.  Args: side
	 */
	public int getBlockTextureFromSide(int a)
	
	/**
	 * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
	 */
	public boolean canPlaceBlockAt(Packet17AddToInventory a, int b, int c, int d)
	
	/**
	 * Called upon block activation (left or right click on the block.). The three integers represent x,y,z of the block.
	 */
	public boolean blockActivated(Packet17AddToInventory a, int b, int c, int d, BlockLog e)
	
	/**
	 * *
	 */
	protected EntitySnowball SetBlockEntity()
	
	/**
	 * Called whenever the block is removed.
	 */
	public void onBlockRemoval(Packet17AddToInventory a, int b, int c, int d)
	
	/**
	 * Checks the neighbor blocks to see if there is a chest there. Args: world, x, y, z
	 */
	private boolean isThereANeighborChest(Packet17AddToInventory a, int b, int c, int d)

}
