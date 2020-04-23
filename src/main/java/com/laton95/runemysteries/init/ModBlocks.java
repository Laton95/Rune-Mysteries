package com.laton95.runemysteries.init;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.block.*;
import com.laton95.runemysteries.enums.EnumColour;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.util.ModLog;
import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = RuneMysteries.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(RuneMysteries.MOD_ID)
public class ModBlocks {
	
	public static final AltarBlock AIR_ALTAR = null;
	
	public static final AltarBlock ASTRAL_ALTAR = null;
	
	public static final AltarBlock BLOOD_ALTAR = null;
	
	public static final AltarBlock BODY_ALTAR = null;
	
	public static final AltarBlock CHAOS_ALTAR = null;
	
	public static final AltarBlock COSMIC_ALTAR = null;
	
	public static final AltarBlock DEATH_ALTAR = null;
	
	public static final AltarBlock EARTH_ALTAR = null;
	
	public static final AltarBlock FIRE_ALTAR = null;
	
	public static final AltarBlock LAW_ALTAR = null;
	
	public static final AltarBlock MIND_ALTAR = null;
	
	public static final AltarBlock NATURE_ALTAR = null;
	
	public static final AltarBlock SOUL_ALTAR = null;
	
	public static final AltarBlock WATER_ALTAR = null;
	
	public static final AltarBlock OURANIA_ALTAR = null;
	
	public static final AltarEntranceBlock AIR_ALTAR_ENTRANCE = null;
	
	public static final AltarEntranceBlock BLOOD_ALTAR_ENTRANCE = null;
	
	public static final AltarEntranceBlock BODY_ALTAR_ENTRANCE = null;
	
	public static final AltarEntranceBlock CHAOS_ALTAR_ENTRANCE = null;
	
	public static final AltarEntranceBlock COSMIC_ALTAR_ENTRANCE = null;
	
	public static final AltarEntranceBlock DEATH_ALTAR_ENTRANCE = null;
	
	public static final AltarEntranceBlock EARTH_ALTAR_ENTRANCE = null;
	
	public static final AltarEntranceBlock FIRE_ALTAR_ENTRANCE = null;
	
	public static final AltarEntranceBlock LAW_ALTAR_ENTRANCE = null;
	
	public static final AltarEntranceBlock MIND_ALTAR_ENTRANCE = null;
	
	public static final AltarEntranceBlock NATURE_ALTAR_ENTRANCE = null;
	
	public static final AltarEntranceBlock SOUL_ALTAR_ENTRANCE = null;
	
	public static final AltarEntranceBlock WATER_ALTAR_ENTRANCE = null;
	
	public static final AltarPortalBlock ALTAR_PORTAL = null;
	
	public static final RuneEssenceBlock RUNE_ESSENCE_BLOCK = null;
	
	public static final ElementalObeliskBlock AIR_OBELISK = null;
	
	public static final ElementalObeliskBlock EARTH_OBELISK = null;
	
	public static final ElementalObeliskBlock FIRE_OBELISK = null;
	
	public static final ElementalObeliskBlock WATER_OBELISK = null;
	
	public static final BlackMonolithBlock BLACK_MONOLITH = null;
	
	public static final Block RUIN_STONE = null;
	
	public static final ModStairsBlock RUIN_STONE_STAIRS = null;
	
	public static final ModSlabBlock RUIN_STONE_SLAB = null;
	
	public static final RailingBlock RUIN_STONE_RAILING = null;
	
	public static final Block TEMPLE_BRICKS = null;
	
	public static final ModStairsBlock TEMPLE_BRICK_STAIRS = null;
	
	public static final ModSlabBlock TEMPLE_BRICK_SLAB = null;
	
	public static final RailingBlock TEMPLE_BRICK_RAILING = null;
	
	public static final Block BLOOD_BRICKS = null;
	
	public static final ModStairsBlock BLOOD_BRICK_STAIRS = null;
	
	public static final ModSlabBlock BLOOD_BRICK_SLAB = null;
	
	public static final RailingBlock BLOOD_BRICK_RAILING = null;
	
	public static final Block FLESH_BLOCK = null;
	
	public static final RailingBlock STONE_RAILING = null;
	
