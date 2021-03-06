// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class ItemSword extends Item {
	// FIELDS
	private int field_4210;
	
	// METHODS
	
	/**
	 * *
	 */
	public abstract int getDamageVsEntity(Entity a);
	
	/**
	 * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise the damage on the stack.
	 */
	public abstract void hitBlock(ItemStack a, int b, int c, int d, int e);
	
	/**
	 * Returns the strength of the stack against a given block. 1.0F base, (Quality+1)*2 if correct blocktype, 1.5F if sword
	 */
	public abstract float getStrVsBlock(ItemStack a, Block b);
	
	/**
	 * *
	 */
	public abstract void hitEntity(ItemStack a, EntityLiving b);

}
