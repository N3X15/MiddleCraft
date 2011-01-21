// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class ItemStack {
	// FIELDS
	public int stackSize;
	public int animationsToGo;
	public int itemID;
	public int itemDamage;
	
	// METHODS
	
	/**
	 * Returns the object corresponding to the stack.
	 */
	public abstract Item getItem();
	
	/**
	 * *
	 */
	public abstract ItemStack func_20118(int a);
	
	/**
	 * Calls the corresponding fct in di
	 */
	public abstract void hitBlock(int a, int b, int c, int d);
	
	/**
	 * Write the stack fields to a NBT object. Return the new NBT object.
	 */
	public abstract NBTTagCompound writeToNBT(NBTTagCompound a);
	
	/**
	 * *
	 */
	public abstract int getDamageVsEntity(Entity a);
	
	/**
	 * Called whenever this item stack is equipped and right clicked. Returns the new item stack to put in the position where this item is. Args: world, player
	 */
	public abstract ItemStack useItemRightClick(World a, EntityPlayer b);
	
	/**
	 * *
	 */
	public abstract void func_577(EntityPlayer a);
	
	/**
	 * Uses the item stack by the player. Gives the coordinates of the block its being used against and the side. Args: player, world, x, y, z, side
	 */
	public abstract boolean useItem(EntityPlayer a, World b, int c, int d, int e, int f);
	
	/**
	 * Returns the strength of the stack against a given block.
	 */
	public abstract float getStrVsBlock(Block a);
	
	/**
	 * *
	 */
	public abstract void hitEntity(EntityLiving a);
	
	/**
	 * Returns maximum size of the stack.
	 */
	public abstract int getMaxStackSize();
	
	/**
	 * Raise the damage on the item by the argument. If the dmg is superior to health, destroy one element of the stack.
	 */
	public abstract void damageItem(int a);
	
	/**
	 * Read the stack fields from a NBT object.
	 */
	public abstract void readFromNBT(NBTTagCompound a);
	
	/**
	 * *
	 */
	public abstract boolean canHarvestBlock(Block a);
	
	/**
	 * Returns the max damage an item in the stack can take.
	 */
	public abstract int getMaxDamage();
	
	/**
	 * Returns a new stack with the same properties.
	 */
	public abstract ItemStack copy();
	
	/**
	 * *
	 */
	public abstract java.lang.String il_toString();

}
