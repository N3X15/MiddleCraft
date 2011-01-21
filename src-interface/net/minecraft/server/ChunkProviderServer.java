// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class ChunkProviderServer {
	// FIELDS
	private java.util.Set field_725;
	private Chunk field_724;
	private IChunkProvider field_730;
	private IChunkLoader field_729;
	private java.util.Map field_728;
	private java.util.List field_727;
	private WorldServer field_726;
	
	// METHODS
	
	/**
	 * 
	 */
	public abstract boolean MIDDLECRAFT_func_899_a();
	
	/**
	 * 
	 */
	public abstract boolean MIDDLECRAFT_func_895_a(int a, int b);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_897_a(IChunkProvider a, int b, int c);
	
	/**
	 * 
	 */
	public abstract boolean MIDDLECRAFT_func_898_a(boolean a, IProgressUpdate b);
	
	/**
	 * 
	 */
	public abstract boolean MIDDLECRAFT_func_900_b();
	
	/**
	 * 
	 */
	public abstract Chunk MIDDLECRAFT_func_896_b(int a, int b);
	
	/**
	 * *
	 */
	public abstract void func_374(int a, int b);
	
	/**
	 * loads or generates the chunk at the chunk location specified
	 */
	public abstract Chunk loadChunk(int a, int b);

}
