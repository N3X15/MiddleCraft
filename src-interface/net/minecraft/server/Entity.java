// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class Entity {
	// FIELDS
	public boolean onGround;
	public boolean isTryingToMoveUp;
	public boolean field_9082;
	public boolean field_9080;
	public boolean field_9078;
	public boolean field_9077;
	public boolean isDead;
	public float yOffset;
	public float width;
	public float height;
	public float field_9075;
	public float field_9074;
	protected boolean entityWalks;
	protected float fallDistance;
	public double lastTickPosX;
	public double lastTickPosY;
	public double lastTickPosZ;
	public float field_9068;
	public float field_9067;
	public boolean field_9066;
	public float field_286;
	public boolean field_9065;
	protected java.util.Random rand;
	public int field_9063;
	public int field_9062;
	public int fire;
	private static int field_384;
	protected int maxAir;
	protected boolean field_9085;
	public int field_9083;
	public int air;
	protected boolean isImmuneToFire;
	public boolean field_276;
	public int field_307;
	public int field_305;
	public int field_303;
	private int field_6151;
	private boolean field_4131;
	private double field_4130;
	private double field_4128;
	public int field_331;
	public double renderDistanceWeight;
	public boolean field_329;
	public Entity riddenByEntity;
	public Entity ridingEntity;
	public World worldObj;
	public double prevPosX;
	public double prevPosY;
	public double prevPosZ;
	public double posX;
	public double posY;
	public double posZ;
	public double motionX;
	public double motionY;
	public double motionZ;
	public float rotationYaw;
	public float rotationPitch;
	public float prevRotationYaw;
	public float prevRotationPitch;
	public AxisAlignedBB boundingBox;
	
	// METHODS
	
	/**
	 * *
	 */
	public abstract void func_127();
	
	/**
	 * *
	 */
	public abstract double func_117();
	
	/**
	 * *
	 */
	public abstract Vec3D func_4039();
	
	/**
	 * *
	 */
	public abstract void func_4042();
	
	/**
	 * *
	 */
	public abstract int[] func_20042();
	
	/**
	 * Sets the x,y,z of the entity from the given parameters. Also seems to set up a bounding box.
	 */
	public abstract void setPosition(double a, double b, double c);
	
	/**
	 * Takes in the distance the entity has fallen this tick and whether its on the ground to update the fall distance and deal fall damage if landing on the ground.  Args: distanceFallenThisTick, onGround
	 */
	protected abstract void updateFallState(double a, boolean b);
	
	/**
	 * Called when the mob is falling. Calculates and applies fall damage.
	 */
	protected abstract void fall(float a);
	
	/**
	 * Sets the width and height of the entity. Args: width, height
	 */
	protected abstract void setSize(float a, float b);
	
	/**
	 * *
	 */
	public abstract void func_90(float a, float b, float c);
	
	/**
	 * Drops an item stack with a specified y offset. Args: itemID, count, yOffset
	 */
	public abstract EntityItem dropItemWithOffset(int a, int b, float c);
	
	/**
	 * 
	 */
	protected abstract void MIDDLECRAFT_func_328_a(NBTTagCompound a);
	
	/**
	 * Returns the distance to the entity. Args: entity
	 */
	public abstract float getDistanceToEntity(Entity a);
	
	/**
	 * 
	 */
	public abstract boolean MIDDLECRAFT_func_325_a(Entity a, int b);
	
	/**
	 * 
	 */
	public abstract boolean MIDDLECRAFT_func_329_a(EntityPlayer a);
	
	/**
	 * Checks if the current block the entity is within of the specified material type
	 */
	public abstract boolean isInsideOfMaterial(Material a);
	
	/**
	 * *
	 */
	public abstract boolean isOffsetPositionInLiquid(double a, double b, double c);
	
	/**
	 * Sets the entity's position and rotation. Args: posX, posY, posZ, yaw, pitch
	 */
	public abstract void setPositionAndRotation(double a, double b, double c, float d, float e);
	
	/**
	 * Gets how bright this entity is.
	 */
	public abstract float getEntityBrightness(float a);
	
	/**
	 * Sets the rotation of the entity
	 */
	protected abstract void setRotation(float a, float b);
	
	/**
	 * Will deal the specified amount of damage to the entity if the entity isn't immune to fire damage. Args: amountDamage
	 */
	protected abstract void dealFireDamage(int a);
	
	/**
	 * Drops an item stack at the entity's position. Args: itemID, count
	 */
	public abstract EntityItem dropItem(int a, int b);
	
	/**
	 * 
	 */
	protected abstract void MIDDLECRAFT_func_327_b(NBTTagCompound a);
	
	/**
	 * Returns the squared distance to the entity. Args: entity
	 */
	public abstract double getDistanceSqToEntity(Entity a);
	
	/**
	 * *
	 */
	public abstract void addToPlayerScore(Entity a, int b);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_324_b(EntityPlayer a);
	
	/**
	 * 
	 */
	public abstract void MIDDLECRAFT_func_323_b_();
	
	/**
	 * Tries to moves the entity by the passed in displacement. Args: x, y, z
	 */
	public abstract void moveEntity(double a, double b, double c);
	
	/**
	 * *
	 */
	public abstract void setLocationAndAngles(double a, double b, double c, float d, float e);
	
	/**
	 * *
	 */
	public abstract boolean func_95(NBTTagCompound a);
	
	/**
	 * Applies a velocity to each of the entities pushing them away from each other. Args: entity
	 */
	public abstract void applyEntityCollision(Entity a);
	
	/**
	 * 
	 */
	public abstract boolean MIDDLECRAFT_func_326_c_();
	
	/**
	 * Gets the squared distance to the position. Args: x, y, z
	 */
	public abstract double getDistanceSq(double a, double b, double c);
	
	/**
	 * Save the entity to NBT (calls an abstract helper method to write extra data)
	 */
	public abstract void writeToNBT(NBTTagCompound a);
	
	/**
	 * *
	 */
	public abstract AxisAlignedBB func_89(Entity a);
	
	/**
	 * Gets the distance to the position. Args: x, y, z
	 */
	public abstract double getDistance(double a, double b, double c);
	
	/**
	 * Reads the entity from NBT (calls an abstract helper method to read specialized data)
	 */
	public abstract void readFromNBT(NBTTagCompound a);
	
	/**
	 * set entity to null to unmount
	 */
	public abstract void mountEntity(Entity a);
	
	/**
	 * *
	 */
	public abstract boolean ep_equals(java.lang.Object a);
	
	/**
	 * Adds to the current velocity of the entity. Args: x, y, z
	 */
	public abstract void addVelocity(double a, double b, double c);
	
	/**
	 * *
	 */
	public abstract int ep_hashCode();
	
	/**
	 * 
	 */
	public abstract double MIDDLECRAFT_func_330_j();
	
	/**
	 * Will get destroyed next tick
	 */
	public abstract void setEntityDead();
	
	/**
	 * *
	 */
	public abstract void func_84();
	
	/**
	 * Called whenever the entity is walking inside of lava.
	 */
	protected abstract void setOnFireFromLava();
	
	/**
	 * *
	 */
	protected abstract void func_4043();
	
	/**
	 * Returns if this entity is sneaking.
	 */
	public abstract boolean isSneaking();
	
	/**
	 * *
	 */
	public abstract AxisAlignedBB func_93();
	
	/**
	 * Returns if this entity is in water and will end up adding the waters velocity to the entity
	 */
	public abstract boolean handleWaterMovement();
	
	/**
	 * *
	 */
	public abstract float func_104();
	
	/**
	 * *
	 */
	public abstract boolean handleLavaMovement();
	
	/**
	 * *
	 */
	protected abstract void func_9060();
	
	/**
	 * *
	 */
	public abstract boolean canBePushed();
	
	/**
	 * *
	 */
	protected abstract java.lang.String getEntityString();
	
	/**
	 * *
	 */
	public abstract boolean isEntityAlive();
	
	/**
	 * *
	 */
	public abstract boolean func_91();
	
	/**
	 * *
	 */
	public abstract void func_115();

}
