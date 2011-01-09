// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class ChunkProviderGenerate {
	// FIELDS
	public NoiseGeneratorOctaves field_715;
	public NoiseGeneratorOctaves field_714;
	public NoiseGeneratorOctaves field_713;
	 double[] field_4229;
	 double[] field_4228;
	 double[] field_4227;
	 double[] field_4226;
	 double[] field_4225;
	 int[][] field_707;
	private java.util.Random rand;
	private NoiseGeneratorOctaves field_705;
	private NoiseGeneratorOctaves field_704;
	private NoiseGeneratorOctaves field_703;
	private NoiseGeneratorOctaves field_702;
	private NoiseGeneratorOctaves field_701;
	private World worldObj;
	private double[] field_4224;
	private double[] field_698;
	private double[] field_697;
	private double[] field_696;
	private MapGenBase field_695;
	private MobSpawnerBase[] biomesForGeneration;
	private double[] field_4222;
	
	// METHODS
	
	/**
	 * 
	 */
	public abstract boolean a();
	
	/**
	 * 
	 */
	public abstract boolean a(int a, int b);
	
	/**
	 * 
	 */
	public abstract void a(int a, int b, byte[] c, MobSpawnerBase[] d);
	
	/**
	 * 
	 */
	public abstract void a(int a, int b, byte[] c, MobSpawnerBase[] d, double[] e);
	
	/**
	 * 
	 */
	public abstract void a(IChunkProvider a, int b, int c);
	
	/**
	 * 
	 */
	public abstract boolean a(boolean a, IProgressUpdate b);
	
	/**
	 * 
	 */
	public abstract boolean b();
	
	/**
	 * 
	 */
	public abstract Chunk b(int a, int b);

}