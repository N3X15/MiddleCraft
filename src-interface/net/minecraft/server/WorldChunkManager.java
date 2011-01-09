// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class WorldChunkManager {
	// FIELDS
	public double[] temperature;
	public double[] humidity;
	public double[] field_4257;
	public MobSpawnerBase[] field_4256;
	private NoiseGeneratorOctaves2 field_4255;
	private NoiseGeneratorOctaves2 field_4254;
	private NoiseGeneratorOctaves2 field_4253;
	
	// METHODS
	
	/**
	 * 
	 */
	public abstract MobSpawnerBase a(int a, int b);
	
	/**
	 * 
	 */
	public abstract MobSpawnerBase[] a(int a, int b, int c, int d);
	
	/**
	 * 
	 */
	public abstract MobSpawnerBase a(ChunkCoordIntPair a);
	
	/**
	 * 
	 */
	public abstract double[] a(double[] a, int b, int c, int d, int e);
	
	/**
	 * 
	 */
	public abstract MobSpawnerBase[] a(MobSpawnerBase[] a, int b, int c, int d, int e);

}