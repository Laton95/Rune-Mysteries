package com.laton95.runemysteries.init;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.block.*;
import com.laton95.runemysteries.enums.EnumColour;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.util.ModLog;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemWallOrFloor;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RuneMysteries.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks {
	
	public static final BlockAltar AIR_ALTAR = new BlockAltar(EnumRuneType.AIR);
	
	public static final BlockAltar ASTRAL_ALTAR = new BlockAltar(EnumRuneType.ASTRAL);
	
	public static final BlockAltar BLOOD_ALTAR = new BlockAltar(EnumRuneType.BLOOD);
	
	public static final BlockAltar BODY_ALTAR = new BlockAltar(EnumRuneType.BODY);
	
	public static final BlockAltar CHAOS_ALTAR = new BlockAltar(EnumRuneType.CHAOS);
	
	public static final BlockAltar COSMIC_ALTAR = new BlockAltar(EnumRuneType.COSMIC);
	
	public static final BlockAltar DEATH_ALTAR = new BlockAltar(EnumRuneType.DEATH);
	
	public static final BlockAltar EARTH_ALTAR = new BlockAltar(EnumRuneType.EARTH);
	
	public static final BlockAltar FIRE_ALTAR = new BlockAltar(EnumRuneType.FIRE);
	
	public static final BlockAltar LAW_ALTAR = new BlockAltar(EnumRuneType.LAW);
	
	public static final BlockAltar MIND_ALTAR = new BlockAltar(EnumRuneType.MIND);
	
	public static final BlockAltar NATURE_ALTAR = new BlockAltar(EnumRuneType.NATURE);
	
	public static final BlockAltar SOUL_ALTAR = new BlockAltar(EnumRuneType.SOUL);
	
	public static final BlockAltar WATER_ALTAR = new BlockAltar(EnumRuneType.WATER);
	
	public static final BlockAltar OURANIA_ALTAR = new BlockAltar(EnumRuneType.OURANIA);
	
	public static final BlockAltarEntrance AIR_ALTAR_ENTRANCE = new BlockAltarEntrance(EnumRuneType.AIR);
	
	public static final BlockAltarEntrance BLOOD_ALTAR_ENTRANCE = new BlockAltarEntrance(EnumRuneType.BLOOD);
	
	public static final BlockAltarEntrance BODY_ALTAR_ENTRANCE = new BlockAltarEntrance(EnumRuneType.BODY);
	
	public static final BlockAltarEntrance CHAOS_ALTAR_ENTRANCE = new BlockAltarEntrance(EnumRuneType.CHAOS);
	
	public static final BlockAltarEntrance COSMIC_ALTAR_ENTRANCE = new BlockAltarEntrance(EnumRuneType.COSMIC);
	
	public static final BlockAltarEntrance DEATH_ALTAR_ENTRANCE = new BlockAltarEntrance(EnumRuneType.DEATH);
	
	public static final BlockAltarEntrance EARTH_ALTAR_ENTRANCE = new BlockAltarEntrance(EnumRuneType.EARTH);
	
	public static final BlockAltarEntrance FIRE_ALTAR_ENTRANCE = new BlockAltarEntrance(EnumRuneType.FIRE);
	
	public static final BlockAltarEntrance LAW_ALTAR_ENTRANCE = new BlockAltarEntrance(EnumRuneType.LAW);
	
	public static final BlockAltarEntrance MIND_ALTAR_ENTRANCE = new BlockAltarEntrance(EnumRuneType.MIND);
	
	public static final BlockAltarEntrance NATURE_ALTAR_ENTRANCE = new BlockAltarEntrance(EnumRuneType.NATURE);
	
	public static final BlockAltarEntrance SOUL_ALTAR_ENTRANCE = new BlockAltarEntrance(EnumRuneType.SOUL);
	
	public static final BlockAltarEntrance WATER_ALTAR_ENTRANCE = new BlockAltarEntrance(EnumRuneType.WATER);
	
	public static final BlockAltarPortal ALTAR_PORTAL = new BlockAltarPortal();
	
	public static final BlockRuneEssence RUNE_ESSENCE = new BlockRuneEssence();
	
	public static final BlockElementalObelisk AIR_OBELISK = new BlockElementalObelisk("air_obelisk", ModItems.AIR_SHARD, EnumRuneType.AIR);
	
	public static final BlockElementalObelisk EARTH_OBELISK = new BlockElementalObelisk("earth_obelisk", ModItems.EARTH_SHARD, EnumRuneType.EARTH);
	
	public static final BlockElementalObelisk FIRE_OBELISK = new BlockElementalObelisk("fire_obelisk", ModItems.FIRE_SHARD, EnumRuneType.FIRE);
	
	public static final BlockElementalObelisk WATER_OBELISK = new BlockElementalObelisk("water_obelisk", ModItems.WATER_SHARD, EnumRuneType.WATER);
	
	public static final BlockBlackMonolith BLACK_MONOLITH = new BlockBlackMonolith();
	
	public static final ModBlock RUIN_STONE = new ModBlock("ruin_stone");
	
	public static final ModBlockStairs RUIN_STONE_STAIRS = new ModBlockStairs("ruin_stone_stairs", RUIN_STONE);
	
	public static final ModBlockSlab RUIN_STONE_SLAB = new ModBlockSlab("ruin_stone_slab", RUIN_STONE);
	
	public static final BlockRailing RUIN_STONE_RAILING = new BlockRailing("ruin_stone_railing", RUIN_STONE);
	
	public static final ModBlock TEMPLE_BRICKS = new ModBlock("temple_bricks");
	
	public static final ModBlockStairs TEMPLE_BRICK_STAIRS = new ModBlockStairs("temple_brick_stairs", TEMPLE_BRICKS);
	
	public static final ModBlockSlab TEMPLE_BRICK_SLAB = new ModBlockSlab("temple_brick_slab", TEMPLE_BRICKS);
	
	public static final BlockRailing TEMPLE_BRICK_RAILING = new BlockRailing("temple_brick_railing", TEMPLE_BRICKS);
	
	public static final ModBlock BLOOD_BRICKS = new ModBlock("blood_bricks");
	
	public static final ModBlockStairs BLOOD_BRICK_STAIRS = new ModBlockStairs("blood_brick_stairs", BLOOD_BRICKS);
	
	public static final ModBlockSlab BLOOD_BRICK_SLAB = new ModBlockSlab("blood_brick_slab", BLOOD_BRICKS);
	
	public static final BlockRailing BLOOD_BRICK_RAILING = new BlockRailing("blood_brick_railing", BLOOD_BRICKS);
	
	public static final ModBlock FLESH_BLOCK = new ModBlock("flesh_block", Block.Properties.create(Material.CLAY).slipperiness(0.8F).sound(SoundType.SLIME));
	
	public static final BlockRailing STONE_RAILING = new BlockRailing("stone_railing", Blocks.STONE);
	
	public static final BlockRailing POLISHED_GRANITE_RAILING = new BlockRailing("polished_granite_railing", Blocks.POLISHED_GRANITE);
	
	public static final BlockRailing POLISHED_DIORITE_RAILING = new BlockRailing("polished_diorite_railing", Blocks.POLISHED_DIORITE);
	
	public static final BlockRailing POLISHED_ANDESITE_RAILING = new BlockRailing("polished_andesite_railing", Blocks.POLISHED_ANDESITE);
	
	public static final BlockRailing QUARTZ_RAILING = new BlockRailing("quartz_railing", Blocks.QUARTZ_BLOCK);
	
	public static final BlockRailing RED_SANDSTONE_RAILING = new BlockRailing("red_sandstone_railing", Blocks.RED_SANDSTONE);
	
	public static final BlockRailing SANDSTONE_RAILING = new BlockRailing("sandstone_railing", Blocks.SANDSTONE);
	
	public static final BlockRailing SMOOTH_STONE_RAILING = new BlockRailing("smooth_stone_railing", Blocks.SMOOTH_STONE);
	
	public static final BlockRailing BRICK_RAILING = new BlockRailing("brick_railing", Blocks.BRICKS);
	
	public static final BlockRailing OBSIDIAN_RAILING = new BlockRailing("obsidian_railing", Blocks.OBSIDIAN);
	
	public static final BlockRailing PURPUR_RAILING = new BlockRailing("purpur_railing", Blocks.PURPUR_BLOCK);
	
	public static final BlockRailing ICE_RAILING = new BlockRailing("ice_railing", Blocks.ICE, true);
	
	public static final BlockRailing STONE_BRICK_RAILING = new BlockRailing("stone_brick_railing", Blocks.STONE_BRICKS);
	
	public static final BlockRailing NETHER_BRICK_RAILING = new BlockRailing("nether_brick_railing", Blocks.NETHER_BRICKS);
	
	public static final BlockRailing END_STONE_BRICK_RAILING = new BlockRailing("end_stone_brick_railing", Blocks.END_STONE_BRICKS);
	
	public static final BlockRailing PACKED_ICE_RAILING = new BlockRailing("packed_ice_railing", Blocks.PACKED_ICE);
	
	public static final BlockRailing PRISMARINE_RAILING = new BlockRailing("prismarine_railing", Blocks.PRISMARINE);
	
	public static final BlockRailing PRISMARINE_BRICK_RAILING = new BlockRailing("prismarine_brick_railing", Blocks.PRISMARINE_BRICKS);
	
	public static final BlockRailing DARK_PRISMARINE_RAILING = new BlockRailing("dark_prismarine_railing", Blocks.DARK_PRISMARINE);
	
	public static final BlockRailing RED_NETHER_BRICK_RAILING = new BlockRailing("red_nether_brick_railing", Blocks.RED_NETHER_BRICKS);
	
	public static final BlockRailing BONE_RAILING = new BlockRailing("bone_railing", Blocks.BONE_BLOCK);
	
	public static final BlockRailing BLUE_ICE_RAILING = new BlockRailing("blue_ice_railing", Blocks.BLUE_ICE);
	
	public static final BlockParticleLight WHITE_LIGHT = new BlockParticleLight(EnumColour.WHITE);
	
	public static final BlockParticleLight ORANGE_LIGHT = new BlockParticleLight(EnumColour.ORANGE);
	
	public static final BlockParticleLight MAGENTA_LIGHT = new BlockParticleLight(EnumColour.MAGENTA);
	
	public static final BlockParticleLight LIGHT_BLUE_LIGHT = new BlockParticleLight(EnumColour.LIGHT_BLUE);
	
	public static final BlockParticleLight YELLOW_LIGHT = new BlockParticleLight(EnumColour.YELLOW);
	
	public static final BlockParticleLight LIME_LIGHT = new BlockParticleLight(EnumColour.LIME);
	
	public static final BlockParticleLight PINK_LIGHT = new BlockParticleLight(EnumColour.PINK);
	
	public static final BlockParticleLight GRAY_LIGHT = new BlockParticleLight(EnumColour.GRAY);
	
	public static final BlockParticleLight LIGHT_GRAY_LIGHT = new BlockParticleLight(EnumColour.LIGHT_GRAY);
	
	public static final BlockParticleLight CYAN_LIGHT = new BlockParticleLight(EnumColour.CYAN);
	
	public static final BlockParticleLight PURPLE_LIGHT = new BlockParticleLight(EnumColour.PURPLE);
	
	public static final BlockParticleLight BLUE_LIGHT = new BlockParticleLight(EnumColour.BLUE);
	
	public static final BlockParticleLight BROWN_LIGHT = new BlockParticleLight(EnumColour.BROWN);
	
	public static final BlockParticleLight GREEN_LIGHT = new BlockParticleLight(EnumColour.GREEN);
	
	public static final BlockParticleLight RED_LIGHT = new BlockParticleLight(EnumColour.RED);
	
	public static final BlockParticleLight BLACK_LIGHT = new BlockParticleLight(EnumColour.BLACK);
	
	public static final BlockUnderwaterTorch UNDERWATER_TORCH = new BlockUnderwaterTorch();
	
	public static final BlockUnderwaterTorchWall UNDERWATER_WALL_TORCH = new BlockUnderwaterTorchWall();
	
	public static final BlockElderCatalyst ELDER_CATALYST = new BlockElderCatalyst();
	
	private static Block[] blocks = {
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
			AIR_OBELISK,
			EARTH_OBELISK,
			FIRE_OBELISK,
			WATER_OBELISK,
			BLACK_MONOLITH,
			RUIN_STONE,
			RUIN_STONE_STAIRS,
			RUIN_STONE_SLAB,
			RUIN_STONE_RAILING,
			TEMPLE_BRICKS,
			TEMPLE_BRICK_STAIRS,
			TEMPLE_BRICK_SLAB,
			TEMPLE_BRICK_RAILING,
			BLOOD_BRICKS,
			BLOOD_BRICK_STAIRS,
			BLOOD_BRICK_SLAB,
			BLOOD_BRICK_RAILING,
			FLESH_BLOCK,
			STONE_RAILING,
			POLISHED_GRANITE_RAILING,
			POLISHED_DIORITE_RAILING,
			POLISHED_ANDESITE_RAILING,
			QUARTZ_RAILING,
			RED_SANDSTONE_RAILING,
			SANDSTONE_RAILING,
			SMOOTH_STONE_RAILING,
			BRICK_RAILING,
			OBSIDIAN_RAILING,
			PURPUR_RAILING,
			ICE_RAILING,
			STONE_BRICK_RAILING,
			NETHER_BRICK_RAILING,
			END_STONE_BRICK_RAILING,
			PACKED_ICE_RAILING,
			PRISMARINE_RAILING,
			PRISMARINE_BRICK_RAILING,
			DARK_PRISMARINE_RAILING,
			RED_NETHER_BRICK_RAILING,
			BONE_RAILING,
			BLUE_ICE_RAILING,
			WHITE_LIGHT,
			ORANGE_LIGHT,
			MAGENTA_LIGHT,
			LIGHT_BLUE_LIGHT,
			YELLOW_LIGHT,
			LIME_LIGHT,
			PINK_LIGHT,
			GRAY_LIGHT,
			LIGHT_GRAY_LIGHT,
			CYAN_LIGHT,
			PURPLE_LIGHT,
			BLUE_LIGHT,
			BROWN_LIGHT,
			GREEN_LIGHT,
			RED_LIGHT,
			BLACK_LIGHT,
			UNDERWATER_TORCH,
			UNDERWATER_WALL_TORCH,
			ELDER_CATALYST
	};
	
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		ModLog.info("Registering blocks");
		
		for(Block block : blocks) {
			event.getRegistry().register(block);
			
			if(block instanceof ModBlock && !((ModBlock) block).hasItem) {
			}
			else {
				ModItems.addBlock(block);
			}
		}
		
		ModItems.addExtraItemBlock(new ItemWallOrFloor(ModBlocks.UNDERWATER_TORCH, ModBlocks.UNDERWATER_WALL_TORCH, (new Item.Properties()).group(RuneMysteries.RUNE_GROUP)));
	}
}