	public static final RailingBlock POLISHED_GRANITE_RAILING = null;
	
	public static final RailingBlock POLISHED_DIORITE_RAILING = null;
	
	public static final RailingBlock POLISHED_ANDESITE_RAILING = null;
	
	public static final RailingBlock QUARTZ_RAILING = null;
	
	public static final RailingBlock RED_SANDSTONE_RAILING = null;
	
	public static final RailingBlock SANDSTONE_RAILING = null;
	
	public static final RailingBlock SMOOTH_STONE_RAILING = null;
	
	public static final RailingBlock BRICK_RAILING = null;
	
	public static final RailingBlock OBSIDIAN_RAILING = null;
	
	public static final RailingBlock PURPUR_RAILING = null;
	
	public static final RailingBlock ICE_RAILING = null;
	
	public static final RailingBlock STONE_BRICK_RAILING = null;
	
	public static final RailingBlock NETHER_BRICK_RAILING = null;
	
	public static final RailingBlock END_STONE_BRICK_RAILING = null;
	
	public static final RailingBlock PACKED_ICE_RAILING = null;
	
	public static final RailingBlock PRISMARINE_RAILING = null;
	
	public static final RailingBlock PRISMARINE_BRICK_RAILING = null;
	
	public static final RailingBlock DARK_PRISMARINE_RAILING = null;
	
	public static final RailingBlock RED_NETHER_BRICK_RAILING = null;
	
	public static final RailingBlock BONE_RAILING = null;
	
	public static final RailingBlock BLUE_ICE_RAILING = null;
	
	public static final ParticleLightBlock WHITE_LIGHT = null;
	
	public static final ParticleLightBlock ORANGE_LIGHT = null;
	
	public static final ParticleLightBlock MAGENTA_LIGHT = null;
	
	public static final ParticleLightBlock LIGHT_BLUE_LIGHT = null;
	
	public static final ParticleLightBlock YELLOW_LIGHT = null;
	
	public static final ParticleLightBlock LIME_LIGHT = null;
	
	public static final ParticleLightBlock PINK_LIGHT = null;
	
	public static final ParticleLightBlock GRAY_LIGHT = null;
	
	public static final ParticleLightBlock LIGHT_GRAY_LIGHT = null;
	
	public static final ParticleLightBlock CYAN_LIGHT = null;
	
	public static final ParticleLightBlock PURPLE_LIGHT = null;
	
	public static final ParticleLightBlock BLUE_LIGHT = null;
	
	public static final ParticleLightBlock BROWN_LIGHT = null;
	
	public static final ParticleLightBlock GREEN_LIGHT = null;
	
	public static final ParticleLightBlock RED_LIGHT = null;
	
	public static final ParticleLightBlock BLACK_LIGHT = null;
	
	public static final ElderCatalystBlock ELDER_CATALYST = null;
	
	public static final Block MENAPHITE_SANDSTONE = null;
	
	public static final CloudBlock CLOUD = null;
	
	public static final GravestoneBlock GRAVESTONE = null;
	
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		ModLog.info("Registering blocks");
		
		registerWithItem(event, new AltarBlock(EnumRuneType.AIR), "air_altar");
		registerWithItem(event, new AltarBlock(EnumRuneType.ASTRAL), "astral_altar");
		registerWithItem(event, new AltarBlock(EnumRuneType.BLOOD), "blood_altar");
		registerWithItem(event, new AltarBlock(EnumRuneType.BODY), "body_altar");
		registerWithItem(event, new AltarBlock(EnumRuneType.CHAOS), "chaos_altar");
		registerWithItem(event, new AltarBlock(EnumRuneType.COSMIC), "cosmic_altar");
		registerWithItem(event, new AltarBlock(EnumRuneType.DEATH), "death_altar");
		registerWithItem(event, new AltarBlock(EnumRuneType.EARTH), "earth_altar");
		registerWithItem(event, new AltarBlock(EnumRuneType.FIRE), "fire_altar");
		registerWithItem(event, new AltarBlock(EnumRuneType.LAW), "law_altar");
		registerWithItem(event, new AltarBlock(EnumRuneType.MIND), "mind_altar");
		registerWithItem(event, new AltarBlock(EnumRuneType.NATURE), "nature_altar");
		registerWithItem(event, new AltarBlock(EnumRuneType.SOUL), "soul_altar");
		registerWithItem(event, new AltarBlock(EnumRuneType.WATER), "water_altar");
		registerWithItem(event, new AltarBlock(EnumRuneType.OURANIA), "ourania_altar");
		
