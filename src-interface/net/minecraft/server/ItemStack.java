// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract final class ItemStack{
	// FIELDS
	public int a;
	public int b;
	public int c;
	public int d;
	 ()V setChunkModified;
	
	// METHODS
	
	/**
	 * *
	 */
	 ItemStack func_20117(ItemStack a);
	
	/**
	 * 
	 */
	public Item a();
	
	/**
	 * 
	 */
	public ItemStack a(int a);
	
	/**
	 * 
	 */
	public void a(int a, int b, int c, int d);
	
	/**
	 * 
	 */
	public NBTTagCompound a(NBTTagCompound a);
	
	/**
	 * 
	 */
	public int a(Entity a);
	
	/**
	 * 
	 */
	public ItemStack a(World a, EntityPlayer b);
	
	/**
	 * 
	 */
	public void a(EntityPlayer a);
	
	/**
	 * 
	 */
	public boolean a(EntityPlayer a, World b, int c, int d, int e, int f);
	
	/**
	 * 
	 */
	public float a(Block a);
	
	/**
	 * 
	 */
	public static ItemStack a(ItemStack a);
	
	/**
	 * 
	 */
	public static boolean a(ItemStack a, ItemStack b);
	
	/**
	 * 
	 */
	public void a(EntityLiving a);
	
	/**
	 * *
	 */
	 boolean canHarvestBlock(Block a);
	
	/**
	 * 
	 */
	public int b();
	
	/**
	 * 
	 */
	public void b(int a);
	
	/**
	 * 
	 */
	public void b(NBTTagCompound a);
	
	/**
	 * 
	 */
	public boolean b(Block a);
	
	/**
	 * 
	 */
	private boolean b(ItemStack a);
	
	/**
	 * Returns the max damage an item in the stack can take.
	 */
	 int getMaxDamage();
	
	/**
	 * 
	 */
	public int c();
	
	/**
	 * Returns a new stack with the same properties.
	 */
	 ItemStack copy();
	
	/**
	 * 
	 */
	public ItemStack d();
	
	/**
	 * *
	 */
	 java.lang.String il_toString();
	
	/**
	 * 
	 */
	public java.lang.String toString();

}
