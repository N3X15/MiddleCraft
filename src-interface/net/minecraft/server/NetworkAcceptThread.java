// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

abstract class NetworkAcceptThread extends java.lang.Thread {
	// FIELDS
	 net.minecraft.server.MinecraftServer mcServer;
	 NetworkListenThread field_985;
	
	// METHODS
	
	/**
	 * 
	 */
	public abstract void run();

}