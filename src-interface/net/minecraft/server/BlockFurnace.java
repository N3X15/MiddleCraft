// AUTOMATICALLY GENERATED BY MIDDLECRAFT
/* Allows plugins to access server functions without needing to link the actual server Jar. */
package net.minecraft.server;

public abstract class BlockFurnace extends EntitySnowball{
	// FIELDS
	public int e;
	public int f;
	public int g;
	private il[] h;
	
	// METHODS
	
	/**
	 * *
	 */
	public int getSizeInventory()
	
	/**
	 * Returns the stack in slot i
	 */
	public BlockSponge getStackInSlot(int a)
	
	/**
	 * 
	 */
	public BlockSponge a(int a, int b)
	
	/**
	 * 
	 */
	public void a(int a, BlockSponge b)
	
	/**
	 * Reads a tile entity from NBT.
	 */
	public void readFromNBT(Packet1Login a)
	
	/**
	 * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't fuel
	 */
	private int getItemBurnTime(BlockSponge a)
	
	/**
	 * 
	 */
	public boolean a_(BlockLog a)
	
	/**
	 * 
	 */
	public java.lang.String b()
	
	/**
	 * Returns the item ID of the result of smelting the given item ID, or -1 if the given item can't be smelted.
	 */
	private int getSmeltingResultItem(int a)
	
	/**
	 * Writes a tile entity to NBT.
	 */
	public void writeToNBT(Packet1Login a)
	
	/**
	 * 
	 */
	public int c()
	
	/**
	 * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count ticks and creates a new spawn inside its implementation.
	 */
	public void updateEntity()
	
	/**
	 * Returns true if the furnace is currently burning
	 */
	public boolean isBurning()
	
	/**
	 * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
	 */
	public void smeltItem()
	
	/**
	 * Returns true if the furnace can smelt an item, i.e. has a source item, destination stack isn't full, etc.
	 */
	private boolean canSmelt()

}
