// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class EntityMinecart extends Entity {
	// FIELDS
	public int field_9168;
	public double field_9166;
	private ItemStack[] cargoItems;
	private boolean field_469;
	private static int[][][] field_468;
	private int field_9163;
	private double field_9162;
	private double field_9161;
	private double field_9160;
	private double field_9159;
	private double field_9158;
	public int field_9167;
	public int field_477;
	public int minecartType;
	public int field_9165;
	public double field_9164;
	
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
	protected abstract void a(NBTTagCompound a);
	
	/**
	 * 
	 */
	public abstract boolean a(Entity a, int b);
	
	/**
	 * 
	 */
	public abstract boolean a(EntityPlayer a);
	
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
	protected abstract void b(NBTTagCompound a);
	
	/**
	 * 
	 */
	public abstract void b_();
	
	/**
	 * 
	 */
	public abstract int c();
	
	/**
	 * 
	 */
	public abstract void c(Entity a);
	
	/**
	 * 
	 */
	public abstract boolean c_();
	
	/**
	 * 
	 */
	public abstract void d();
	
	/**
	 * 
	 */
	public abstract AxisAlignedBB d(Entity a);
	
	/**
	 * 
	 */
	public abstract Vec3D g(double a, double b, double c);
	
	/**
	 * 
	 */
	public abstract double j();
	
	/**
	 * 
	 */
	public abstract void l();
	
	/**
	 * 
	 */
	public abstract AxisAlignedBB q();
	
	/**
	 * 
	 */
	public abstract boolean v();

}