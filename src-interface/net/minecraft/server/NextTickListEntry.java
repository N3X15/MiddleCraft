// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class NextTickListEntry{
	// FIELDS
	public Packet4UpdateTime a;
	public Packet4UpdateTime b;
	 double[] c;
	 double[] d;
	 double[] e;
	 double[] f;
	 double[] g;
	private java.util.Random h;
	private Packet4UpdateTime i;
	private Packet4UpdateTime j;
	private Packet4UpdateTime k;
	private Packet4UpdateTime l;
	private Packet4UpdateTime m;
	private Packet17AddToInventory n;
	private double[] o;
	private double[] p;
	private double[] q;
	private double[] r;
	private NBTTagShort s;
	
	// METHODS
	
	/**
	 * 
	 */
	public boolean a()
	
	/**
	 * Checks to see if a chunk exists at x, y
	 */
	public boolean chunkExists(int a, int b)
	
	/**
	 * 
	 */
	public void a(int a, int b, byte[] c)
	
	/**
	 * Populates chunk with ores etc etc
	 */
	public void populate(HashEntry2 a, int b, int c)
	
	/**
	 * Called to save the world
	 */
	public boolean saveWorld(boolean a, ChunkCoordIntPair b)
	
	/**
	 * 
	 */
	private double[] a(double[] a, int b, int c, int d, int e, int f, int g)
	
	/**
	 * 
	 */
	public boolean b()
	
	/**
	 * *
	 */
	public kx provideChunk(int a, int b)
	
	/**
	 * 
	 */
	public void b(int a, int b, byte[] c)

}
