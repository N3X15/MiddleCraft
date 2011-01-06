// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class BlockLeaves extends NBTTagByteArray{
	// FIELDS
	private int a;
	
	// METHODS
	
	/**
	 * *
	 */
	public int getDamageVsEntity(IMobs a)
	
	/**
	 * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise the damage on the stack.
	 */
	public void hitBlock(BlockSponge a, int b, int c, int d, int e)
	
	/**
	 * Returns the strength of the stack against a given block. 1.0F base, (Quality+1)*2 if correct blocktype, 1.5F if sword
	 */
	public float getStrVsBlock(BlockSponge a, BlockSoil b)
	
	/**
	 * *
	 */
	public void hitEntity(BlockSponge a, lc b)

}
