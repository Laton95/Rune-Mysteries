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
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = RuneMysteries.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(RuneMysteries.MOD_ID)
public class ModBlocks {
	
	public static final BlockAltar AIR_ALTAR = null;
	
	public static final BlockAltar ASTRAL_ALTAR = null;
	
	public static final BlockAltar BLOOD_ALTAR = null;
	
	public static final BlockAltar BODY_ALTAR = null;
	
	public static final BlockAltar CHAOS_ALTAR = null;
	
	public static final BlockAltar COSMIC_ALTAR = null;
	
	public static final BlockAltar DEATH_ALTAR = null;
	
	public static final BlockAltar EARTH_ALTAR = null;
	
	public static final BlockAltar FIRE_ALTAR = null;
	
	public static final BlockAltar LAW_ALTAR = null;
	
	public static final BlockAltar MIND_ALTAR = null;
	
	public static final BlockAltar NATURE_ALTAR = null;
	
	public static final BlockAltar SOUL_ALTAR = null;
	
	public static final BlockAltar WATER_ALTAR = null;
	
	public static final BlockAltar OURANIA_ALTAR = null;
	
	public static final BlockAltarEntrance AIR_ALTAR_ENTRANCE = null;
	
	public static final BlockAltarEntrance BLOOD_ALTAR_ENTRANCE = null;
	
	public static final BlockAltarEntrance BODY_ALTAR_ENTRANCE = null;
	
	public static final BlockAltarEntrance CHAOS_ALTAR_ENTRANCE = null;
	
	public static final BlockAltarEntrance COSMIC_ALTAR_ENTRANCE = null;
	
	public static final BlockAltarEntrance DEATH_ALTAR_ENTRANCE = null;
	
	public static final BlockAltarEntrance EARTH_ALTAR_ENTRANCE = null;
	
	public static final BlockAltarEntrance FIRE_ALTAR_ENTRANCE = null;
	
	public static final BlockAltarEntrance LAW_ALTAR_ENTRANCE = null;
	
	public static final BlockAltarEntrance MIND_ALTAR_ENTRANCE = null;
	
	public static final BlockAltarEntrance NATURE_ALTAR_ENTRANCE = null;
	
	public static final BlockAltarEntrance SOUL_ALTAR_ENTRANCE = null;
	
	public static final BlockAltarEntrance WATER_ALTAR_ENTRANCE = null;
	
	public static final BlockAltarPortal ALTAR_PORTAL = null;
	
	public static final BlockRuneEssence RUNE_ESSENCE_BLOCK = null;
	
	public static final BlockElementalObelisk AIR_OBELISK = null;
	
	public static final BlockElementalObelisk EARTH_OBELISK = null;
	
	public static final BlockElementalObelisk FIRE_OBELISK = null;
	
	public static final BlockElementalObelisk WATER_OBELISK = null;
	
	public static final BlockBlackMonolith BLACK_MONOLITH = null;
	
	public static final ModBlock RUIN_STONE = null;
	
	public static final ModBlockStairs RUIN_STONE_STAIRS = null;
	
	public static final ModBlockSlab RUIN_STONE_SLAB = null;
	
	public static final BlockRailing RUIN_STONE_RAILING = null;
	
	public static final ModBlock TEMPLE_BRICKS = null;
	
	public static final ModBlockStairs TEMPLE_BRICK_STAIRS = null;
	
	public static final ModBlockSlab TEMPLE_BRICK_SLAB = null;
	
	public static final BlockRailing TEMPLE_BRICK_RAILING = null;
	
	public static final ModBlock BLOOD_BRICKS = null;
	
	public static final ModBlockStairs BLOOD_BRICK_STAIRS = null;
	
	public static final ModBlockSlab BLOOD_BRICK_SLAB = null;
	
	public static final BlockRailing BLOOD_BRICK_RAILING = null;
	
	public static final ModBlock FLESH_BLOCK = null;
	
	public static final BlockRailing STONE_RAILING = null;
	
	public static final BlockRailing POLISHED_GRANITE_RAILING = null;
	
