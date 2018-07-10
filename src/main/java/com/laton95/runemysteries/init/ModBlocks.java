package com.laton95.runemysteries.init;

import com.laton95.runemysteries.block.*;
import com.laton95.runemysteries.block.RMModSlab.Half;
import com.laton95.runemysteries.enums.EnumColour;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.util.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ModBlocks
{
	
	public static final BlockRuneAltar AIR_ALTAR = new BlockRuneAltar(EnumRuneType.AIR);
	
	public static final BlockRuneAltar ASTRAL_ALTAR = new BlockRuneAltar(EnumRuneType.ASTRAL);
	
	public static final BlockRuneAltar BLOOD_ALTAR = new BlockRuneAltar(EnumRuneType.BLOOD);
	
	public static final BlockRuneAltar BODY_ALTAR = new BlockRuneAltar(EnumRuneType.BODY);
	
	public static final BlockRuneAltar CHAOS_ALTAR = new BlockRuneAltar(EnumRuneType.CHAOS);
	
	public static final BlockRuneAltar COSMIC_ALTAR = new BlockRuneAltar(EnumRuneType.COSMIC);
	
	public static final BlockRuneAltar DEATH_ALTAR = new BlockRuneAltar(EnumRuneType.DEATH);
	
	public static final BlockRuneAltar EARTH_ALTAR = new BlockRuneAltar(EnumRuneType.EARTH);
	
	public static final BlockRuneAltar FIRE_ALTAR = new BlockRuneAltar(EnumRuneType.FIRE);
	
	public static final BlockRuneAltar LAW_ALTAR = new BlockRuneAltar(EnumRuneType.LAW);
	
	public static final BlockRuneAltar MIND_ALTAR = new BlockRuneAltar(EnumRuneType.MIND);
	
	public static final BlockRuneAltar NATURE_ALTAR = new BlockRuneAltar(EnumRuneType.NATURE);
	
	public static final BlockRuneAltar SOUL_ALTAR = new BlockRuneAltar(EnumRuneType.SOUL);
	
	public static final BlockRuneAltar WATER_ALTAR = new BlockRuneAltar(EnumRuneType.WATER);
	
	public static final BlockRuneAltar OURANIA_ALTAR = new BlockRuneAltar();
	
	public static final BlockRuneAltarEntrance AIR_ALTAR_ENTRANCE = new BlockRuneAltarEntrance(EnumRuneType.AIR);
	
	public static final BlockRuneAltarEntrance BLOOD_ALTAR_ENTRANCE = new BlockRuneAltarEntrance(EnumRuneType.BLOOD);
	
	public static final BlockRuneAltarEntrance BODY_ALTAR_ENTRANCE = new BlockRuneAltarEntrance(EnumRuneType.BODY);
	
	public static final BlockRuneAltarEntrance CHAOS_ALTAR_ENTRANCE = new BlockRuneAltarEntrance(EnumRuneType.CHAOS);
	
	public static final BlockRuneAltarEntrance COSMIC_ALTAR_ENTRANCE = new BlockRuneAltarEntrance(EnumRuneType.COSMIC);
	
	public static final BlockRuneAltarEntrance DEATH_ALTAR_ENTRANCE = new BlockRuneAltarEntrance(EnumRuneType.DEATH);
	
	public static final BlockRuneAltarEntrance EARTH_ALTAR_ENTRANCE = new BlockRuneAltarEntrance(EnumRuneType.EARTH);
	
	public static final BlockRuneAltarEntrance FIRE_ALTAR_ENTRANCE = new BlockRuneAltarEntrance(EnumRuneType.FIRE);
	
	public static final BlockRuneAltarEntrance LAW_ALTAR_ENTRANCE = new BlockRuneAltarEntrance(EnumRuneType.LAW);
	
	public static final BlockRuneAltarEntrance MIND_ALTAR_ENTRANCE = new BlockRuneAltarEntrance(EnumRuneType.MIND);
	
	public static final BlockRuneAltarEntrance NATURE_ALTAR_ENTRANCE = new BlockRuneAltarEntrance(EnumRuneType.NATURE);
	
	public static final BlockRuneAltarEntrance SOUL_ALTAR_ENTRANCE = new BlockRuneAltarEntrance(EnumRuneType.SOUL);
	
	public static final BlockRuneAltarEntrance WATER_ALTAR_ENTRANCE = new BlockRuneAltarEntrance(EnumRuneType.WATER);
	
	public static final BlockAltarPortal ALTAR_PORTAL = new BlockAltarPortal("altar_exit_portal");
	
	public static final BlockRuneEssence RUNE_ESSENCE = new BlockRuneEssence(false);
	
	public static final BlockRuneEssence FINITE_RUNE_ESSENCE = new BlockRuneEssence(true);
	
	public static final RMModBlock RUIN_STONE = new RMModBlock("ruin_stone", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true, true);
	
	public static final BlockStationStone STATION_STONE = new BlockStationStone();
	
	public static final RMModBlock TEMPLE_BRICKS = new RMModBlock("temple_bricks", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true, true);
	
	public static final RMModStairs TEMPLE_BRICK_STAIRS = new RMModStairs("temple_brick_stairs", "pickaxe", 1, true, TEMPLE_BRICKS.getDefaultState());
	
	public static final RMModSlab.Half TEMPLE_BRICK_SLAB = new RMModSlab.Half("temple_brick_slab", Material.ROCK, .5f, 10.0f, "pickaxe", 1, true);
	
	public static final RMModRail TEMPLE_BRICK_RAIL = new RMModRail("temple_brick_rail", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	
	public static final RMModBlock BLOOD_BRICKS = new RMModBlock("blood_bricks", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true, true);
	
	public static final RMModStairs BLOOD_BRICK_STAIRS = new RMModStairs("blood_brick_stairs", "pickaxe", 1, true, BLOOD_BRICKS.getDefaultState());
	
	public static final RMModSlab.Half BLOOD_BRICK_SLAB = new RMModSlab.Half("blood_brick_slab", Material.ROCK, .5f, 10.0f, "pickaxe", 1, true);
	
	public static final RMModRail BLOOD_BRICK_RAIL = new RMModRail("blood_brick_rail", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	
	public static final RMRandomRotationBlock FLESH_BLOCK = new RMRandomRotationBlock("flesh_block", Material.CAKE, 1.5f, 10.0f, "pickaxe", 1, true, true);
	
	public static final RMModRail STONE_BRICK_RAIL = new RMModRail("stone_brick_rail", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	
	public static final RMModRail NETHER_BRICK_RAIL = new RMModRail("nether_brick_rail", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	
	public static final RMModRail PRISMARINE_RAIL = new RMModRail("dark_prismarine_rail", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	
	public static final RMModRail PURPUR_RAIL = new RMModRail("purpur_block_rail", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	
	public static final RMModRail QUARTZ_RAIL = new RMModRail("quartz_block_rail", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	
	public static final RMModRail SANDSTONE_RAIL = new RMModRail("smooth_sandstone_rail", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	
	public static final RMModRail ANDESITE_RAIL = new RMModRail("smooth_andesite_rail", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	
	public static final RMModRail ICE_RAIL = (RMModRail) new RMModRail("ice_rail", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true).setRenderLayer(BlockRenderLayer.TRANSLUCENT);
	
	public static final RMModRail PACKED_ICE_RAIL = new RMModRail("packed_ice_rail", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	
	public static final RMModRail RED_SANDSTONE_RAIL = new RMModRail("red_sandstone_rail", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	
	public static final RMModRail OBSIDIAN_RAIL = new RMModRail("obsidian_rail", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	
	public static final RMModRail GRANITE_RAIL = new RMModRail("smooth_granite_rail", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	
	public static final RMModRail DIORITE_RAIL = new RMModRail("smooth_diorite_rail", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	
	public static final RMModRail BONE_RAIL = new RMModRail("bone_block_rail", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	
	public static final RMModRail END_BRICK_RAIL = new RMModRail("end_brick_rail", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	
	public static final RMModRail BRICK_RAIL = new RMModRail("brick_block_rail", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	
	public static final BlockParticleLight WHITE_LIGHT = new BlockParticleLight(EnumColour.WHITE);
	
	public static final BlockParticleLight ORANGE_LIGHT = new BlockParticleLight(EnumColour.ORANGE);
	
	public static final BlockParticleLight MAGENTA_LIGHT = new BlockParticleLight(EnumColour.MAGENTA);
	
	public static final BlockParticleLight LIGHT_BLUE_LIGHT = new BlockParticleLight(EnumColour.LIGHT_BLUE);
	
	public static final BlockParticleLight YELLOW_LIGHT = new BlockParticleLight(EnumColour.YELLOW);
	
	public static final BlockParticleLight LIME_LIGHT = new BlockParticleLight(EnumColour.LIME);
	
	public static final BlockParticleLight PINK_LIGHT = new BlockParticleLight(EnumColour.PINK);
	
	public static final BlockParticleLight GRAY_LIGHT = new BlockParticleLight(EnumColour.GRAY);
	
	public static final BlockParticleLight SILVER_LIGHT = new BlockParticleLight(EnumColour.SILVER);
	
	public static final BlockParticleLight CYAN_LIGHT = new BlockParticleLight(EnumColour.CYAN);
	
	public static final BlockParticleLight PURPLE_LIGHT = new BlockParticleLight(EnumColour.PURPLE);
	
	public static final BlockParticleLight BLUE_LIGHT = new BlockParticleLight(EnumColour.BLUE);
	
	public static final BlockParticleLight BROWN_LIGHT = new BlockParticleLight(EnumColour.BROWN);
	
	public static final BlockParticleLight GREEN_LIGHT = new BlockParticleLight(EnumColour.GREEN);
	
	public static final BlockParticleLight RED_LIGHT = new BlockParticleLight(EnumColour.RED);
	
	public static final BlockParticleLight BLACK_LIGHT = new BlockParticleLight(EnumColour.BLACK);
	
	public static final BlockBlackMonolith BLACK_MONOLITH = new BlockBlackMonolith();
	
	public static final BlockElderStone ELDER_STONE = new BlockElderStone();
	
	public static final BlockElementalObelisk AIR_OBELISK = new BlockElementalObelisk("air_obelisk", ModItems.AIR_OBELISK_SHARD, ModItems.AIR_ORB);
	
	public static final BlockElementalObelisk EARTH_OBELISK = new BlockElementalObelisk("earth_obelisk", ModItems.EARTH_OBELISK_SHARD, ModItems.EARTH_ORB);
	
	public static final BlockElementalObelisk FIRE_OBELISK = new BlockElementalObelisk("fire_obelisk", ModItems.FIRE_OBELISK_SHARD, ModItems.FIRE_ORB);
	
	public static final BlockElementalObelisk WATER_OBELISK = new BlockElementalObelisk("water_obelisk", ModItems.WATER_OBELISK_SHARD, ModItems.WATER_ORB);
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		LogHelper.info("Registering blocks");
		
		Block[] blocks = {
				AIR_ALTAR,
				ASTRAL_ALTAR,
				BLOOD_ALTAR,
				BODY_ALTAR,
				CHAOS_ALTAR,
				COSMIC_ALTAR,
				DEATH_ALTAR,
				EARTH_ALTAR,
				FIRE_ALTAR,
				LAW_ALTAR,
				MIND_ALTAR,
				NATURE_ALTAR,
				SOUL_ALTAR,
				WATER_ALTAR,
				OURANIA_ALTAR,
				
				AIR_ALTAR_ENTRANCE,
				BLOOD_ALTAR_ENTRANCE,
				BODY_ALTAR_ENTRANCE,
				CHAOS_ALTAR_ENTRANCE,
				COSMIC_ALTAR_ENTRANCE,
				DEATH_ALTAR_ENTRANCE,
				EARTH_ALTAR_ENTRANCE,
				FIRE_ALTAR_ENTRANCE,
				LAW_ALTAR_ENTRANCE,
				MIND_ALTAR_ENTRANCE,
				NATURE_ALTAR_ENTRANCE,
				SOUL_ALTAR_ENTRANCE,
				WATER_ALTAR_ENTRANCE,
				
				ALTAR_PORTAL,
				
				RUNE_ESSENCE,
				FINITE_RUNE_ESSENCE,
				
				RUIN_STONE,
				STATION_STONE,
				
				TEMPLE_BRICKS,
				TEMPLE_BRICK_STAIRS,
				TEMPLE_BRICK_SLAB,
				TEMPLE_BRICK_RAIL,
				
				BLOOD_BRICKS,
				BLOOD_BRICK_STAIRS,
				BLOOD_BRICK_SLAB,
				BLOOD_BRICK_RAIL,
				
				FLESH_BLOCK,
				
				STONE_BRICK_RAIL,
				NETHER_BRICK_RAIL,
				PRISMARINE_RAIL,
				PURPUR_RAIL,
				QUARTZ_RAIL,
				SANDSTONE_RAIL,
				ANDESITE_RAIL,
				ICE_RAIL,
				PACKED_ICE_RAIL,
				RED_SANDSTONE_RAIL,
				OBSIDIAN_RAIL,
				GRANITE_RAIL,
				DIORITE_RAIL,
				BONE_RAIL,
				END_BRICK_RAIL,
				BRICK_RAIL,
				
				WHITE_LIGHT,
				ORANGE_LIGHT,
				MAGENTA_LIGHT,
				LIGHT_BLUE_LIGHT,
				YELLOW_LIGHT,
				LIME_LIGHT,
				PINK_LIGHT,
				GRAY_LIGHT,
				SILVER_LIGHT,
				CYAN_LIGHT,
				PURPLE_LIGHT,
				BLUE_LIGHT,
				BROWN_LIGHT,
				GREEN_LIGHT,
				RED_LIGHT,
				BLACK_LIGHT,
				
				BLACK_MONOLITH,
				ELDER_STONE,
				
				AIR_OBELISK,
				EARTH_OBELISK,
				FIRE_OBELISK,
				WATER_OBELISK
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
