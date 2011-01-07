// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class EntityGhast extends EntityFlying{
	// FIELDS
	public int a;
	private Entity aj;
	private int ak;
	public double b;
	public double c;
	public double d;
	public int e;
	public ()V setChunkModified;
	
	// METHODS
	
	/**
	 * *
	 */
	 boolean func_4046(double a, double b, double c, double d);
	
	/**
	 * 
	 */
	public boolean a();
	
	/**
	 * 
	 */
	private boolean a(double a, double b, double c, double d);
	
	/**
	 * *
	 */
	 void func_152();
	
	/**
	 * 
	 */
	protected void c();
	
	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	 java.lang.String getLivingSound();
	
	/**
	 * 
	 */
	protected java.lang.String d();
	
	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	 java.lang.String getHurtSound();
	
	/**
	 * 
	 */
	protected java.lang.String e();
	
	/**
	 * Returns the sound this mob makes on death.
	 */
	 java.lang.String getDeathSound();
	
	/**
	 * 
	 */
	protected java.lang.String f();
	
	/**
	 * Returns the item ID for the item the mob drops on death.
	 */
	 int getDropItemId();
	
	/**
	 * 
	 */
	protected int g();
	
	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	 float getSoundVolume();
	
	/**
	 * 
	 */
	protected float h();
	
	/**
	 * *
	 */
	 int func_4045();
	
	/**
	 * 
	 */
	public int i();

}
