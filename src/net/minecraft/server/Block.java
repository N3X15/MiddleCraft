package com.minecraft.server;

import java.util.ArrayList;
import java.util.Random;

/**
 * Copyright (c) 2010, MiddleCraft Contributors
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * 
 *  * Redistributions of source code must retain the above copyright notice, this list of 
 *    conditions and the following disclaimer.
 * 
 *  * Redistributions in binary form must reproduce the above copyright notice, this list 
 *    of conditions and the following disclaimer in the documentation and/or other materials 
 *    provided with the distribution.
 * 
 *  * Neither the name of MiddleCraft nor the names of its contributors may be 
 *    used to endorse or promote products derived from this software without specific prior 
 *    written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY 
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT 
 * SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, 
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT 
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE 
 * USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/**
 * Interface for the server's World class
 * @author Rob
 *
 */
public abstract class Block {

	protected Block(int i, Material material) {
	}

	protected Block(int i, int j, Material material) {
	}

	protected abstract Block setStepSound(StepSound stepsound);

	protected abstract Block setLightOpacity(int i);

	protected abstract Block setLightValue(float f);

	protected abstract Block setResistance(float f);

	//private abstract boolean unusedMethod();

	protected abstract Block setHardness(float f);

	protected abstract void setTickOnLoad(boolean flag);

	public abstract void setBlockBounds(float f, float f1, float f2, float f3, float f4, float f5);

