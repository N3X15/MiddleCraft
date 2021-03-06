// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class BlockPortal extends BlockBreakable {
	// FIELDS
	
	// METHODS
	
	/**
	 * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	 */
	public abstract boolean isOpaqueCube();
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_72_a(World a, int b, int c, int d, Entity e);
	
	/**
	 * 
	 */
	public abstract int MIDDLECRAFT_func_71_a(java.util.Random a);
	
	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	public abstract void setBlockBoundsBasedOnState(IBlockAccess a, int b, int c, int d);
	
	/**
	 * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given coordinates.  Args: blockAccess, x, y, z, side
	 */
	public abstract boolean shouldSideBeRendered(IBlockAccess a, int b, int c, int d, int e);
	
	/**
	 * Checks to see if this location is valid to create a portal and will return True if it does. Args: world, x, y, z
	 */
	public abstract boolean tryToCreatePortal(World a, int b, int c, int d);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_70_b(World a, int b, int c, int d, int e);
	
	/**
	 * 
	 */
	public abstract AxisAlignedBB MIDDLECRAFT_func_69_d(World a, int b, int c, int d);

}
