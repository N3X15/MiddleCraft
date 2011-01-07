// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class MCHashTable{
	// FIELDS
	private transient iv[] a;
	private transient int b;
	private int c;
	private final float d;
	private transient volatile int e;
	 ()V setChunkModified;
	
	// METHODS
	
	/**
	 * Adds a key and associated value to this map
	 */
	 void addKey(int a, java.lang.Object b);
	
	/**
	 * 
	 */
	public void a();
	
	/**
	 * 
	 */
	public java.lang.Object a(int a);
	
	/**
	 * 
	 */
	private static int a(int a, int b);
	
	/**
	 * 
	 */
	private void a(int a, int b, java.lang.Object c, int d);
	
	/**
	 * 
	 */
	public void a(int a, java.lang.Object b);
	
	/**
	 * 
	 */
	private void a(iv[] a);
	
	/**
	 * Returns true if this hash table contains the specified item.
	 */
	 boolean containsItem(int a);
	
	/**
	 * 
	 */
	public boolean b(int a);
	
	/**
	 * Returns the internal entry for a key
	 */
	 HashEntry lookupEntry(int a);
	
	/**
	 * 
	 */
	final HashEntry c(int a);
	
	/**
	 * Removes the specified object from the map and returns it
	 */
	 java.lang.Object removeObject(int a);
	
	/**
	 * 
	 */
	public java.lang.Object d(int a);
	
	/**
	 * Removes the specified entry from the map and returns it
	 */
	 HashEntry removeEntry(int a);
	
	/**
	 * 
	 */
	final HashEntry e(int a);
	
	/**
	 * Returns the hash code for a key
	 */
	 int getHash(int a);
	
	/**
	 * 
	 */
	static int f(int a);
	
	/**
	 * Makes the passed in integer suitable for hashing by a number of shifts
	 */
	 int computeHash(int a);
	
	/**
	 * 
	 */
	private static int g(int a);
	
	/**
	 * Increases the number of hash slots
	 */
	 void grow(int a);
	
	/**
	 * 
	 */
	private void h(int a);

}
