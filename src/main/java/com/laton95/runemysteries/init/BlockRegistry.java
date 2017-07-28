package com.laton95.runemysteries.init;

import java.util.ArrayList;
import java.util.Map;

import com.laton95.runemysteries.block.BlockAltarPortal;
import com.laton95.runemysteries.block.BlockOuraniaAltar;
import com.laton95.runemysteries.block.BlockParticleLight;
import com.laton95.runemysteries.block.BlockRail;
import com.laton95.runemysteries.block.BlockRailGate;
import com.laton95.runemysteries.block.BlockRuneAltar;
import com.laton95.runemysteries.block.BlockRuneAltarEntrance;
import com.laton95.runemysteries.block.BlockRuneEssence;
import com.laton95.runemysteries.block.BlockStationStone;
import com.laton95.runemysteries.block.RMModBlock;
import com.laton95.runemysteries.block.RMModSlab;
import com.laton95.runemysteries.block.RMModStairs;
import com.laton95.runemysteries.utility.LogHelper;
import com.laton95.runemysteries.utility.ModConfig;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class BlockRegistry {
	private static ArrayList<Block> blockList = new ArrayList<Block>();
	private static ArrayList<RMModSlab> halfSlabList = new ArrayList<RMModSlab>();
	private static ArrayList<RMModSlab> doubleSlabList = new ArrayList<RMModSlab>();

	public static RMModBlock runeEssenceFinite = new RMModBlock("rune_Essence_Block_Finite", Material.ROCK, 1.5f, 10.0f,
			"pickaxe", 1, true, ItemRegistry.runeEssence);
	public static RMModBlock ruinBlock = new RMModBlock("ruin_Block", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	public static BlockStationStone stationStone = new BlockStationStone();
	public static RMModBlock templeBlock = new RMModBlock("temple_Block", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1,
			true);
	public static RMModStairs templeBlockStairs = new RMModStairs("temple_Block_Stairs", 1.5f, 10.0f, "pickaxe", 1, true, templeBlock.getDefaultState());
	public static RMModSlab.Half templeBlockHalfSlab = new RMModSlab.Half("temple_Block_Slab_Half", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	public static RMModSlab.Double templeBlockDoubleSlab = new RMModSlab.Double("temple_Block_Slab_Double", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	public static BlockRail templeBlockRail = new BlockRail("temple_Block_Rail", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	public static BlockRailGate templeBlockRailGate = new BlockRailGate("temple_Block_Rail Gate", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	public static RMModBlock bloodBlock = new RMModBlock("blood_Block", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	public static RMModStairs bloodBlockStairs = new RMModStairs("blood_Block_Stairs", 1.5f, 10.0f, "pickaxe", 1, true, bloodBlock.getDefaultState());
	public static RMModSlab.Half bloodBlockHalfSlab = new RMModSlab.Half("blood_Block_Slab_Half", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	public static RMModSlab.Double bloodBlockDoubleSlab = new RMModSlab.Double("blood_Block_Slab_Double", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	public static BlockRail bloodBlockRail = new BlockRail("blood_Block_Rail", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	public static BlockRailGate bloodBlockRailGate = new BlockRailGate("blood_Block_Rail_Gate", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	public static RMModBlock fleshBlock = new RMModBlock("flesh_Block", Material.CAKE, 1.5f, 10.0f, "pickaxe", 1, true);
	private static BlockRail stonebrickRail = new BlockRail("stonebrick_Rail", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	public static BlockRailGate stonebrickBlockRailGate = new BlockRailGate("stonebrick_Block_Rail_Gate", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);

	public static BlockRuneEssence runeEssence = new BlockRuneEssence();
	public static BlockRuneAltar airAltar = new BlockRuneAltar("air_Altar_Block", ItemRegistry.airRune);
	public static BlockRuneAltar astralAltar = new BlockRuneAltar("astral_Altar_Block", ItemRegistry.astralRune);
	public static BlockRuneAltar bloodAltar = new BlockRuneAltar("blood_Altar_Block", ItemRegistry.bloodRune);
	public static BlockRuneAltar bodyAltar = new BlockRuneAltar("body_Altar_Block", ItemRegistry.bodyRune);
	public static BlockRuneAltar chaosAltar = new BlockRuneAltar("chaos_Altar_Block", ItemRegistry.chaosRune);
	public static BlockRuneAltar cosmicAltar = new BlockRuneAltar("cosmic_Altar_Block", ItemRegistry.cosmicRune);
	public static BlockRuneAltar deathAltar = new BlockRuneAltar("death_Altar_Block", ItemRegistry.deathRune);
	public static BlockRuneAltar earthAltar = new BlockRuneAltar("earth_Altar_Block", ItemRegistry.earthRune);
	public static BlockRuneAltar fireAltar = new BlockRuneAltar("fire_Altar_Block", ItemRegistry.fireRune);
	public static BlockRuneAltar lawAltar = new BlockRuneAltar("law_Altar_Block", ItemRegistry.lawRune);
	public static BlockRuneAltar mindAltar = new BlockRuneAltar("mind_Altar_Block", ItemRegistry.mindRune);
	public static BlockRuneAltar natureAltar = new BlockRuneAltar("nature_Altar_Block", ItemRegistry.natureRune);
	public static BlockRuneAltar soulAltar = new BlockRuneAltar("soul_Altar_Block", ItemRegistry.soulRune);
	public static BlockRuneAltar waterAltar = new BlockRuneAltar("water_Altar_Block", ItemRegistry.waterRune);
	public static BlockOuraniaAltar ouraniaAltar = new BlockOuraniaAltar();

	public static BlockRuneAltarEntrance airAltarEntrance = new BlockRuneAltarEntrance("air_Altar_Entrance_Block",
			ItemRegistry.airTalisman, "air");
	public static BlockRuneAltarEntrance bloodAltarEntrance = new BlockRuneAltarEntrance("blood_Altar_Entrance_Block",
			ItemRegistry.bloodTalisman, "blood");
	public static BlockRuneAltarEntrance bodyAltarEntrance = new BlockRuneAltarEntrance("body_Altar_Entrance_Block",
			ItemRegistry.bodyTalisman, "body");
	public static BlockRuneAltarEntrance chaosAltarEntrance = new BlockRuneAltarEntrance("chaos_Altar_Entrance_Block",
			ItemRegistry.chaosTalisman, "chaos");
	public static BlockRuneAltarEntrance cosmicAltarEntrance = new BlockRuneAltarEntrance("cosmic_Altar_Entrance_Block",
			ItemRegistry.cosmicTalisman, "cosmic");
	public static BlockRuneAltarEntrance deathAltarEntrance = new BlockRuneAltarEntrance("death_Altar_Entrance_Block",
			ItemRegistry.deathTalisman, "death");
	public static BlockRuneAltarEntrance earthAltarEntrance = new BlockRuneAltarEntrance("earth_Altar_Entrance_Block",
			ItemRegistry.earthTalisman, "earth");
	public static BlockRuneAltarEntrance fireAltarEntrance = new BlockRuneAltarEntrance("fire_Altar_Entrance_Block",
			ItemRegistry.fireTalisman, "fire");
	public static BlockRuneAltarEntrance lawAltarEntrance = new BlockRuneAltarEntrance("law_Altar_Entrance_Block",
			ItemRegistry.lawTalisman, "law");
	public static BlockRuneAltarEntrance mindAltarEntrance = new BlockRuneAltarEntrance("mind_Altar_Entrance_Block",
			ItemRegistry.mindTalisman, "mind");
	public static BlockRuneAltarEntrance natureAltarEntrance = new BlockRuneAltarEntrance("nature_Altar_Entrance_Block",
			ItemRegistry.natureTalisman, "nature");
	public static BlockRuneAltarEntrance soulAltarEntrance = new BlockRuneAltarEntrance("soul_Altar_Entrance_Block",
			ItemRegistry.soulTalisman, "soul");
	public static BlockRuneAltarEntrance waterAltarEntrance = new BlockRuneAltarEntrance("water_Altar_Entrance_Block",
			ItemRegistry.waterTalisman, "water");

	public static BlockAltarPortal airAltarPortal = new BlockAltarPortal("air_Altar_Exit_Portal", "air", 0);
	public static BlockAltarPortal bloodAltarPortal = new BlockAltarPortal("blood_Altar_Exit_Portal", "blood", 0);
	public static BlockAltarPortal bodyAltarPortal = new BlockAltarPortal("body_Altar_Exit_Portal", "body", 0);
	public static BlockAltarPortal chaosAltarPortal = new BlockAltarPortal("chaos_Altar_Exit_Portal", "chaos", -1);
	public static BlockAltarPortal cosmicAltarPortal = new BlockAltarPortal("cosmic_Altar_Exit_Portal", "cosmic", 1);
	public static BlockAltarPortal deathAltarPortal = new BlockAltarPortal("death_Altar_Exit_Portal", "death", 0);
	public static BlockAltarPortal earthAltarPortal = new BlockAltarPortal("earth_Altar_Exit_Portal", "earth", 0);
	public static BlockAltarPortal fireAltarPortal = new BlockAltarPortal("fire_Altar_Exit_Portal", "fire", 0);
	public static BlockAltarPortal lawAltarPortal = new BlockAltarPortal("law_Altar_Exit_Portal", "law", 0);
	public static BlockAltarPortal mindAltarPortal = new BlockAltarPortal("mind_Altar_Exit_Portal", "mind", 0);
	public static BlockAltarPortal natureAltarPortal = new BlockAltarPortal("nature_Altar_Exit_Portal", "nature", 0);
	public static BlockAltarPortal soulAltarPortal = new BlockAltarPortal("soul_Altar_Exit_Portal", "soul", 0);
	public static BlockAltarPortal waterAltarPortal = new BlockAltarPortal("water_Altar_Exit_Portal", "water", 0);
	
	public static BlockParticleLight particleLight = new BlockParticleLight("particle_Light", Material.BARRIER, 0f, 0f, "pickaxe", 0, true);
	

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
		blockList.add(runeEssence);
		blockList.add(runeEssenceFinite);
		blockList.add(ruinBlock);
		blockList.add(stationStone);
		blockList.add(templeBlock);
		blockList.add(templeBlockStairs);
		blockList.add(templeBlockRail);
		blockList.add(templeBlockRailGate);
		blockList.add(bloodBlock);
		blockList.add(bloodBlockStairs);
		blockList.add(bloodBlockRail);
		blockList.add(bloodBlockRailGate);
		blockList.add(fleshBlock);
		blockList.add(stonebrickRail);
		blockList.add(stonebrickBlockRailGate);
		
		halfSlabList.add(bloodBlockHalfSlab);
		doubleSlabList.add(bloodBlockDoubleSlab);
		
		halfSlabList.add(templeBlockHalfSlab);
		doubleSlabList.add(templeBlockDoubleSlab);

		blockList.add(airAltar);
		blockList.add(astralAltar);
		blockList.add(bloodAltar);
		blockList.add(bodyAltar);
		blockList.add(chaosAltar);
		blockList.add(cosmicAltar);
		blockList.add(deathAltar);
		blockList.add(earthAltar);
		blockList.add(fireAltar);
		blockList.add(lawAltar);
		blockList.add(mindAltar);
		blockList.add(natureAltar);
		blockList.add(soulAltar);
		blockList.add(waterAltar);
		blockList.add(ouraniaAltar);

		blockList.add(airAltarEntrance);
		blockList.add(bloodAltarEntrance);
		blockList.add(bodyAltarEntrance);
		blockList.add(chaosAltarEntrance);
		blockList.add(cosmicAltarEntrance);
		blockList.add(deathAltarEntrance);
		blockList.add(earthAltarEntrance);
		blockList.add(fireAltarEntrance);
		blockList.add(lawAltarEntrance);
		blockList.add(mindAltarEntrance);
		blockList.add(natureAltarEntrance);
		blockList.add(soulAltarEntrance);
		blockList.add(waterAltarEntrance);

		blockList.add(airAltarPortal);
		blockList.add(bloodAltarPortal);
		blockList.add(bodyAltarPortal);
		blockList.add(chaosAltarPortal);
		blockList.add(cosmicAltarPortal);
		blockList.add(deathAltarPortal);
		blockList.add(earthAltarPortal);
		blockList.add(fireAltarPortal);
		blockList.add(lawAltarPortal);
		blockList.add(mindAltarPortal);
		blockList.add(natureAltarPortal);
		blockList.add(soulAltarPortal);
		blockList.add(waterAltarPortal);
		
		blockList.add(particleLight);
	}

	public static void setupDimIDs() {
		airAltarEntrance.setDimID(ModConfig.dimensions.airTempleDimID);
		bloodAltarEntrance.setDimID(ModConfig.dimensions.bloodTempleDimID);
		bodyAltarEntrance.setDimID(ModConfig.dimensions.bodyTempleDimID);
		chaosAltarEntrance.setDimID(ModConfig.dimensions.chaosTempleDimID);
		cosmicAltarEntrance.setDimID(ModConfig.dimensions.cosmicTempleDimID);
		deathAltarEntrance.setDimID(ModConfig.dimensions.deathTempleDimID);
		earthAltarEntrance.setDimID(ModConfig.dimensions.earthTempleDimID);
		fireAltarEntrance.setDimID(ModConfig.dimensions.fireTempleDimID);
		lawAltarEntrance.setDimID(ModConfig.dimensions.lawTempleDimID);
		mindAltarEntrance.setDimID(ModConfig.dimensions.mindTempleDimID);
		natureAltarEntrance.setDimID(ModConfig.dimensions.natureTempleDimID);
		soulAltarEntrance.setDimID(ModConfig.dimensions.soulTempleDimID);
		waterAltarEntrance.setDimID(ModConfig.dimensions.waterTempleDimID);
	}
}
