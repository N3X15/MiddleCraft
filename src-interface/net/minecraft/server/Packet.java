// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class Packet{
	// FIELDS
	private static java.util.Map a;
	private static java.util.Map b;
	 ()V setChunkModified;
	public final long j;
	public boolean k;
	
	// METHODS
	
	/**
	 * Adds a two way mapping between the packet ID and packet class.
	 */
	 void addIdClassMapping(int a, java.lang.Class b);
	
	/**
	 * 
	 */
	public abstract int a();
	
	/**
	 * 
	 */
	public static Packet a(int a);
	
	/**
	 * 
	 */
	static void a(int a, java.lang.Class b);
	
	/**
	 * 
	 */
	public abstract void a(NetHandler a);
	
	/**
	 * 
	 */
	public abstract void a(java.io.DataInputStream a);
	
	/**
	 * 
	 */
	public abstract void a(java.io.DataOutputStream a);
	
	/**
	 * 
	 */
	public static void a(Packet a, java.io.DataOutputStream b);
	
	/**
	 * Returns the ID of this packet.
	 */
	 int getPacketId();
	
	/**
	 * 
	 */
	public final int b();
	
	/**
	 * 
	 */
	public static Packet b(java.io.DataInputStream a);

}