	public static final BlockRailing POLISHED_DIORITE_RAILING = null;
	
	public static final BlockRailing POLISHED_ANDESITE_RAILING = null;
	
	public static final BlockRailing QUARTZ_RAILING = null;
	
	public static final BlockRailing RED_SANDSTONE_RAILING = null;
	
	public static final BlockRailing SANDSTONE_RAILING = null;
	
	public static final BlockRailing SMOOTH_STONE_RAILING = null;
	
	public static final BlockRailing BRICK_RAILING = null;
	
	public static final BlockRailing OBSIDIAN_RAILING = null;
	
	public static final BlockRailing PURPUR_RAILING = null;
	
	public static final BlockRailing ICE_RAILING = null;
	
	public static final BlockRailing STONE_BRICK_RAILING = null;
	
	public static final BlockRailing NETHER_BRICK_RAILING = null;
	
	public static final BlockRailing END_STONE_BRICK_RAILING = null;
	
	public static final BlockRailing PACKED_ICE_RAILING = null;
	
	public static final BlockRailing PRISMARINE_RAILING = null;
	
	public static final BlockRailing PRISMARINE_BRICK_RAILING = null;
	
	public static final BlockRailing DARK_PRISMARINE_RAILING = null;
	
	public static final BlockRailing RED_NETHER_BRICK_RAILING = null;
	
	public static final BlockRailing BONE_RAILING = null;
	
	public static final BlockRailing BLUE_ICE_RAILING = null;
	
	public static final BlockParticleLight WHITE_LIGHT = null;
	
	public static final BlockParticleLight ORANGE_LIGHT = null;
	
	public static final BlockParticleLight MAGENTA_LIGHT = null;
	
	public static final BlockParticleLight LIGHT_BLUE_LIGHT = null;
	
	public static final BlockParticleLight YELLOW_LIGHT = null;
	
	public static final BlockParticleLight LIME_LIGHT = null;
	
	public static final BlockParticleLight PINK_LIGHT = null;
	
	public static final BlockParticleLight GRAY_LIGHT = null;
	
	public static final BlockParticleLight LIGHT_GRAY_LIGHT = null;
	
	public static final BlockParticleLight CYAN_LIGHT = null;
	
	public static final BlockParticleLight PURPLE_LIGHT = null;
	
	public static final BlockParticleLight BLUE_LIGHT = null;
	
	public static final BlockParticleLight BROWN_LIGHT = null;
	
	public static final BlockParticleLight GREEN_LIGHT = null;
	
	public static final BlockParticleLight RED_LIGHT = null;
	
	public static final BlockParticleLight BLACK_LIGHT = null;
	
	public static final BlockUnderwaterTorch UNDERWATER_TORCH = null;
	
	public static final BlockUnderwaterTorchWall UNDERWATER_WALL_TORCH = null;
	
	public static final BlockElderCatalyst ELDER_CATALYST = null;
	
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		ModLog.info("Registering blocks");
		
		register(event, new BlockAltar(EnumRuneType.AIR), "air_altar");
		register(event, new BlockAltar(EnumRuneType.ASTRAL), "astral_altar");
		register(event, new BlockAltar(EnumRuneType.BLOOD), "blood_altar");
		register(event, new BlockAltar(EnumRuneType.BODY), "body_altar");
		register(event, new BlockAltar(EnumRuneType.CHAOS), "chaos_altar");
		register(event, new BlockAltar(EnumRuneType.COSMIC), "cosmic_altar");
		register(event, new BlockAltar(EnumRuneType.DEATH), "death_altar");
		register(event, new BlockAltar(EnumRuneType.EARTH), "earth_altar");
		register(event, new BlockAltar(EnumRuneType.FIRE), "fire_altar");
		register(event, new BlockAltar(EnumRuneType.LAW), "law_altar");
		register(event, new BlockAltar(EnumRuneType.MIND), "mind_altar");
		register(event, new BlockAltar(EnumRuneType.NATURE), "nature_altar");
		register(event, new BlockAltar(EnumRuneType.SOUL), "soul_altar");
		register(event, new BlockAltar(EnumRuneType.WATER), "water_altar");
		register(event, new BlockAltar(EnumRuneType.OURANIA), "ourania_altar");
		
