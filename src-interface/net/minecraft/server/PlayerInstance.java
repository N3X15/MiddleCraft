// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

abstract class PlayerInstance {
	// FIELDS
	 PlayerManager field_1073;
	private java.util.List field_1072;
	private int field_1071;
	private int field_1070;
	private ChunkCoordIntPair field_1069;
	private short[] field_1068;
	private int field_1067;
	private int field_1066;
	private int field_1065;
	private int field_1064;
	private int field_1063;
	private int field_1062;
	private int field_1061;
	
	// METHODS
	
	/**
	 * 
	 */
	public abstract void a();
	
	/**
	 * 
	 */
	public abstract void a(int a, int b, int c);
	
	/**
	 * 
	 */
	public abstract void a(EntityPlayerMP a);
	
	/**
	 * 
	 */
	public abstract void a(Packet a);
	
	/**
	 * 
	 */
	public abstract void b(EntityPlayerMP a);

}
