// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class ChunkProviderLoadOrGenerate {
	// FIELDS
	 int field_717;
	 int field_716;
	private Chunk field_723;
	private IChunkProvider field_722;
	private IChunkLoader field_721;
	private Chunk[] chunks;
	private World worldObj;
	private Chunk field_718;
	
	// METHODS
	
	/**
	 * 
	 */
	public abstract boolean MIDDLECRAFT_func_1690_a();
	
	/**
	 * 
	 */
	public abstract boolean MIDDLECRAFT_func_1683_a(int a, int b);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_1688_a(IChunkProvider a, int b, int c);
	
	/**
	 * 
	 */
	public abstract boolean MIDDLECRAFT_func_1689_a(boolean a, IProgressUpdate b);
	
	/**
	 * 
	 */
	public abstract boolean MIDDLECRAFT_func_1691_b();
	
	/**
	 * 
	 */
	public abstract Chunk MIDDLECRAFT_func_1684_b(int a, int b);

}
