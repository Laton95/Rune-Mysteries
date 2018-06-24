package com.laton95.runemysteries.init;

import com.laton95.runemysteries.block.*;
import com.laton95.runemysteries.block.RMModSlab.Half;
import com.laton95.runemysteries.util.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ModBlocks
{
	
	public static final BlockRuneAltar RUNE_ALTAR = new BlockRuneAltar();
	
	public static final BlockRuneAltarEntrance RUNE_ALTAR_ENTRANCE = new BlockRuneAltarEntrance();
	
	public static final BlockAltarPortal ALTAR_PORTAL = new BlockAltarPortal("altar_Exit_Portal");
	
	public static final BlockRuneEssence RUNE_ESSENCE = new BlockRuneEssence();
	
	public static final RMModBlock RUIN_BLOCK = new RMModBlock("ruin_Block", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true, true);
	
	public static final BlockStationStone STATION_STONE = new BlockStationStone();
	
	public static final RMModBlock TEMPLE_BLOCK = new RMModBlock("temple_Block", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true, true);
	
	public static final RMModStairs TEMPLE_BLOCK_STAIRS = new RMModStairs("temple_Block_Stairs", "pickaxe", 1, true, TEMPLE_BLOCK.getDefaultState());
	
	public static final RMModSlab.Half TEMPLE_BLOCK_SLAB = new RMModSlab.Half("temple_Block_Slab", Material.ROCK, .5f, 10.0f, "pickaxe", 1, true);
	
	public static final RMModRail TEMPLE_BLOCK_RAIL = new RMModRail("temple_Block_Rail", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	
	public static final RMModBlock BLOOD_BLOCK = new RMModBlock("blood_Block", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true, true);
	
	public static final RMModStairs BLOOD_BLOCK_STAIRS = new RMModStairs("blood_Block_Stairs", "pickaxe", 1, true, BLOOD_BLOCK.getDefaultState());
	
	public static final RMModSlab.Half BLOOD_BLOCK_SLAB = new RMModSlab.Half("blood_Block_Slab", Material.ROCK, .5f, 10.0f, "pickaxe", 1, true);
	
	public static final RMModRail BLOOD_BLOCK_RAIL = new RMModRail("blood_Block_Rail", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	
	public static final RMRandomRotationBlock FLESH_BLOCK = new RMRandomRotationBlock("flesh_Block", Material.CAKE, 1.5f, 10.0f, "pickaxe", 1, true, true);
	
	public static final RMModRail STONEBRICK_RAIL = new RMModRail("stonebrick_Rail", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	
	public static final RMModRail NETHERBRICK_RAIL = new RMModRail("netherbrick_Rail", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	
	public static final RMModRail PRISMARINE_RAIL = new RMModRail("prismarine_Rail", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	
	public static final RMModRail PURPUR_RAIL = new RMModRail("purpur_Rail", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	
	public static final RMModRail QUARTZ_RAIL = new RMModRail("quartz_Rail", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	
	public static final RMModRail SANDSTONE_RAIL = new RMModRail("sandstone_Rail", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	
	public static final BlockParticleLight PARTICLE_LIGHT = new BlockParticleLight();
	
	public static final BlockBlackMonolith BLACK_MONOLITH = new BlockBlackMonolith();
	
	public static final BlockElderStone ELDER_STONE = new BlockElderStone();
	
	public static final BlockElementalObelisk ELEMENTAL_OBELISK = new BlockElementalObelisk();
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		LogHelper.info("Registering blocks");
		
		Block[] blocks = {
				RUNE_ALTAR,
				RUNE_ALTAR_ENTRANCE,
				ALTAR_PORTAL,
				RUNE_ESSENCE,
				RUIN_BLOCK,
				STATION_STONE,
				TEMPLE_BLOCK,
				TEMPLE_BLOCK_STAIRS,
				TEMPLE_BLOCK_SLAB,
				TEMPLE_BLOCK_RAIL,
				BLOOD_BLOCK,
				BLOOD_BLOCK_STAIRS,
				BLOOD_BLOCK_SLAB,
				BLOOD_BLOCK_RAIL,
				FLESH_BLOCK,
				STONEBRICK_RAIL,
				NETHERBRICK_RAIL,
				PRISMARINE_RAIL,
				PURPUR_RAIL,
				QUARTZ_RAIL,
				SANDSTONE_RAIL,
				PARTICLE_LIGHT,
				BLACK_MONOLITH,
				ELDER_STONE,
				ELEMENTAL_OBELISK
		};
		
		for(Block block : blocks)
		{
			if(block instanceof RMModSlab)
			{
				RMModSlab.Half slab = (Half) block;
				RMModSlab.Double doubleSlab = slab.doubleSlab;
				ModItems.addBlock(block);
				event.getRegistry().register(slab);
				event.getRegistry().register(doubleSlab);
			}
			else if(block instanceof RMModBlock && !((RMModBlock) block).hasItem)
			{
				event.getRegistry().register(block);
			}
			else
			{
				ModItems.addBlock(block);
				event.getRegistry().register(block);
			}
		}
	}
}
