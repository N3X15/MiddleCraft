// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class NetworkListenThread {
	// FIELDS
	public static java.util.logging.Logger logger;
	public volatile boolean field_973;
	public net.minecraft.server.MinecraftServer mcServer;
	private java.net.ServerSocket serverSocket;
	private java.lang.Thread networkAcceptThread;
	private int field_977;
	private java.util.ArrayList field_976;
	private java.util.ArrayList field_975;
	
	// METHODS
	
	/**
	 * *
	 */
	public abstract void func_715();
	
	/**
	 * *
	 */
	public abstract void func_4108(NetServerHandler a);

}