		registerWithoutItem(event, new BlockAltarEntrance(EnumRuneType.AIR), "air_altar_entrance");
		registerWithoutItem(event, new BlockAltarEntrance(EnumRuneType.BLOOD), "blood_altar_entrance");
		registerWithoutItem(event, new BlockAltarEntrance(EnumRuneType.BODY), "body_altar_entrance");
		registerWithoutItem(event, new BlockAltarEntrance(EnumRuneType.CHAOS), "chaos_altar_entrance");
		registerWithoutItem(event, new BlockAltarEntrance(EnumRuneType.COSMIC), "cosmic_altar_entrance");
		registerWithoutItem(event, new BlockAltarEntrance(EnumRuneType.DEATH), "death_altar_entrance");
		registerWithoutItem(event, new BlockAltarEntrance(EnumRuneType.EARTH), "earth_altar_entrance");
		registerWithoutItem(event, new BlockAltarEntrance(EnumRuneType.FIRE), "fire_altar_entrance");
		registerWithoutItem(event, new BlockAltarEntrance(EnumRuneType.LAW), "law_altar_entrance");
		registerWithoutItem(event, new BlockAltarEntrance(EnumRuneType.MIND), "mind_altar_entrance");
		registerWithoutItem(event, new BlockAltarEntrance(EnumRuneType.NATURE), "nature_altar_entrance");
		registerWithoutItem(event, new BlockAltarEntrance(EnumRuneType.SOUL), "soul_altar_entrance");
		registerWithoutItem(event, new BlockAltarEntrance(EnumRuneType.WATER), "water_altar_entrance");
		
		registerWithoutItem(event, new BlockAltarPortal(), "altar_portal");
		register(event, new BlockRuneEssence(), "rune_essence_block");
		
		register(event, new BlockElementalObelisk(ModItems.AIR_SHARD, EnumRuneType.AIR), "air_obelisk");
		register(event, new BlockElementalObelisk(ModItems.EARTH_SHARD, EnumRuneType.EARTH), "earth_obelisk");
		register(event, new BlockElementalObelisk(ModItems.FIRE_SHARD, EnumRuneType.FIRE), "fire_obelisk");
		register(event, new BlockElementalObelisk(ModItems.WATER_SHARD, EnumRuneType.WATER), "water_obelisk");
		
		register(event, new BlockBlackMonolith(), "black_monolith");
		
		register(event, new ModBlock(), "ruin_stone");
		register(event, new ModBlockStairs(Blocks.STONE), "ruin_stone_stairs");
		register(event, new ModBlockSlab(Blocks.STONE), "ruin_stone_slab");
		register(event, new BlockRailing(Blocks.STONE), "ruin_stone_railing");
		register(event, new ModBlock(), "temple_bricks");
		register(event, new ModBlockStairs(Blocks.STONE), "temple_brick_stairs");
		register(event, new ModBlockSlab(Blocks.STONE), "temple_brick_slab");
		register(event, new BlockRailing(Blocks.STONE), "temple_brick_railing");
		register(event, new ModBlock(), "blood_bricks");
		register(event, new ModBlockStairs(Blocks.STONE), "blood_brick_stairs");
		register(event, new ModBlockSlab(Blocks.STONE), "blood_brick_slab");
		register(event, new BlockRailing(Blocks.STONE), "blood_brick_railing");
		register(event, new ModBlock(Block.Properties.create(Material.CLAY).slipperiness(0.8F).sound(SoundType.SLIME)), "flesh_block");

