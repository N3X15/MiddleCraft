// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class ItemTool extends Item {
	// FIELDS
	protected int field_262;
	private Block[] blocksEffectiveAgainst;
	private float field_264;
	private int field_263;
	
	// METHODS
	
	/**
	 * 
	 */
	public abstract int a(Entity a);
	
	/**
	 * 
	 */
	public abstract void a(ItemStack a, int b, int c, int d, int e);
	
	/**
	 * 
	 */
	public abstract float a(ItemStack a, Block b);
	
	/**
	 * 
	 */
	public abstract void a(ItemStack a, EntityLiving b);

}