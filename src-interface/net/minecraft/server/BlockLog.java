// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class BlockLog extends lc{
	// FIELDS
	private int a;
	public double aA;
	public double aB;
	public double aC;
	public double aD;
	public lj aE;
	public WorldGenPumpkin an;
	public EntityArrow ao;
	public EntityArrow ap;
	public byte aq;
	public int ar;
	public float as;
	public float at;
	public boolean au;
	public int av;
	public java.lang.String aw;
	public int ax;
	public double ay;
	public double az;
	
	// METHODS
	
	/**
	 * 
	 */
	public double B()
	
	/**
	 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons use this to react to sunlight and start to burn.
	 */
	public void onLivingUpdate()
	
	/**
	 * 
	 */
	public void H()
	
	/**
	 * 
	 */
	protected void I()
	
	/**
	 * 
	 */
	public void L()
	
	/**
	 * 
	 */
	public BlockSponge M()
	
	/**
	 * 
	 */
	public void N()
	
	/**
	 * 
	 */
	public void a(int a, int b, int c)
	
	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(Packet1Login a)
	
	/**
	 * Displays the furnace GUI for the passed in furnace entity. Args: tileEntityFurnace
	 */
	public void displayGUIFurnace(BlockFurnace a)
	
	/**
	 * Called when the entity is attacked.
	 */
	public boolean attackEntity(IMobs a, int b)
	
	/**
	 * Returns how strong the player is against the specified block at this moment
	 */
	public float getCurrentPlayerStrVsBlock(BlockSoil a)
	
	/**
	 * 
	 */
	protected void a(MathHelper a)
	
	/**
	 * 
	 */
	public void a(BlockSponge a)
	
	/**
	 * *
	 */
	public void dropPlayerItemWithRandomChoice(BlockSponge a, boolean b)
	
	/**
	 * 
	 */
	public void a(kp a)
	
	/**
	 * 
	 */
	public void a(lg a)
	
	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(Packet1Login a)
	
	/**
	 * *
	 */
	public void addToPlayerScore(IMobs a, int b)
	
	/**
	 * 
	 */
	public boolean b(BlockSoil a)
	
	/**
	 * 
	 */
	public void b(BlockSponge a)
	
	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate()
	
	/**
	 * 
	 */
	protected void c()
	
	/**
	 * 
	 */
	public void c(IMobs a, int b)
	
	/**
	 * 
	 */
	protected void d(int a)
	
	/**
	 * Called when the mob's health reaches 0.
	 */
	public void onDeath(IMobs a)
	
	/**
	 * 
	 */
	public void g(IMobs a)
	
	/**
	 * 
	 */
	public void h(IMobs a)
	
	/**
	 * 
	 */
	private void j(IMobs a)
	
	/**
	 * Will get destroyed next tick
	 */
	public void setEntityDead()
	
	/**
	 * 
	 */
	public float s()
	
	/**
	 * 
	 */
	public void z()

}
