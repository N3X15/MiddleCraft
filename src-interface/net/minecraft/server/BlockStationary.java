// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class BlockStationary extends BlockFluids {
	// FIELDS
	
	// METHODS
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_63_a(World a, int b, int c, int d, java.util.Random e);
	
	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are their own) Args: x, y, z, blockID
	 */
	public abstract void onNeighborBlockChange(World a, int b, int c, int d, int e);

}
