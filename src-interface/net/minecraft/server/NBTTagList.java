// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class NBTTagList extends NBTBase{
	// FIELDS
	private java.util.List a;
	private byte b;
	 ()V setChunkModified;
	
	// METHODS
	
	/**
	 * Adds the provided tag to the end of the list. There is no check to verify this tag is of the same type as any previous tag.
	 */
	 void setTag(NBTBase a);
	
	/**
	 * 
	 */
	public byte a();
	
	/**
	 * 
	 */
	public NBTBase a(int a);
	
	/**
	 * 
	 */
	public void a(NBTBase a);
	
	/**
	 * 
	 */
	 void a(java.io.DataInput a);
	
	/**
	 * 
	 */
	 void a(java.io.DataOutput a);
	
	/**
	 * Returns the number of tags in the list.
	 */
	 int tagCount();
	
	/**
	 * 
	 */
	public int b();
	
	/**
	 * *
	 */
	 java.lang.String es_toString();
	
	/**
	 * 
	 */
	public java.lang.String toString();

}
