// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class MobSpawnerBase {
	// FIELDS
	public static MobSpawnerBase rainforest;
	public static MobSpawnerBase swampland;
	public static MobSpawnerBase seasonalForest;
	public static MobSpawnerBase forest;
	public static MobSpawnerBase savanna;
	public static MobSpawnerBase shrubland;
	public static MobSpawnerBase taiga;
	public static MobSpawnerBase desert;
	public static MobSpawnerBase plains;
	public static MobSpawnerBase iceDesert;
	public static MobSpawnerBase tundra;
	public static MobSpawnerBase hell;
	public java.lang.String biomeName;
	public int field_6162;
	public byte topBlock;
	public byte fillerBlock;
	public int field_6161;
	protected java.lang.Class[] biomeMonsters;
	protected java.lang.Class[] biomeCreatures;
	private static MobSpawnerBase[] biomeLookupTable;
	
	// METHODS
	
	/**
	 * 
	 */
	protected abstract MobSpawnerBase a(int a);
	
	/**
	 * 
	 */
	protected abstract MobSpawnerBase a(java.lang.String a);
	
	/**
	 * 
	 */
	public abstract java.lang.Class[] a(EnumCreatureType a);
	
	/**
	 * 
	 */
	protected abstract MobSpawnerBase b();
	
	/**
	 * 
	 */
	protected abstract MobSpawnerBase b(int a);

}