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
public class ModBlocks {
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":air_altar")
	public static BlockAltar AIR_ALTAR;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":astral_altar")
	public static BlockAltar ASTRAL_ALTAR;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":blood_altar")
	public static BlockAltar BLOOD_ALTAR;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":body_altar")
	public static BlockAltar BODY_ALTAR;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":chaos_altar")
	public static BlockAltar CHAOS_ALTAR;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":cosmic_altar")
	public static BlockAltar COSMIC_ALTAR;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":death_altar")
	public static BlockAltar DEATH_ALTAR;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":earth_altar")
	public static BlockAltar EARTH_ALTAR;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":fire_altar")
	public static BlockAltar FIRE_ALTAR;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":law_altar")
	public static BlockAltar LAW_ALTAR;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":mind_altar")
	public static BlockAltar MIND_ALTAR;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":nature_altar")
	public static BlockAltar NATURE_ALTAR;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":soul_altar")
	public static BlockAltar SOUL_ALTAR;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":water_altar")
	public static BlockAltar WATER_ALTAR;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":ourania_altar")
	public static BlockAltar OURANIA_ALTAR;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":air_altar_entrance")
	public static BlockAltarEntrance AIR_ALTAR_ENTRANCE;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":blood_altar_entrance")
	public static BlockAltarEntrance BLOOD_ALTAR_ENTRANCE;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":body_altar_entrance")
	public static BlockAltarEntrance BODY_ALTAR_ENTRANCE;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":chaos_altar_entrance")
	public static BlockAltarEntrance CHAOS_ALTAR_ENTRANCE;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":cosmic_altar_entrance")
	public static BlockAltarEntrance COSMIC_ALTAR_ENTRANCE;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":death_altar_entrance")
	public static BlockAltarEntrance DEATH_ALTAR_ENTRANCE;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":earth_altar_entrance")
	public static BlockAltarEntrance EARTH_ALTAR_ENTRANCE;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":fire_altar_entrance")
	public static BlockAltarEntrance FIRE_ALTAR_ENTRANCE;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":law_altar_entrance")
	public static BlockAltarEntrance LAW_ALTAR_ENTRANCE;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":mind_altar_entrance")
	public static BlockAltarEntrance MIND_ALTAR_ENTRANCE;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":nature_altar_entrance")
	public static BlockAltarEntrance NATURE_ALTAR_ENTRANCE;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":soul_altar_entrance")
	public static BlockAltarEntrance SOUL_ALTAR_ENTRANCE;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":water_altar_entrance")
	public static BlockAltarEntrance WATER_ALTAR_ENTRANCE;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":altar_portal")
	public static BlockAltarPortal ALTAR_PORTAL;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":rune_essence")
	public static BlockRuneEssence RUNE_ESSENCE;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":air_obelisk")
	public static BlockElementalObelisk AIR_OBELISK;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":earth_obelisk")
	public static BlockElementalObelisk EARTH_OBELISK;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":fire_obelisk")
	public static BlockElementalObelisk FIRE_OBELISK;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":water_obelisk")
	public static BlockElementalObelisk WATER_OBELISK;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":black_monolith")
	public static BlockBlackMonolith BLACK_MONOLITH;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":ruin_stone")
	public static ModBlock RUIN_STONE;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":ruin_stone_stairs")
	public static ModBlockStairs RUIN_STONE_STAIRS;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":ruin_stone_slab")
	public static ModBlockSlab RUIN_STONE_SLAB;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":ruin_stone_railing")
	public static BlockRailing RUIN_STONE_RAILING;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":temple_bricks")
	public static ModBlock TEMPLE_BRICKS;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":temple_brick_stairs")
	public static ModBlockStairs TEMPLE_BRICK_STAIRS;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":temple_brick_slab")
	public static ModBlockSlab TEMPLE_BRICK_SLAB;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":temple_brick_railing")
	public static BlockRailing TEMPLE_BRICK_RAILING;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":blood_bricks")
	public static ModBlock BLOOD_BRICKS;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":blood_brick_stairs")
	public static ModBlockStairs BLOOD_BRICK_STAIRS;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":blood_brick_slab")
	public static ModBlockSlab BLOOD_BRICK_SLAB;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":blood_brick_railing")
	public static BlockRailing BLOOD_BRICK_RAILING;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":flesh_block")
	public static ModBlock FLESH_BLOCK;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":stone_railing")
	public static BlockRailing STONE_RAILING;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":polished_granite_railing")
	public static BlockRailing POLISHED_GRANITE_RAILING;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":polished_diorite_railing")
	public static BlockRailing POLISHED_DIORITE_RAILING;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":polished_andesite_railing")
	public static BlockRailing POLISHED_ANDESITE_RAILING;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":quartz_railing")
	public static BlockRailing QUARTZ_RAILING;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":red_sandstone_railing")
	public static BlockRailing RED_SANDSTONE_RAILING;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":sandstone_railing")
	public static BlockRailing SANDSTONE_RAILING;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":smooth_stone_railing")
	public static BlockRailing SMOOTH_STONE_RAILING;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":brick_railing")
	public static BlockRailing BRICK_RAILING;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":obsidian_railing")
	public static BlockRailing OBSIDIAN_RAILING;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":purpur_railing")
	public static BlockRailing PURPUR_RAILING;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":ice_railing")
	public static BlockRailing ICE_RAILING;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":stone_brick_railing")
	public static BlockRailing STONE_BRICK_RAILING;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":nether_brick_railing")
	public static BlockRailing NETHER_BRICK_RAILING;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":end_stone_brick_railing")
	public static BlockRailing END_STONE_BRICK_RAILING;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":packed_ice_railing")
	public static BlockRailing PACKED_ICE_RAILING;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":prismarine_railing")
	public static BlockRailing PRISMARINE_RAILING;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":prismarine_brick_railing")
	public static BlockRailing PRISMARINE_BRICK_RAILING;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":dark_prismarine_railing")
	public static BlockRailing DARK_PRISMARINE_RAILING;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":red_nether_brick_railing")
	public static BlockRailing RED_NETHER_BRICK_RAILING;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":bone_railing")
	public static BlockRailing BONE_RAILING;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":blue_ice_railing")
	public static BlockRailing BLUE_ICE_RAILING;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":white_light")
	public static BlockParticleLight WHITE_LIGHT;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":orange_light")
	public static BlockParticleLight ORANGE_LIGHT;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":magenta_light")
	public static BlockParticleLight MAGENTA_LIGHT;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":light_blue_light")
	public static BlockParticleLight LIGHT_BLUE_LIGHT;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":yellow_light")
	public static BlockParticleLight YELLOW_LIGHT;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":lime_light")
	public static BlockParticleLight LIME_LIGHT;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":pink_light")
	public static BlockParticleLight PINK_LIGHT;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":gray_light")
	public static BlockParticleLight GRAY_LIGHT;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":light_gray_light")
	public static BlockParticleLight LIGHT_GRAY_LIGHT;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":cyan_light")
	public static BlockParticleLight CYAN_LIGHT;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":purple_light")
	public static BlockParticleLight PURPLE_LIGHT;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":blue_light")
	public static BlockParticleLight BLUE_LIGHT;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":brown_light")
	public static BlockParticleLight BROWN_LIGHT;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":green_light")
	public static BlockParticleLight GREEN_LIGHT;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":red_light")
	public static BlockParticleLight RED_LIGHT;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":black_light")
	public static BlockParticleLight BLACK_LIGHT;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":underwater_torch")
	public static BlockUnderwaterTorch UNDERWATER_TORCH;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":underwater_wall_torch")
	public static BlockUnderwaterTorchWall UNDERWATER_WALL_TORCH;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":elder_catalyst")
	public static BlockElderCatalyst ELDER_CATALYST;
	
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
		register(event, new BlockRuneEssence(), "rune_essence");
		
		register(event, new BlockElementalObelisk(ModItems.AIR_SHARD, EnumRuneType.AIR), "air_obelisk");
		register(event, new BlockElementalObelisk(ModItems.EARTH_SHARD, EnumRuneType.EARTH), "earth_obelisk");
		register(event, new BlockElementalObelisk(ModItems.FIRE_SHARD, EnumRuneType.FIRE), "fire_obelisk");
		register(event, new BlockElementalObelisk(ModItems.WATER_SHARD, EnumRuneType.WATER), "water_obelisk");
		
		register(event, new BlockBlackMonolith(), "black_monolith");
		
		register(event, new ModBlock(), "ruin_stone");
		register(event, new ModBlockStairs(RUIN_STONE), "ruin_stone_stairs");
		register(event, new ModBlockSlab(RUIN_STONE), "ruin_stone_slab");
		register(event, new BlockRailing(RUIN_STONE), "ruin_stone_railing");
		register(event, new ModBlock(), "temple_bricks");
		register(event, new ModBlockStairs(TEMPLE_BRICKS), "temple_brick_stairs");
		register(event, new ModBlockSlab(TEMPLE_BRICKS), "temple_brick_slab");
		register(event, new ModBlock(), "temple_brick_railing");
		register(event, new ModBlock(), "blood_bricks");
		register(event, new ModBlockStairs(BLOOD_BRICKS), "blood_brick_stairs");
		register(event, new ModBlockSlab(BLOOD_BRICKS), "blood_brick_slab");
		register(event, new BlockRailing(BLOOD_BRICKS), "blood_brick_railing");
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
