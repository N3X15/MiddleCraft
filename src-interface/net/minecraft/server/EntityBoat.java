// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class EntityBoat extends Entity {
	// FIELDS
	public int field_9178;
	private double field_9175;
	private double field_9173;
	private double field_9171;
	public int field_9177;
	public int field_436;
	private int field_9176;
	private double field_9174;
	private double field_9172;
	
	// METHODS
	
	/**
	 * 
	 */
	public abstract void A();
	
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
	protected abstract void b(NBTTagCompound a);
	
	/**
	 * 
	 */
	public abstract void b_();
	
	/**
	 * 
	 */
	public abstract boolean c_();
	
	/**
	 * 
	 */
	public abstract AxisAlignedBB d(Entity a);
	
	/**
	 * 
	 */
	public abstract double j();
	
	/**
	 * 
	 */
	public abstract AxisAlignedBB q();
	
	/**
	 * 
	 */
	public abstract boolean v();

}
