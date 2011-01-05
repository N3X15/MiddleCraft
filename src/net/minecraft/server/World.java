/**
 * 
 */
package com.minecraft.server;

import java.io.*;
import java.util.*;

/**
 * @author Rob
 *
 */
public abstract class World {

	public abstract WorldChunkManager func_4077_a();

	//protected abstract IChunkProvider func_4076_a(File file);

	public abstract int func_528_f(int i, int j);

	public abstract void saveWorld(boolean flag, IProgressUpdate iprogressupdate);

	
	public abstract int getBlockId(int i, int j, int k);

	public abstract boolean func_20111_e(int i, int j, int k);

	public abstract boolean blockExists(int i, int j, int k);

	public abstract boolean checkChunksExist(int i, int j, int k, int l, int i1, int j1);


	public abstract Chunk getChunkFromBlockCoords(int i, int j);

	public abstract Chunk getChunkFromChunkCoords(int i, int j);

	public abstract boolean setBlockAndMetadata(int i, int j, int k, int l, int i1);

	public abstract boolean setBlock(int i, int j, int k, int l);

	public abstract Material getBlockMaterial(int i, int j, int k);

	public abstract int getBlockMetadata(int i, int j, int k);

	public abstract void setBlockMetadataWithNotify(int i, int j, int k, int l);

	public abstract boolean setBlockMetadata(int i, int j, int k, int l);

	public abstract boolean setBlockWithNotify(int i, int j, int k, int l);

	public abstract boolean setBlockAndMetadataWithNotify(int i, int j, int k, int l, int i1);

	public abstract void markBlockNeedsUpdate(int i, int j, int k);

	public abstract void func_498_f(int i, int j, int k, int l);

	public abstract void func_519_b(int i, int j, int k, int l, int i1, int j1);

	public abstract void notifyBlocksOfNeighborChange(int i, int j, int k, int l);

	public abstract boolean canBlockSeeTheSky(int i, int j, int k);

	public abstract int getBlockLightValue(int i, int j, int k);

	public abstract int getBlockLightValue(int i, int j, int k, boolean flag);

	public abstract boolean canExistingBlockSeeTheSky(int i, int j, int k);

	public abstract int getHeightValue(int i, int j);

	public abstract void neighborLightPropagationChanged(EnumSkyBlock enumskyblock, int i, int j, int k, int l);

	public abstract int getSavedLightValue(EnumSkyBlock enumskyblock, int i, int j, int k);

	public abstract void setLightValue(EnumSkyBlock enumskyblock, int i, int j, int k, int l);

	public abstract float getLightBrightness(int i, int j, int k);

	public abstract boolean isDaytime();

	public abstract MovingObjectPosition func_486_a(Vec3D vec3d, Vec3D vec3d1);

	public abstract MovingObjectPosition rayTraceBlocks(Vec3D vec3d, Vec3D vec3d1, boolean flag);

	public abstract void playSoundAtEntity(Entity entity, String s, float f, float f1);

	public abstract void playSoundEffect(double d, double d1, double d2, String s, 
			float f, float f1);

	public abstract void playRecord(String s, int i, int j, int k);

	public abstract void spawnParticle(String s, double d, double d1, double d2, 
			double d3, double d4, double d5);

	public abstract boolean entityJoinedWorld(Entity entity);
	public abstract void func_20109_d(Entity entity);

	public abstract void func_20110_e(Entity entity);

	public abstract void func_4072_a(IWorldAccess iworldaccess);

	public abstract List<?> getCollidingBoundingBoxes(Entity entity, AxisAlignedBB axisalignedbb);

	public abstract int calculateSkylightSubtracted(float f);

	public abstract float getCelestialAngle(float f);

	public abstract int func_4075_e(int i, int j);

	public abstract void scheduleBlockUpdate(int i, int j, int k, int l);

	public abstract void func_459_b();

	public abstract void updateEntity(Entity entity);

	public abstract void updateEntityWithOptionalForce(Entity entity, boolean flag);

	public abstract boolean checkIfAABBIsClear(AxisAlignedBB axisalignedbb);

	public abstract boolean getIsAnyLiquid(AxisAlignedBB axisalignedbb);

	public abstract boolean isBoundingBoxBurning(AxisAlignedBB axisalignedbb);

