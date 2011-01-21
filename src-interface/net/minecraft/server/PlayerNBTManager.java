// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class PlayerNBTManager {
	// FIELDS
	public static java.util.logging.Logger logger;
	private java.io.File worldFile;
	
	// METHODS
	
	/**
	 * Writes the player data to disk from the specified PlayerEntityMP.
	 */
	public abstract void writePlayerData(EntityPlayerMP a);
	
	/**
	 * Reads the player data from disk into the specified PlayerEntityMP.
	 */
	public abstract void readPlayerData(EntityPlayerMP a);

}
