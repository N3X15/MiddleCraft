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
	public abstract void MIDDLECRAFT_func_1709_a();
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_1712_a(double a, double b, double c, float d, float e);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_1726_a(Packet7 a);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_1730_a(Packet106 a);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_1727_a(Packet9 a);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_1719_a(Packet3Chat a);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_1729_a(Packet102 a);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_1728_a(Packet101 a);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_1731_a(Packet130 a);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_1714_a(Packet15Place a);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_1718_a(Packet16BlockItemSwitch a);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_1711_a(Packet10Flying a);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_1710_a(java.lang.String a);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_1715_a(java.lang.String a, java.lang.Object[] b);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_1713_a(Packet14BlockDig a);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_1716_a(Packet a);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_1722_a(Packet255KickDisconnect a);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_1721_a(Packet18ArmAnimation a);
	
	/**
	 * 
	 */
	public abstract int MIDDLECRAFT_func_1723_b();
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_1724_b(java.lang.String a);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_1717_b(Packet a);
	
	/**
	 * 
	 */
	public abstract java.lang.String MIDDLECRAFT_func_1725_c();

}