	public abstract boolean func_490_a(AxisAlignedBB axisalignedbb, Material material, Entity entity);

	public abstract boolean isMaterialInBB(AxisAlignedBB axisalignedbb, Material material);

	public abstract boolean func_524_b(AxisAlignedBB axisalignedbb, Material material);

	public abstract Explosion createExplosion(Entity entity, double d, double d1, double d2, 
			float f);

	public abstract Explosion func_12015_a(Entity entity, double d, double d1, double d2, 
			float f, boolean flag);

	public abstract float func_494_a(Vec3D vec3d, AxisAlignedBB axisalignedbb);

	public abstract TileEntity getBlockTileEntity(int i, int j, int k);

	public abstract void setBlockTileEntity(int i, int j, int k, TileEntity tileentity);

	public abstract void func_513_l(int i, int j, int k);

	public abstract boolean doesBlockAllowAttachment(int i, int j, int k);

	public abstract boolean func_6156_d();

	public abstract void func_483_a(EnumSkyBlock enumskyblock, int i, int j, int k, int l, int i1, int j1);

	public abstract void func_484_a(EnumSkyBlock enumskyblock, int i, int j, int k, int l, int i1, int j1, 
			boolean flag);

	public abstract void calculateInitialSkylight();

	public abstract void tick();
	
	public abstract boolean TickUpdates(boolean flag);

	public abstract List<?> getEntitiesWithinAABBExcludingEntity(Entity entity, AxisAlignedBB axisalignedbb);

	public abstract List<?> getEntitiesWithinAABB(Class<?> class1, AxisAlignedBB axisalignedbb);

	public abstract void func_515_b(int i, int j, int k, TileEntity tileentity);

	public abstract int countEntities(Class<?> class1);

	public abstract void func_464_a(List<?> list);

	public abstract void func_461_b(List<?> list);

	public abstract boolean canBlockBePlacedAt(int i, int j, int k, int l, boolean flag);

	public abstract PathEntity getPathToEntity(Entity entity, Entity entity1, float f);

	public abstract PathEntity getEntityPathToXYZ(Entity entity, int i, int j, int k, float f);

	public abstract boolean isBlockProvidingPowerTo(int i, int j, int k, int l);

	public abstract boolean isBlockGettingPowered(int i, int j, int k);

	public abstract boolean isBlockIndirectlyProvidingPowerTo(int i, int j, int k, int l);

	public abstract boolean isBlockIndirectlyGettingPowered(int i, int j, int k);

	public abstract EntityPlayer getClosestPlayerToEntity(Entity entity, double d);

	public abstract EntityPlayer getClosestPlayer(double d, double d1, double d2, double d3);

	public abstract byte[] func_504_c(int i, int j, int k, int l, int i1, int j1);

	public abstract void func_476_g();

	public abstract boolean func_6157_a(EntityPlayer entityplayer, int i, int j, int k);

	public abstract void func_9206_a(Entity entity, byte byte0);

	public boolean field_4280_a;
	//private List field_821_y;
	public List<?> loadedEntityList;
	//private List field_790_z;
	//private TreeSet scheduledTickTreeSet;
	//private Set scheduledTickSet;
	public List<?> loadedTileEntityList;
	public List<?> playerEntities;
	public long worldTime;
	//private long field_6159_E;
	public int skylightSubtracted;
	protected int field_4279_g;
	protected int field_4278_h;
	public boolean field_808_h;
	//private long field_784_F;
	protected int autosavePeriod;
	public int monstersEnabled;
	public Random rand;
	public int spawnX;
	public int spawnY;
	public int spawnZ;
	public boolean field_9212_p;
	public WorldProvider worldProvider;
	protected List<?> worldAccesses;
	//private IChunkProvider chunkProvider;
	public File field_9211_s;
	public File field_797_s;
	public long randomSeed;
	//private NBTTagCompound nbtCompoundPlayer;
	public long sizeOnDisk;
	public String field_9210_w;
	public boolean field_9209_x;
	//private ArrayList field_9207_I;
	//private int field_4265_J;
	static int field_4268_y = 0;
	//private Set field_4264_K;
	//private int field_4263_L;
	//private List field_778_L;
	public boolean multiplayerWorld;
}
