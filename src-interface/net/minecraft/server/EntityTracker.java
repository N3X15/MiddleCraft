// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class EntityTracker {
	// FIELDS
	private java.util.Set field_911;
	private MCHashTable field_910;
	private net.minecraft.server.MinecraftServer mcServer;
	private int field_912;
	
	// METHODS
	
	/**
	 * *
	 */
	public abstract void func_607();
	
	/**
	 * *
	 */
	public abstract void func_611(Entity a);
	
	/**
	 * *
	 */
	public abstract void func_6187(Entity a, int b, int c);
	
	/**
	 * *
	 */
	public abstract void func_6186(Entity a, int b, int c, boolean d);
	
	/**
	 * *
	 */
	public abstract void func_12021(Entity a, Packet b);
	
	/**
	 * *
	 */
	public abstract void func_9238(EntityPlayerMP a);
	
	/**
	 * *
	 */
	public abstract void func_610(Entity a);
	
	/**
	 * *
	 */
	public abstract void func_609(Entity a, Packet b);

}
