// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class MapGenBase {
	// FIELDS
	protected int field_947;
	protected java.util.Random field_946;
	
	// METHODS
	
	/**
	 * 
	 */
	public abstract void a(IChunkProvider a, World b, int c, int d, byte[] e);
	
	/**
	 * 
	 */
	protected abstract void a(World a, int b, int c, int d, int e, byte[] f);

}