// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class EntityList {
	// FIELDS
	private static java.util.Map field_849;
	private static java.util.Map field_848;
	private static java.util.Map field_851;
	private static java.util.Map field_850;
	
	// METHODS
	
	/**
	 * 
	 */
	public abstract static Entity a(NBTTagCompound a, World b);
	
	/**
	 * 
	 */
	public abstract static int a(Entity a);
	
	/**
	 * 
	 */
	private abstract static void a(java.lang.Class a, java.lang.String b, int c);
	
	/**
	 * 
	 */
	public abstract static Entity a(java.lang.String a, World b);
	
	/**
	 * 
	 */
	public abstract static java.lang.String b(Entity a);

}
