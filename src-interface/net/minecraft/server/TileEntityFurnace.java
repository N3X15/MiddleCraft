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
	public abstract int MIDDLECRAFT_func_590_a();
	
	/**
	 * 
	 */
	public abstract ItemStack MIDDLECRAFT_func_591_a(int a);
	
	/**
	 * 
	 */
	public abstract ItemStack MIDDLECRAFT_func_592_a(int a, int b);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_593_a(int a, ItemStack b);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_595_a(NBTTagCompound a);
	
	/**
	 * 
	 */
	public abstract boolean MIDDLECRAFT_func_604_a_(EntityPlayer a);
	
	/**
	 * 
	 */
	public abstract java.lang.String MIDDLECRAFT_func_594_b();
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_596_b(NBTTagCompound a);
	
	/**
	 * 
	 */
	public abstract int MIDDLECRAFT_func_597_c();
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_599_e();
	
	/**
	 * 
	 */
	public abstract boolean MIDDLECRAFT_func_598_g();
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_601_h();

}
