// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class Packet extends EntityCow{
	// FIELDS
	public boolean a;
	
	// METHODS
	
	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(Packet1Login a)
	
	/**
	 * 
	 */
	public boolean a(BlockLog a)
	
	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(Packet1Login a)
	
	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected java.lang.String getLivingSound()
	
	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected java.lang.String getHurtSound()
	
	/**
	 * Returns the sound this mob makes on death.
	 */
	protected java.lang.String getDeathSound()
	
	/**
	 * Returns the item ID for the item the mob drops on death.
	 */
	protected int getDropItemId()

}
