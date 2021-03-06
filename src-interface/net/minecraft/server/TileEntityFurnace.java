// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class TileEntityFurnace extends TileEntity {
	// FIELDS
	public int furnaceBurnTime;
	public int currentItemBurnTime;
	public int furnaceCookTime;
	private ItemStack[] furnaceItemStacks;
	
	// METHODS
	
	/**
	 * 
	 */
	public abstract int MIDDLECRAFT_func_298_a();
	
	/**
	 * 
	 */
	public abstract ItemStack MIDDLECRAFT_func_299_a(int a);
	
	/**
	 * 
	 */
	public abstract ItemStack MIDDLECRAFT_func_300_a(int a, int b);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_301_a(int a, ItemStack b);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_303_a(NBTTagCompound a);
	
	/**
	 * 
	 */
	public abstract boolean MIDDLECRAFT_func_307_a_(EntityPlayer a);
	
	/**
	 * 
	 */
	public abstract java.lang.String MIDDLECRAFT_func_302_b();
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_304_b(NBTTagCompound a);
	
	/**
	 * 
	 */
	public abstract int MIDDLECRAFT_func_305_c();
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_306_e();
	
	/**
	 * Returns true if the furnace is currently burning
	 */
	public abstract boolean isBurning();
	
	/**
	 * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
	 */
	public abstract void smeltItem();

}
