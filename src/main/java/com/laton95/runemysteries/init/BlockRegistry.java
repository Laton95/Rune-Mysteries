package com.laton95.runemysteries.init;

import java.util.ArrayList;

import com.laton95.runemysteries.block.BlockAltarPortal;
import com.laton95.runemysteries.block.BlockCamoMine;
import com.laton95.runemysteries.block.BlockOuraniaAltar;
import com.laton95.runemysteries.block.BlockParticleLight;
import com.laton95.runemysteries.block.RMModRail;
import com.laton95.runemysteries.block.BlockRuneAltar;
import com.laton95.runemysteries.block.BlockRuneAltarEntrance;
import com.laton95.runemysteries.block.BlockRuneEssence;
import com.laton95.runemysteries.block.BlockStationStone;
import com.laton95.runemysteries.block.RMModBlock;
import com.laton95.runemysteries.block.RMModSlab;
import com.laton95.runemysteries.block.RMModStairs;
import com.laton95.runemysteries.util.LogHelper;
import com.laton95.runemysteries.util.ModConfig;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class BlockRegistry {
	private static ArrayList<Block> blockList = new ArrayList<>();
	private static ArrayList<RMModSlab> halfSlabList = new ArrayList<>();
	private static ArrayList<RMModSlab> doubleSlabList = new ArrayList<>();

	public static final RMModBlock RUNE_ESSENCE_FINITE = new RMModBlock("rune_Essence_Block_Finite", Material.ROCK, 1.5f, 10.0f,
			"pickaxe", 1, true, ItemRegistry.RUNE_ESSENCE);
	public static final RMModBlock RUIN_BLOCK = new RMModBlock("ruin_Block", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	public static final BlockStationStone STATION_STONE = new BlockStationStone();
	public static final RMModBlock TEMPLE_BLOCK = new RMModBlock("temple_Block", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1,
			true);
	public static final RMModStairs TEMPLE_BLOCK_STAIRS = new RMModStairs("temple_Block_Stairs", "pickaxe", 1,
			true, TEMPLE_BLOCK.getDefaultState());
	public static final RMModSlab.Half TEMPLE_BLOCK_HALF_SLAB = new RMModSlab.Half("temple_Block_Slab_Half", Material.ROCK, 1.5f,
			10.0f, "pickaxe", 1, true);
	public static final RMModSlab.Double TEMPLE_BLOCK_DOUBLE_SLAB = new RMModSlab.Double("temple_Block_Slab_Double",
			Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	public static final RMModRail TEMPLE_BLOCK_RAIL = new RMModRail("temple_Block_Rail", Material.ROCK, 1.5f, 10.0f, "pickaxe",
			1, true);
	public static final RMModBlock BLOOD_BLOCK = new RMModBlock("blood_Block", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	public static final RMModStairs BLOOD_BLOCK_STAIRS = new RMModStairs("blood_Block_Stairs", "pickaxe", 1, true,
			BLOOD_BLOCK.getDefaultState());
	public static final RMModSlab.Half BLOOD_BLOCK_HALF_SLAB = new RMModSlab.Half("blood_Block_Slab_Half", Material.ROCK, 1.5f,
			10.0f, "pickaxe", 1, true);
	public static final RMModSlab.Double BLOOD_BLOCK_DOUBLE_SLAB = new RMModSlab.Double("blood_Block_Slab_Double", Material.ROCK,
			1.5f, 10.0f, "pickaxe", 1, true);
	public static final RMModRail BLOOD_BLOCK_RAIL = new RMModRail("blood_Block_Rail", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1,
			true);
	public static final RMModBlock FLESH_BLOCK = new RMModBlock("flesh_Block", Material.CAKE, 1.5f, 10.0f, "pickaxe", 1, true);
	public static final RMModRail STONEBRICK_RAIL = new RMModRail("stonebrick_Rail", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1,
			true);

	public static final BlockRuneEssence RUNE_ESSENCE = new BlockRuneEssence();
	public static final BlockRuneAltar AIR_ALTAR = new BlockRuneAltar("air_Altar_Block", ItemRegistry.AIR_RUNE);
	public static final BlockRuneAltar ASTRAL_ALTAR = new BlockRuneAltar("astral_Altar_Block", ItemRegistry.ASTRAL_RUNE);
	public static final BlockRuneAltar BLOOD_ALTAR = new BlockRuneAltar("blood_Altar_Block", ItemRegistry.BLOOD_RUNE);
	public static final BlockRuneAltar BODY_ALTAR = new BlockRuneAltar("body_Altar_Block", ItemRegistry.BODY_RUNE);
	public static final BlockRuneAltar CHAOS_ALTAR = new BlockRuneAltar("chaos_Altar_Block", ItemRegistry.CHAOS_RUNE);
	public static final BlockRuneAltar COSMIC_ALTAR = new BlockRuneAltar("cosmic_Altar_Block", ItemRegistry.COSMIC_RUNE);
	public static final BlockRuneAltar DEATH_ALTAR = new BlockRuneAltar("death_Altar_Block", ItemRegistry.DEATH_RUNE);
	public static final BlockRuneAltar EARTH_ALTAR = new BlockRuneAltar("earth_Altar_Block", ItemRegistry.EARTH_RUNE);
	public static final BlockRuneAltar FIRE__ALTAR = new BlockRuneAltar("fire_Altar_Block", ItemRegistry.FIRE_RUNE);
	public static final BlockRuneAltar LAW_ALTAR = new BlockRuneAltar("law_Altar_Block", ItemRegistry.LAW_RUNE);
	public static final BlockRuneAltar MIND_ALTAR = new BlockRuneAltar("mind_Altar_Block", ItemRegistry.MIND_RUNE);
	public static final BlockRuneAltar NATURE_ALTAR = new BlockRuneAltar("nature_Altar_Block", ItemRegistry.NATURE_RUNE);
	public static final BlockRuneAltar SOUL_ALTAR = new BlockRuneAltar("soul_Altar_Block", ItemRegistry.SOUL_RUNE);
	public static final BlockRuneAltar WATER_ALTAR = new BlockRuneAltar("water_Altar_Block", ItemRegistry.WATER_RUNE);
	public static final BlockOuraniaAltar OURANIA_ALTAR = new BlockOuraniaAltar();

	public static final BlockRuneAltarEntrance AIR_ALTAR_ENTRANCE = new BlockRuneAltarEntrance("air_Altar_Entrance_Block",
			ItemRegistry.AIR_TALISMAN, "air");
	public static final BlockRuneAltarEntrance BLOOD_ALTAR_ENTRANCE = new BlockRuneAltarEntrance("blood_Altar_Entrance_Block",
			ItemRegistry.BLOOD_TALISMAN, "blood");
	public static final BlockRuneAltarEntrance BODY_ALTAR_ENTRANCE = new BlockRuneAltarEntrance("body_Altar_Entrance_Block",
			ItemRegistry.BODY_TALISMAN, "body");
	public static final BlockRuneAltarEntrance CHAOS_ALTAR_ENTRANCE = new BlockRuneAltarEntrance("chaos_Altar_Entrance_Block",
			ItemRegistry.CHAOS_TALISMAN, "chaos");
	public static final BlockRuneAltarEntrance COSMIC_ALTAR_ENTRANCE = new BlockRuneAltarEntrance("cosmic_Altar_Entrance_Block",
			ItemRegistry.COSMIC_TALISMAN, "cosmic");
	public static final BlockRuneAltarEntrance DEATH_ALTAR_ENTRANCE = new BlockRuneAltarEntrance("death_Altar_Entrance_Block",
			ItemRegistry.DEATH_TALISMAN, "death");
	public static final BlockRuneAltarEntrance EARTH_ALTAR_ENTRANCE = new BlockRuneAltarEntrance("earth_Altar_Entrance_Block",
			ItemRegistry.EARTH_TALISMAN, "earth");
	public static final BlockRuneAltarEntrance FIRE_ALTAR_ENTRANCE = new BlockRuneAltarEntrance("fire_Altar_Entrance_Block",
			ItemRegistry.FIRE_TALISMAN, "fire");
	public static final BlockRuneAltarEntrance LAW_ALTAR_ENTRANCE = new BlockRuneAltarEntrance("law_Altar_Entrance_Block",
			ItemRegistry.LAW_TALISMAN, "law");
	public static final BlockRuneAltarEntrance MIND_ALTAR_ENTRANCE = new BlockRuneAltarEntrance("mind_Altar_Entrance_Block",
			ItemRegistry.MIND_TALISMAN, "mind");
	public static final BlockRuneAltarEntrance NATURE_ALTAR_ENTRANCE = new BlockRuneAltarEntrance("nature_Altar_Entrance_Block",
			ItemRegistry.NATURE_TALISMAN, "nature");
	public static final BlockRuneAltarEntrance SOUL_ALTAR_ENTRANCE = new BlockRuneAltarEntrance("soul_Altar_Entrance_Block",
			ItemRegistry.SOUL_TALISMAN, "soul");
	public static final BlockRuneAltarEntrance WATER_ALTAR_ENTRANCE = new BlockRuneAltarEntrance("water_Altar_Entrance_Block",
			ItemRegistry.WATER_TALISMAN, "water");

	public static final BlockAltarPortal AIR_ALTAR_PORTAL = new BlockAltarPortal("air_Altar_Exit_Portal", "air", 0);
	public static final BlockAltarPortal BLOOD_ALTAR_PORTAL = new BlockAltarPortal("blood_Altar_Exit_Portal", "blood", 0);
	public static final BlockAltarPortal BODY_ALTAR_PORTAL = new BlockAltarPortal("body_Altar_Exit_Portal", "body", 0);
	public static final BlockAltarPortal CHAOS_ALTAR_PORTAL = new BlockAltarPortal("chaos_Altar_Exit_Portal", "chaos", -1);
	public static final BlockAltarPortal COSMIC_ALTAR_PORTAL = new BlockAltarPortal("cosmic_Altar_Exit_Portal", "cosmic", 1);
	public static final BlockAltarPortal DEATH_ALTAR_PORTAL = new BlockAltarPortal("death_Altar_Exit_Portal", "death", 0);
	public static final BlockAltarPortal EARTH__ALTAR_PORTAL = new BlockAltarPortal("earth_Altar_Exit_Portal", "earth", 0);
	public static final BlockAltarPortal FIRE_ALTAR_PORTAL = new BlockAltarPortal("fire_Altar_Exit_Portal", "fire", 0);
	public static final BlockAltarPortal LAW_ALTAR_PORTAL = new BlockAltarPortal("law_Altar_Exit_Portal", "law", 0);
	public static final BlockAltarPortal MIND_ALTAR_PORTAL = new BlockAltarPortal("mind_Altar_Exit_Portal", "mind", 0);
	public static final BlockAltarPortal NATURE_ALTAR_PORTAL = new BlockAltarPortal("nature_Altar_Exit_Portal", "nature", 0);
	public static final BlockAltarPortal SOUL_ALTAR_PORTAL = new BlockAltarPortal("soul_Altar_Exit_Portal", "soul", 0);
	public static final BlockAltarPortal WATER_ALTAR_PORTAL = new BlockAltarPortal("water_Altar_Exit_Portal", "water", 0);

	public static final BlockParticleLight PARTICLE_LIGHT = new BlockParticleLight("particle_Light", Material.BARRIER, 0f, 0f,
			"pickaxe", 0, true);
	
	public static final BlockCamoMine CAMO_MINE = new BlockCamoMine("camo_mine", Material.TNT, 0f, 0f, "shovel", 0, true);

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		LogHelper.info("Registering blocks");
		makeBlockList();
		for (Block block : blockList) {
			ItemBlock itemBlock = new ItemBlock(block);
			itemBlock.setRegistryName(block.getRegistryName());
			ItemRegistry.addItemBlock(itemBlock);
			event.getRegistry().register(block);
		}

		int i = 0;
		for (RMModSlab slab : halfSlabList) {
			RMModSlab doubleSlab = doubleSlabList.get(i);
			ItemBlock itemBlock = new ItemSlab(slab, slab, doubleSlab);
			itemBlock.setRegistryName(slab.getRegistryName());
			ItemRegistry.addItemBlock(itemBlock);
			slab.setDroppedItem(itemBlock);
			doubleSlab.setDroppedItem(itemBlock);
			event.getRegistry().register(slab);
			event.getRegistry().register(doubleSlab);
			i++;
		}
	}

	private static void makeBlockList() {
		blockList.add(RUNE_ESSENCE);
		blockList.add(RUNE_ESSENCE_FINITE);
		blockList.add(RUIN_BLOCK);
		blockList.add(STATION_STONE);
		blockList.add(TEMPLE_BLOCK);
		blockList.add(TEMPLE_BLOCK_STAIRS);
		blockList.add(TEMPLE_BLOCK_RAIL);
		blockList.add(BLOOD_BLOCK);
		blockList.add(BLOOD_BLOCK_STAIRS);
		blockList.add(BLOOD_BLOCK_RAIL);
		blockList.add(FLESH_BLOCK);
		blockList.add(STONEBRICK_RAIL);

		halfSlabList.add(BLOOD_BLOCK_HALF_SLAB);
		doubleSlabList.add(BLOOD_BLOCK_DOUBLE_SLAB);

		halfSlabList.add(TEMPLE_BLOCK_HALF_SLAB);
		doubleSlabList.add(TEMPLE_BLOCK_DOUBLE_SLAB);

		blockList.add(AIR_ALTAR);
		blockList.add(ASTRAL_ALTAR);
		blockList.add(BLOOD_ALTAR);
		blockList.add(BODY_ALTAR);
		blockList.add(CHAOS_ALTAR);
		blockList.add(COSMIC_ALTAR);
		blockList.add(DEATH_ALTAR);
		blockList.add(EARTH_ALTAR);
		blockList.add(FIRE__ALTAR);
		blockList.add(LAW_ALTAR);
		blockList.add(MIND_ALTAR);
		blockList.add(NATURE_ALTAR);
		blockList.add(SOUL_ALTAR);
		blockList.add(WATER_ALTAR);
		blockList.add(OURANIA_ALTAR);

		blockList.add(AIR_ALTAR_ENTRANCE);
		blockList.add(BLOOD_ALTAR_ENTRANCE);
		blockList.add(BODY_ALTAR_ENTRANCE);
		blockList.add(CHAOS_ALTAR_ENTRANCE);
		blockList.add(COSMIC_ALTAR_ENTRANCE);
		blockList.add(DEATH_ALTAR_ENTRANCE);
		blockList.add(EARTH_ALTAR_ENTRANCE);
		blockList.add(FIRE_ALTAR_ENTRANCE);
		blockList.add(LAW_ALTAR_ENTRANCE);
		blockList.add(MIND_ALTAR_ENTRANCE);
		blockList.add(NATURE_ALTAR_ENTRANCE);
		blockList.add(SOUL_ALTAR_ENTRANCE);
		blockList.add(WATER_ALTAR_ENTRANCE);

		blockList.add(AIR_ALTAR_PORTAL);
		blockList.add(BLOOD_ALTAR_PORTAL);
		blockList.add(BODY_ALTAR_PORTAL);
		blockList.add(CHAOS_ALTAR_PORTAL);
		blockList.add(COSMIC_ALTAR_PORTAL);
		blockList.add(DEATH_ALTAR_PORTAL);
		blockList.add(EARTH__ALTAR_PORTAL);
		blockList.add(FIRE_ALTAR_PORTAL);
		blockList.add(LAW_ALTAR_PORTAL);
		blockList.add(MIND_ALTAR_PORTAL);
		blockList.add(NATURE_ALTAR_PORTAL);
		blockList.add(SOUL_ALTAR_PORTAL);
		blockList.add(WATER_ALTAR_PORTAL);

		blockList.add(PARTICLE_LIGHT);
		
		blockList.add(CAMO_MINE);
	}

	public static void setupDimIDs() {
		AIR_ALTAR_ENTRANCE.setDimID(ModConfig.DIMENSIONS.airTempleDimID);
		BLOOD_ALTAR_ENTRANCE.setDimID(ModConfig.DIMENSIONS.bloodTempleDimID);
		BODY_ALTAR_ENTRANCE.setDimID(ModConfig.DIMENSIONS.bodyTempleDimID);
		CHAOS_ALTAR_ENTRANCE.setDimID(ModConfig.DIMENSIONS.chaosTempleDimID);
		COSMIC_ALTAR_ENTRANCE.setDimID(ModConfig.DIMENSIONS.cosmicTempleDimID);
		DEATH_ALTAR_ENTRANCE.setDimID(ModConfig.DIMENSIONS.deathTempleDimID);
		EARTH_ALTAR_ENTRANCE.setDimID(ModConfig.DIMENSIONS.earthTempleDimID);
		FIRE_ALTAR_ENTRANCE.setDimID(ModConfig.DIMENSIONS.fireTempleDimID);
		LAW_ALTAR_ENTRANCE.setDimID(ModConfig.DIMENSIONS.lawTempleDimID);
		MIND_ALTAR_ENTRANCE.setDimID(ModConfig.DIMENSIONS.mindTempleDimID);
		NATURE_ALTAR_ENTRANCE.setDimID(ModConfig.DIMENSIONS.natureTempleDimID);
		SOUL_ALTAR_ENTRANCE.setDimID(ModConfig.DIMENSIONS.soulTempleDimID);
		WATER_ALTAR_ENTRANCE.setDimID(ModConfig.DIMENSIONS.waterTempleDimID);
	}
}