		register(event, new BlockRailing(Blocks.STONE), "stone_railing");
		register(event, new BlockRailing(Blocks.POLISHED_GRANITE), "polished_granite_railing");
		register(event, new BlockRailing(Blocks.POLISHED_DIORITE), "polished_diorite_railing");
		register(event, new BlockRailing(Blocks.POLISHED_ANDESITE), "polished_andesite_railing");
		register(event, new BlockRailing(Blocks.QUARTZ_BLOCK), "quartz_railing");
		register(event, new BlockRailing(Blocks.RED_SANDSTONE), "red_sandstone_railing");
		register(event, new BlockRailing(Blocks.SANDSTONE), "sandstone_railing");
		register(event, new BlockRailing(Blocks.SMOOTH_STONE), "smooth_stone_railing");
		register(event, new BlockRailing(Blocks.BRICKS), "brick_railing");
		register(event, new BlockRailing(Blocks.OBSIDIAN), "obsidian_railing");
		register(event, new BlockRailing(Blocks.PURPUR_BLOCK), "purpur_railing");
		register(event, new BlockRailing(Blocks.ICE), "ice_railing");
		register(event, new BlockRailing(Blocks.STONE_BRICKS), "stone_brick_railing");
		register(event, new BlockRailing(Blocks.NETHER_BRICKS), "nether_brick_railing");
		register(event, new BlockRailing(Blocks.END_STONE_BRICKS), "end_stone_brick_railing");
		register(event, new BlockRailing(Blocks.PACKED_ICE), "packed_ice_railing");
		register(event, new BlockRailing(Blocks.PRISMARINE), "prismarine_railing");
		register(event, new BlockRailing(Blocks.PRISMARINE_BRICKS), "prismarine_brick_railing");
		register(event, new BlockRailing(Blocks.DARK_PRISMARINE), "dark_prismarine_railing");
		register(event, new BlockRailing(Blocks.RED_NETHER_BRICKS), "red_nether_brick_railing");
		register(event, new BlockRailing(Blocks.BONE_BLOCK), "bone_railing");
		register(event, new BlockRailing(Blocks.BLUE_ICE), "blue_ice_railing");
		
		registerWithoutItem(event, new BlockParticleLight(EnumColour.WHITE), "white_light");
		registerWithoutItem(event, new BlockParticleLight(EnumColour.ORANGE), "orange_light");
		registerWithoutItem(event, new BlockParticleLight(EnumColour.MAGENTA), "magenta_light");
		registerWithoutItem(event, new BlockParticleLight(EnumColour.LIGHT_BLUE), "light_blue_light");
		registerWithoutItem(event, new BlockParticleLight(EnumColour.YELLOW), "yellow_light");
		registerWithoutItem(event, new BlockParticleLight(EnumColour.LIME), "lime_light");
		registerWithoutItem(event, new BlockParticleLight(EnumColour.PINK), "pink_light");
		registerWithoutItem(event, new BlockParticleLight(EnumColour.GRAY), "gray_light");
		registerWithoutItem(event, new BlockParticleLight(EnumColour.LIGHT_GRAY), "light_gray_light");
		registerWithoutItem(event, new BlockParticleLight(EnumColour.CYAN), "cyan_light");
		registerWithoutItem(event, new BlockParticleLight(EnumColour.PURPLE), "purple_light");
		registerWithoutItem(event, new BlockParticleLight(EnumColour.BLUE), "blue_light");
		registerWithoutItem(event, new BlockParticleLight(EnumColour.BROWN), "brown_light");
		registerWithoutItem(event, new BlockParticleLight(EnumColour.GREEN), "green_light");
		registerWithoutItem(event, new BlockParticleLight(EnumColour.RED), "red_light");
		registerWithoutItem(event, new BlockParticleLight(EnumColour.BLACK), "black_light");
		
		registerWithoutItem(event, new BlockUnderwaterTorch(), "underwater_torch");
		registerWithoutItem(event, new BlockUnderwaterTorchWall(), "underwater_wall_torch");
		register(event, new BlockElderCatalyst(), "elder_catalyst");
		
	}
	
	private static void register(RegistryEvent.Register<Block> event, Block block, String name) {
		block.setRegistryName(RuneMysteries.MOD_ID, name);
		event.getRegistry().register(block);
		ModItems.addBlock(block);
	}
	
	private static void registerWithoutItem(RegistryEvent.Register<Block> event, Block block, String name) {
		block.setRegistryName(RuneMysteries.MOD_ID, name);
		event.getRegistry().register(block);
	}
}
