// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class CraftingInventoryCB {
	// FIELDS
	private short field_20132;
	private java.util.Set field_20131;
	public java.util.List field_20136;
	public java.util.List field_20135;
	public int field_20134;
	protected java.util.List field_20133;
	
	// METHODS
	
	/**
	 * 
	 */
	public abstract void a();
	
	/**
	 * 
	 */
	public abstract Slot a(int a);
	
	/**
	 * 
	 */
	public abstract ItemStack a(int a, int b, EntityPlayer c);
	
	/**
	 * 
	 */
	public abstract void a(ICrafting a);
	
	/**
	 * 
	 */
	protected abstract void a(Slot a);
	
	/**
	 * 
	 */
	public abstract void a(EntityPlayer a);
	
	/**
	 * 
	 */
	public abstract void a(EntityPlayer a, boolean b);
	
	/**
	 * 
	 */
	public abstract void a(IInventory a);
	
	/**
	 * 
	 */
	public abstract Slot a(IInventory a, int b);
	
	/**
	 * 
	 */
	public abstract boolean b(EntityPlayer a);
	
	/**
	 * 
	 */
	public abstract boolean c(EntityPlayer a);

}
