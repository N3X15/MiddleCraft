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
	public abstract int a();
	
	/**
	 * 
	 */
	public abstract ItemStack a(int a);
	
	/**
	 * 
	 */
	public abstract ItemStack a(int a, int b);
	
	/**
	 * 
	 */
	public abstract void a(int a, ItemStack b);
	
	/**
	 * 
	 */
	public abstract void a(NBTTagCompound a);
	
	/**
	 * 
	 */
	public abstract boolean a_(EntityPlayer a);
	
	/**
	 * 
	 */
	public abstract java.lang.String b();
	
	/**
	 * 
	 */
	public abstract void b(NBTTagCompound a);
	
	/**
	 * 
	 */
	public abstract int c();
	
	/**
	 * 
	 */
	public abstract void e();
	
	/**
	 * 
	 */
	public abstract boolean g();
	
	/**
	 * 
	 */
	public abstract void h();

}
