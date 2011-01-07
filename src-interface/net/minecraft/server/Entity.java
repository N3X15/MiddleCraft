// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class Entity{
	// FIELDS
	public boolean A;
	public boolean B;
	public boolean C;
	public boolean D;
	public boolean E;
	public boolean F;
	public boolean G;
	public float H;
	public float I;
	public float J;
	public float K;
	public float L;
	protected boolean M;
	protected float N;
	public double O;
	public double P;
	public double Q;
	public float R;
	public float S;
	public boolean T;
	public float U;
	public boolean V;
	protected java.util.Random W;
	public int X;
	public int Y;
	public int Z;
	private static int a;
	protected int aa;
	protected boolean ab;
	public int ac;
	public int ad;
	protected boolean ae;
	public boolean af;
	public int ag;
	public int ah;
	public int ai;
	private int b;
	private boolean c;
	private double d;
	private double e;
	 ()V setChunkModified;
	public int g;
	public double h;
	public boolean i;
	public Entity j;
	public Entity k;
	public World l;
	public double m;
	public double n;
	public double o;
	public double p;
	public double q;
	public double r;
	public double s;
	public double t;
	public double u;
	public float v;
	public float w;
	public float x;
	public float y;
	public final AxisAlignedBB z;
	
	// METHODS
	
	/**
	 * *
	 */
	 void func_127();
	
	/**
	 * 
	 */
	public void A();
	
	/**
	 * *
	 */
	 double func_117();
	
	/**
	 * 
	 */
	public double B();
	
	/**
	 * *
	 */
	 Vec3D func_4039();
	
	/**
	 * 
	 */
	public Vec3D C();
	
	/**
	 * *
	 */
	 void func_4042();
	
	/**
	 * 
	 */
	public void D();
	
	/**
	 * *
	 */
	 int[] func_20042();
	
	/**
	 * 
	 */
	public int[] E();
	
	/**
	 * Drops an item stack with a specified y offset. Args: itemID, count, yOffset
	 */
	 EntityItem dropItemWithOffset(int a, int b, float c);
	
	/**
	 * 
	 */
	public void a(double a, double b, double c);
	
	/**
	 * 
	 */
	protected void a(double a, boolean b);
	
	/**
	 * 
	 */
	protected void a(float a);
	
	/**
	 * 
	 */
	protected void a(float a, float b);
	
	/**
	 * 
	 */
	public void a(float a, float b, float c);
	
	/**
	 * 
	 */
	public EntityItem a(int a, int b, float c);
	
	/**
	 * 
	 */
	protected abstract void a(NBTTagCompound a);
	
	/**
	 * 
	 */
	public float a(Entity a);
	
	/**
	 * 
	 */
	public boolean a(Entity a, int b);
	
	/**
	 * 
	 */
	public boolean a(EntityPlayer a);
	
	/**
	 * 
	 */
	public boolean a(Material a);
	
	/**
	 * 
	 */
	protected transient NBTTagList a(double[] a);
	
	/**
	 * 
	 */
	protected transient NBTTagList a(float[] a);
	
	/**
	 * *
	 */
	 void addToPlayerScore(Entity a, int b);
	
	/**
	 * 
	 */
	public boolean b(double a, double b, double c);
	
	/**
	 * 
	 */
	public void b(double a, double b, double c, float d, float e);
	
	/**
	 * 
	 */
	public float b(float a);
	
	/**
	 * 
	 */
	protected void b(float a, float b);
	
	/**
	 * 
	 */
	protected void b(int a);
	
	/**
	 * 
	 */
	public EntityItem b(int a, int b);
	
	/**
	 * 
	 */
	protected abstract void b(NBTTagCompound a);
	
	/**
	 * 
	 */
	public double b(Entity a);
	
	/**
	 * 
	 */
	public void b(Entity a, int b);
	
	/**
	 * 
	 */
	public void b(EntityPlayer a);
	
	/**
	 * 
	 */
	public void b_();
	
	/**
	 * Applies a velocity to each of the entities pushing them away from each other. Args: entity
	 */
	 void applyEntityCollision(Entity a);
	
	/**
	 * 
	 */
	public void c(double a, double b, double c);
	
	/**
	 * 
	 */
	public void c(double a, double b, double c, float d, float e);
	
	/**
	 * 
	 */
	public boolean c(NBTTagCompound a);
	
	/**
	 * 
	 */
	public void c(Entity a);
	
	/**
	 * 
	 */
	public boolean c_();
	
	/**
	 * *
	 */
	 AxisAlignedBB func_89(Entity a);
	
	/**
	 * 
	 */
	public double d(double a, double b, double c);
	
	/**
	 * 
	 */
	public void d(NBTTagCompound a);
	
	/**
	 * 
	 */
	public AxisAlignedBB d(Entity a);
	
	/**
	 * Gets the distance to the position. Args: x, y, z
	 */
	 double getDistance(double a, double b, double c);
	
	/**
	 * 
	 */
	public double e(double a, double b, double c);
	
	/**
	 * 
	 */
	public void e(NBTTagCompound a);
	
	/**
	 * 
	 */
	public void e(Entity a);
	
	/**
	 * *
	 */
	 boolean ep_equals(java.lang.Object a);
	
	/**
	 * 
	 */
	public boolean equals(java.lang.Object a);
	
	/**
	 * Adds to the current velocity of the entity. Args: x, y, z
	 */
	 void addVelocity(double a, double b, double c);
	
	/**
	 * 
	 */
	public void f(double a, double b, double c);
	
	/**
	 * *
	 */
	 int ep_hashCode();
	
	/**
	 * 
	 */
	public int hashCode();
	
	/**
	 * 
	 */
	public double j();
	
	/**
	 * Will get destroyed next tick
	 */
	 void setEntityDead();
	
	/**
	 * 
	 */
	public void l();
	
	/**
	 * *
	 */
	 void func_84();
	
	/**
	 * 
	 */
	public void m();
	
	/**
	 * Called whenever the entity is walking inside of lava.
	 */
	 void setOnFireFromLava();
	
	/**
	 * 
	 */
	protected void n();
	
	/**
	 * *
	 */
	 void func_4043();
	
	/**
	 * 
	 */
	protected void o();
	
	/**
	 * Returns if this entity is sneaking.
	 */
	 boolean isSneaking();
	
	/**
	 * 
	 */
	public boolean p();
	
	/**
	 * *
	 */
	 AxisAlignedBB func_93();
	
	/**
	 * 
	 */
	public AxisAlignedBB q();
	
	/**
	 * Returns if this entity is in water and will end up adding the waters velocity to the entity
	 */
	 boolean handleWaterMovement();
	
	/**
	 * 
	 */
	public boolean r();
	
	/**
	 * *
	 */
	 float func_104();
	
	/**
	 * 
	 */
	public float s();
	
	/**
	 * *
	 */
	 boolean handleLavaMovement();
	
	/**
	 * 
	 */
	public boolean t();
	
	/**
	 * *
	 */
	 void func_9060();
	
	/**
	 * 
	 */
	protected void u();
	
	/**
	 * *
	 */
	 boolean canBePushed();
	
	/**
	 * 
	 */
	public boolean v();
	
	/**
	 * *
	 */
	 java.lang.String getEntityString();
	
	/**
	 * 
	 */
	protected final java.lang.String w();
	
	/**
	 * *
	 */
	 boolean isEntityAlive();
	
	/**
	 * 
	 */
	public boolean x();
	
	/**
	 * *
	 */
	 boolean func_91();
	
	/**
	 * 
	 */
	public boolean y();
	
	/**
	 * *
	 */
	 void func_115();
	
	/**
	 * 
	 */
	public void z();

}
