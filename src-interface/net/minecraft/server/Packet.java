// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class Packet{
	// FIELDS
	private static java.util.Map b;
	private static java.util.Map a;
	public final long j;
	public boolean k;
	
	// METHODS
	
	/**
	 * 
	 */
	public abstract void a(java.io.DataInputStream a)
	
	/**
	 * 
	 */
	public static Packet b(java.io.DataInputStream a)
	
	/**
	 * 
	 */
	public final int b()
	
	/**
	 * 
	 */
	public static Packet a(int a)
	
	/**
	 * 
	 */
	public abstract void a(NetHandler a)
	
	/**
	 * 
	 */
	public abstract int a()
	
	/**
	 * 
	 */
	public abstract void a(java.io.DataOutputStream a)
	
	/**
	 * 
	 */
	static void a(int a, java.lang.Class b)
	
	/**
	 * 
	 */
	public static void a(Packet a, java.io.DataOutputStream b)

}