		register(event, new AltarEntranceBlock(EnumRuneType.AIR), "air_altar_entrance");
		register(event, new AltarEntranceBlock(EnumRuneType.BLOOD), "blood_altar_entrance");
		register(event, new AltarEntranceBlock(EnumRuneType.BODY), "body_altar_entrance");
		register(event, new AltarEntranceBlock(EnumRuneType.CHAOS), "chaos_altar_entrance");
		register(event, new AltarEntranceBlock(EnumRuneType.COSMIC), "cosmic_altar_entrance");
		register(event, new AltarEntranceBlock(EnumRuneType.DEATH), "death_altar_entrance");
		register(event, new AltarEntranceBlock(EnumRuneType.EARTH), "earth_altar_entrance");
		register(event, new AltarEntranceBlock(EnumRuneType.FIRE), "fire_altar_entrance");
		register(event, new AltarEntranceBlock(EnumRuneType.LAW), "law_altar_entrance");
		register(event, new AltarEntranceBlock(EnumRuneType.MIND), "mind_altar_entrance");
		register(event, new AltarEntranceBlock(EnumRuneType.NATURE), "nature_altar_entrance");
		register(event, new AltarEntranceBlock(EnumRuneType.SOUL), "soul_altar_entrance");
		register(event, new AltarEntranceBlock(EnumRuneType.WATER), "water_altar_entrance");
		
		registerWithItem(event, new AltarPortalBlock(), "altar_portal");
		registerWithItem(event, new RuneEssenceBlock(), "rune_essence_block");
		
		registerWithItem(event, new ElementalObeliskBlock(EnumRuneType.AIR), "air_obelisk");
		registerWithItem(event, new ElementalObeliskBlock(EnumRuneType.EARTH), "earth_obelisk");
		registerWithItem(event, new ElementalObeliskBlock(EnumRuneType.FIRE), "fire_obelisk");
		registerWithItem(event, new ElementalObeliskBlock(EnumRuneType.WATER), "water_obelisk");
		
		registerWithItem(event, new BlackMonolithBlock(), "black_monolith");
		
