// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class NetServerHandler extends NetHandler {
	// FIELDS
	public static java.util.logging.Logger logger;
	public NetworkManager netManager;
	public boolean field_18;
	private net.minecraft.server.MinecraftServer mcServer;
	private EntityPlayerMP playerEntity;
	private int field_15;
	private double field_9009;
	private double field_9008;
	private double field_9007;
	private boolean field_9006;
	private java.util.Map field_10;
	
	// METHODS
	
	/**
	 * 
	 */
	public abstract void a();
	
	/**
	 * 
	 */
	public abstract void a(double a, double b, double c, float d, float e);
	
	/**
	 * 
	 */
	public abstract void a(Packet7 a);
	
	/**
	 * 
	 */
	public abstract void a(Packet106 a);
	
	/**
	 * 
	 */
	public abstract void a(Packet9 a);
	
	/**
	 * 
	 */
	public abstract void a(Packet3Chat a);
	
	/**
	 * 
	 */
	public abstract void a(Packet102 a);
	
	/**
	 * 
	 */
	public abstract void a(Packet101 a);
	
	/**
	 * 
	 */
	public abstract void a(Packet130 a);
	
	/**
	 * 
	 */
	public abstract void a(Packet15Place a);
	
	/**
	 * 
	 */
	public abstract void a(Packet16BlockItemSwitch a);
	
	/**
	 * 
	 */
	public abstract void a(Packet10Flying a);
	
	/**
	 * 
	 */
	public abstract void a(java.lang.String a);
	
	/**
	 * 
	 */
	public abstract void a(java.lang.String a, java.lang.Object[] b);
	
	/**
	 * 
	 */
	public abstract void a(Packet14BlockDig a);
	
	/**
	 * 
	 */
	public abstract void a(Packet a);
	
	/**
	 * 
	 */
	public abstract void a(Packet255KickDisconnect a);
	
	/**
	 * 
	 */
	public abstract void a(Packet18ArmAnimation a);
	
	/**
	 * 
	 */
	public abstract int b();
	
	/**
	 * 
	 */
	public abstract void b(java.lang.String a);
	
	/**
	 * 
	 */
	public abstract void b(Packet a);
	
	/**
	 * 
	 */
	public abstract java.lang.String c();

}
