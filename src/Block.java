
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
 * @author Rob
 *
 */
public class Block {

	private int id;
	private String name;
	
    /**
	 * @param i
	 * @param string
	 */
	public Block(int i, String string) {
		id=i;
		name=string;
	}
	
	public int getType() { return id; }
	public String getName() { return name; }
	public static class Type
	{
		public static final Block Air = new Block(0,"Air");
	    public static final Block Rock = new Block(1,"Rock");
	    public static final Block Grass = new Block(2,"Grass");
	    public static final Block Dirt = new Block(3,"Dirt");
	    public static final Block Cobblestone = new Block(4,"Cobblestone");
	    public static final Block Wood = new Block(5,"Wood");
	    public static final Block Sapling = new Block(6,"Sapling");
	    public static final Block Adminium = new Block(7,"Adminium");
	    public static final Block Water = new Block(8,"Water");
	    public static final Block StillWater = new Block(9,"Still water");
	    public static final Block Lava = new Block(10,"Lava");
	    public static final Block StillLava = new Block(11,"Still lava");
	    public static final Block Sand = new Block(12,"Sand");
	    public static final Block Gravel = new Block(13,"Gravel");
	    public static final Block GoldOre = new Block(14,"Gold ore");
	    public static final Block IronOre = new Block(15,"Iron ore");
	    public static final Block CoalOre = new Block(16,"Coal ore");
	    public static final Block Tree = new Block(17,"Tree");
	    public static final Block Leaves = new Block(18,"Leaves");
	    public static final Block Sponge = new Block(19,"Sponge");
	    public static final Block Glass = new Block(20,"Glass");
	    public static final Block Cloth = new Block(35,"Cloth");
	    public static final Block Flower = new Block(37,"Flower");
	    public static final Block Rose = new Block(38,"Rose");
	    public static final Block BrownMushroom = new Block(39,"Brown mushroom");
	    public static final Block RedMushroom = new Block(40,"Red mushroom");
	    public static final Block GoldBlock = new Block(41,"Gold block");
	    public static final Block IronBlock = new Block(42,"Iron block");
	    public static final Block DoubleStoneSlab = new Block(43,"Double stone slab");
	    public static final Block StoneSlab = new Block(44,"Stone slab");
	    public static final Block Brick = new Block(45,"Brick");
	    public static final Block TNT = new Block(46,"TNT");
	    public static final Block Bookshelf = new Block(47,"Bookshelf");
	    public static final Block MossyCobblestone = new Block(48,"Mossy cobblestone");
	    public static final Block Obsidian = new Block(49,"Obsidian");
	    public static final Block Torch = new Block(50,"Torch");
	    public static final Block Fire = new Block(51,"Fire");
	    public static final Block MobSpawner = new Block(52,"Mob spawner");
	    public static final Block WoodStairs = new Block(53,"Wood stairs");
	    public static final Block Chest = new Block(54,"Chest");
	    public static final Block RedstoneDust = new Block(55,"Redstone dust");
	    public static final Block DiamondOre = new Block(56,"Diamond ore");
	    public static final Block Diamond = new Block(57,"Diamond");
	    public static final Block Workbench = new Block(58,"Workbench");
	    public static final Block Crop = new Block(59,"Crop");
	    public static final Block Soil = new Block(60,"Soil");
	    public static final Block Furnace = new Block(61,"Furnace");
	    public static final Block LitFurnace = new Block(62,"Lit furnace");
	    public static final Block SignBlock = new Block(63,"Sign block");
	    public static final Block WoodDoorBlock = new Block(64,"Wood door block");
	    public static final Block Ladder = new Block(65,"Ladder");
	    public static final Block Rails = new Block(66,"Rails");
	    public static final Block CobblestoneStairs = new Block(67,"Cobblestone stairs");
	    public static final Block WallSign = new Block(68,"Wall sign");
	    public static final Block Lever = new Block(69,"Lever");
	    public static final Block RockPressurePlate = new Block(70,"Rock pressure plate");
	    public static final Block IronDoorBlock = new Block(71,"Iron door block");
	    public static final Block WoodPressurePlate = new Block(72,"Wood pressure plate");
	    public static final Block RedstoneOre  = new Block(73,"Redstone ore ");
	    public static final Block LitRedstoneOre = new Block(74,"Lit redstone ore");
	    public static final Block RedstoneTorch = new Block(75,"Redstone torch");
	    public static final Block LitRedstoneTorch = new Block(76,"Lit redstone torch");
	    public static final Block Button = new Block(77,"Button");
	    public static final Block Snow = new Block(78,"Snow");
	    public static final Block Ice = new Block(79,"Ice");
	    public static final Block SnowBlock = new Block(80,"Snow block");
	    public static final Block Cactus = new Block(81,"Cactus");
	    public static final Block ClayBlock = new Block(82,"Clay block");
	    public static final Block ReedBlock = new Block(83,"Reed block");
	    public static final Block Jukebox = new Block(84,"Jukebox");
	    public static final Block Fence = new Block(85,"Fence");
	    public static final Block Pumpkin = new Block(86,"Pumpkin");
	    public static final Block Netherstone = new Block(87,"Netherstone");
	    public static final Block SlowSand = new Block(88,"Slow sand");
	    public static final Block Lightstone = new Block(89,"Lightstone");
	    public static final Block Portal = new Block(90,"Portal");
	    public static final Block JackOLantern = new Block(91,"Jack-o'-lantern");

	}

}
