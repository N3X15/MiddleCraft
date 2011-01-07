// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class NetworkManager {
	// FIELDS
	public static final java.lang.Object threadSyncObject;
	public static int numReadThreads;
	public static int numWriteThreads;
	public int chunkDataSendCounter;
	private java.lang.Object sendQueueLock;
	private java.net.Socket networkSocket;
	private final java.net.SocketAddress field_12032;
	private java.io.DataInputStream socketInputStream;
	private java.io.DataOutputStream socketOutputStream;
	private boolean isRunning;
	private java.util.List readPackets;
	private java.util.List dataPackets;
	private java.util.List chunkDataPackets;
	private NetHandler netHandler;
	private boolean isServerTerminating;
	private java.lang.Thread writeThread;
	private java.lang.Thread readThread;
	private boolean isTerminating;
	private java.lang.String terminationReason;
	private java.lang.Object[] field_20176;
	private int timeSinceLastRead;
	private int sendQueueByteLength;
	private int field_20175;
	
	// METHODS
	
	/**
	 * 
	 */
	public abstract void a();
	
	/**
	 * 
	 */
	abstract static boolean a(NetworkManager a);
	
	/**
	 * 
	 */
	public abstract void a(NetHandler a);
	
	/**
	 * 
	 */
	public abstract transient void a(java.lang.String a, java.lang.Object[] b);
	
	/**
	 * 
	 */
	public abstract void a(Packet a);
	
	/**
	 * 
	 */
	public abstract java.net.SocketAddress b();
	
	/**
	 * 
	 */
	abstract static boolean b(NetworkManager a);
	
	/**
	 * 
	 */
	public abstract void c();
	
	/**
	 * 
	 */
	abstract static void c(NetworkManager a);
	
	/**
	 * 
	 */
	public abstract int d();
	
	/**
	 * 
	 */
	abstract static void d(NetworkManager a);
	
	/**
	 * 
	 */
	abstract static java.lang.Thread e(NetworkManager a);
	
	/**
	 * 
	 */
	abstract static java.lang.Thread f(NetworkManager a);

}
