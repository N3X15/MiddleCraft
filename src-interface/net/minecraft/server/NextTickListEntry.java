// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class NextTickListEntry {
	// FIELDS
	public int xCoord;
	public int yCoord;
	public int zCoord;
	public int blockID;
	public long scheduledTime;
	private static long nextTickEntryID;
	private long tickEntryID;
	
	// METHODS
	
	/**
	 * 
	 */
	public abstract NextTickListEntry a(long a);
	
	/**
	 * 
	 */
	public abstract int a(NextTickListEntry a);
	
	/**
	 * 
	 */
	public abstract boolean equals(java.lang.Object a);
	
	/**
	 * 
	 */
	public abstract int hashCode();

}
