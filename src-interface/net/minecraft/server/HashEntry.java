// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

abstract class HashEntry {
	// FIELDS
	 int hashEntry;
	 java.lang.Object valueEntry;
	 HashEntry nextEntry;
	 int slotHash;
	
	// METHODS
	
	/**
	 * *
	 */
	public abstract int getHash();
	
	/**
	 * *
	 */
	public abstract java.lang.Object getValue();
	
	/**
	 * *
	 */
	public abstract boolean iv_equals(java.lang.Object a);
	
	/**
	 * *
	 */
	public abstract int iv_hashCode();
	
	/**
	 * *
	 */
	public abstract java.lang.String iv_toString();

}