	public abstract boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l);

	public abstract int getBlockTextureFromSide(int i);

	public abstract void getCollidingBoundingBoxes(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, ArrayList<?> arraylist);

	public abstract AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k);

	public abstract boolean isOpaqueCube();

	public abstract boolean canCollideCheck(int i, boolean flag);

	public abstract boolean isCollidable();

	public abstract void updateTick(World world, int i, int j, int k, Random random);

	public abstract void onBlockDestroyedByPlayer(World world, int i, int j, int k, int l);

	public abstract void onNeighborBlockChange(World world, int i, int j, int k, int l);

	public abstract int tickRate();

	public abstract void onBlockAdded(World world, int i, int j, int k);

	public abstract void onBlockRemoval(World world, int i, int j, int k);

	public abstract int quantityDropped(Random random);

	public abstract int idDropped(int i, Random random);

	public abstract float func_254_a(EntityPlayer entityplayer);

	public abstract void dropBlockAsItem(World world, int i, int j, int k, int l);

	public abstract void dropBlockAsItemWithChance(World world, int i, int j, int k, int l, float f);

	public abstract float getExplosionResistance(Entity entity);

	public abstract MovingObjectPosition collisionRayTrace(World world, int i, int j, int k, Vec3D vec3d, Vec3D vec3d1);

	//private abstract boolean isVecInsideYZBounds(Vec3D vec3d);

	//private abstract boolean isVecInsideXZBounds(Vec3D vec3d);

	//private abstract boolean isVecInsideXYBounds(Vec3D vec3d);

	public abstract void onBlockDestroyedByExplosion(World world, int i, int j, int k);

	public abstract boolean canPlaceBlockAt(World world, int i, int j, int k);

	public abstract boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer);

	public abstract void onEntityWalking(World world, int i, int j, int k, Entity entity);

	public abstract void onBlockPlaced(World world, int i, int j, int k, int l);

	public abstract void onBlockClicked(World world, int i, int j, int k, EntityPlayer entityplayer);

	public abstract void velocityToAddToEntity(World world, int i, int j, int k, Entity entity, Vec3D vec3d);

	public abstract void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i, int j, int k);

	public abstract boolean isPoweringTo(IBlockAccess iblockaccess, int i, int j, int k, int l);

	public abstract boolean canProvidePower();

	public abstract void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity);

	public abstract boolean isIndirectlyPoweringTo(World world, int i, int j, int k, int l);

	public abstract void harvestBlock(World world, int i, int j, int k, int l);

	public abstract boolean canBlockStay(World world, int i, int j, int k);

	public abstract void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving);

	public abstract Block func_20037_a(String s);

	public abstract String func_20036_e();

	public static StepSound soundPowderFootstep;
	public static StepSound soundWoodFootstep;
	public static StepSound soundGravelFootstep;
	public static StepSound soundGrassFootstep;
	public static StepSound soundStoneFootstep;
	public static StepSound soundMetalFootstep;
	public static StepSound soundGlassFootstep;
	public static StepSound soundClothFootstep;
	public static StepSound soundSandFootstep;
	public static Block blocksList[];
	public static boolean tickOnLoad[] = new boolean[256];
	public static boolean opaqueCubeLookup[] = new boolean[256];
	public static boolean isBlockContainer[] = new boolean[256];
	public static int lightOpacity[] = new int[256];
	public static boolean unusedBooleanArray[] = new boolean[256];
	public static int lightValue[] = new int[256];
	public static Block stone;
	public static BlockGrass grass;
	public static Block dirt;
	public static Block cobblestone;
	public static Block planks;
	public static Block sapling;
	public static Block bedrock;
	public static Block waterStill;
	public static Block waterMoving;
	public static Block lavaStill;
	public static Block lavaMoving;
	public static Block sand;
	public static Block gravel;
	public static Block oreGold;
	public static Block oreIron;
	public static Block oreCoal;
	public static Block wood;
	public static BlockLeaves leaves;
	public static Block sponge;
	public static Block glass;
	public static Block field_9042_N = null;
	public static Block field_9041_O = null;
	public static Block field_9040_P = null;
	public static Block field_9039_Q = null;
	public static Block field_9038_R = null;
	public static Block field_9037_S = null;
	public static Block field_9036_T = null;
	public static Block field_9034_U = null;
	public static Block field_9033_V = null;
	public static Block field_9032_W = null;
	public static Block field_9031_X = null;
	public static Block field_9030_Y = null;
	public static Block field_9029_Z = null;
	public static Block field_9049_aa = null;
	public static Block cloth;
	public static Block field_9048_ac = null;
	public static BlockFlower plantYellow;
	public static BlockFlower plantRed;
	public static BlockFlower mushroomBrown;
	public static BlockFlower mushroomRed;
	public static Block blockGold;
	public static Block blockSteel;
	public static Block stairDouble;
	public static Block stairSingle;
	public static Block brick;
	public static Block tnt;
	public static Block bookShelf;
	public static Block cobblestoneMossy;
	public static Block obsidian;
	public static Block torchWood;
	public static BlockFire fire;
	public static Block mobSpawner;
	public static Block stairCompactPlanks;
	public static Block crate;
	public static Block redstoneWire;
	public static Block oreDiamond;
	public static Block blockDiamond;
	public static Block workbench;
	public static Block crops;
	public static Block tilledField;
	public static Block stoneOvenIdle;
	public static Block stoneOvenActive;
	public static Block signPost;
	public static Block doorWood;
	public static Block ladder;
	public static Block minecartTrack;
	public static Block stairCompactCobblestone;
	public static Block signWall;
	public static Block lever;
	public static Block pressurePlateStone;
	public static Block doorSteel;
	public static Block pressurePlatePlanks;
	public static Block oreRedstone;
	public static Block oreRedstoneGlowing;
	public static Block torchRedstoneIdle;
	public static Block torchRedstoneActive;
	public static Block button;
	public static Block snow;
	public static Block ice;
	public static Block blockSnow;
	public static Block cactus;
	public static Block blockClay;
	public static Block reed;
	public static Block jukebox;
	public static Block fence;
	public static Block pumpkin;
	public static Block bloodStone;
	public static Block slowSand;
	public static Block lightStone;
	public static BlockPortal portal;
	public static Block pumpkinLantern;
	public int blockIndexInTexture;
	public int blockID;
	protected float blockHardness;
	protected float blockResistance;
	public double minX;
	public double minY;
	public double minZ;
	public double maxX;
	public double maxY;
	public double maxZ;
	public StepSound stepSound;
	public float field_554_bm;
	public Material blockMaterial;
	public float slipperiness;
	//private String field_666_a;
}
