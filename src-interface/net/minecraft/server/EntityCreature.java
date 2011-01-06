// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class EntityCreature extends EntityTrackerEntry{
	// FIELDS
	
	// METHODS
	
	/**
	 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons use this to react to sunlight and start to burn.
	 */
	public void onLivingUpdate()
	
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