		registerWithItem(event, new Block(Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0f)), "ruin_stone");
		registerWithItem(event, new ModStairsBlock(Blocks.STONE), "ruin_stone_stairs");
		registerWithItem(event, new ModSlabBlock(Blocks.STONE), "ruin_stone_slab");
		registerWithItem(event, new RailingBlock(Blocks.STONE), "ruin_stone_railing");
		registerWithItem(event, new Block(Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0f)), "temple_bricks");
		registerWithItem(event, new ModStairsBlock(Blocks.STONE_BRICKS), "temple_brick_stairs");
		registerWithItem(event, new ModSlabBlock(Blocks.STONE_BRICKS), "temple_brick_slab");
		registerWithItem(event, new RailingBlock(Blocks.STONE_BRICKS), "temple_brick_railing");
		registerWithItem(event, new Block(Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0f)), "blood_bricks");
		registerWithItem(event, new ModStairsBlock(Blocks.STONE_BRICKS), "blood_brick_stairs");
		registerWithItem(event, new ModSlabBlock(Blocks.STONE_BRICKS), "blood_brick_slab");
		registerWithItem(event, new RailingBlock(Blocks.STONE_BRICKS), "blood_brick_railing");
		registerWithItem(event, new Block(Properties.create(Material.CLAY).slipperiness(0.8F).sound(SoundType.SLIME)), "flesh_block");
		
		registerWithItem(event, new RailingBlock(Blocks.STONE), "stone_railing");
		registerWithItem(event, new RailingBlock(Blocks.POLISHED_GRANITE), "polished_granite_railing");
		registerWithItem(event, new RailingBlock(Blocks.POLISHED_DIORITE), "polished_diorite_railing");
		registerWithItem(event, new RailingBlock(Blocks.POLISHED_ANDESITE), "polished_andesite_railing");
		registerWithItem(event, new RailingBlock(Blocks.QUARTZ_BLOCK), "quartz_railing");
		registerWithItem(event, new RailingBlock(Blocks.RED_SANDSTONE), "red_sandstone_railing");
		registerWithItem(event, new RailingBlock(Blocks.SANDSTONE), "sandstone_railing");
		registerWithItem(event, new RailingBlock(Blocks.SMOOTH_STONE), "smooth_stone_railing");
		registerWithItem(event, new RailingBlock(Blocks.BRICKS), "brick_railing");
		registerWithItem(event, new RailingBlock(Blocks.OBSIDIAN), "obsidian_railing");
		registerWithItem(event, new RailingBlock(Blocks.PURPUR_BLOCK), "purpur_railing");
		registerWithItem(event, new RailingBlock(Blocks.ICE), "ice_railing");
		registerWithItem(event, new RailingBlock(Blocks.STONE_BRICKS), "stone_brick_railing");
		registerWithItem(event, new RailingBlock(Blocks.NETHER_BRICKS), "nether_brick_railing");
		registerWithItem(event, new RailingBlock(Blocks.END_STONE_BRICKS), "end_stone_brick_railing");
		registerWithItem(event, new RailingBlock(Blocks.PACKED_ICE), "packed_ice_railing");
		registerWithItem(event, new RailingBlock(Blocks.PRISMARINE), "prismarine_railing");
		registerWithItem(event, new RailingBlock(Blocks.PRISMARINE_BRICKS), "prismarine_brick_railing");
		registerWithItem(event, new RailingBlock(Blocks.DARK_PRISMARINE), "dark_prismarine_railing");
		registerWithItem(event, new RailingBlock(Blocks.RED_NETHER_BRICKS), "red_nether_brick_railing");
		registerWithItem(event, new RailingBlock(Blocks.BONE_BLOCK), "bone_railing");
		registerWithItem(event, new RailingBlock(Blocks.BLUE_ICE), "blue_ice_railing");
		
		register(event, new ParticleLightBlock(EnumColour.WHITE), "white_light");
		register(event, new ParticleLightBlock(EnumColour.ORANGE), "orange_light");
		register(event, new ParticleLightBlock(EnumColour.MAGENTA), "magenta_light");
		register(event, new ParticleLightBlock(EnumColour.LIGHT_BLUE), "light_blue_light");
		register(event, new ParticleLightBlock(EnumColour.YELLOW), "yellow_light");
		register(event, new ParticleLightBlock(EnumColour.LIME), "lime_light");
		register(event, new ParticleLightBlock(EnumColour.PINK), "pink_light");
		register(event, new ParticleLightBlock(EnumColour.GRAY), "gray_light");
		register(event, new ParticleLightBlock(EnumColour.LIGHT_GRAY), "light_gray_light");
		register(event, new ParticleLightBlock(EnumColour.CYAN), "cyan_light");
		register(event, new ParticleLightBlock(EnumColour.PURPLE), "purple_light");
		register(event, new ParticleLightBlock(EnumColour.BLUE), "blue_light");
		register(event, new ParticleLightBlock(EnumColour.BROWN), "brown_light");
		register(event, new ParticleLightBlock(EnumColour.GREEN), "green_light");
		register(event, new ParticleLightBlock(EnumColour.RED), "red_light");
		register(event, new ParticleLightBlock(EnumColour.BLACK), "black_light");
		
		registerWithItem(event, new ElderCatalystBlock(), "elder_catalyst");
		
		registerWithItem(event, new Block(Properties.create(Material.ROCK, MaterialColor.SAND).hardnessAndResistance(0.8F)), "menaphite_sandstone");
		registerWithItem(event, new CloudBlock(), "cloud");
		registerWithItem(event, new GravestoneBlock(), "gravestone");
	}
	
	private static void register(RegistryEvent.Register<Block> event, Block block, String name) {
		block.setRegistryName(RuneMysteries.MOD_ID, name);
		event.getRegistry().register(block);
	}
	
	private static void registerWithItem(RegistryEvent.Register<Block> event, Block block, String name) {
		register(event, block, name);
		ModItems.addBlock(block);
	}
}
