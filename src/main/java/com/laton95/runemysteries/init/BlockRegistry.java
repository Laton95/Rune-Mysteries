package com.laton95.runemysteries.init;

import java.util.ArrayList;

import com.laton95.runemysteries.block.BlockOuraniaAltar;
import com.laton95.runemysteries.block.BlockRuneAltar;
import com.laton95.runemysteries.block.BlockRuneEssence;
import com.laton95.runemysteries.block.RMModBlock;
import com.laton95.runemysteries.utility.LogHelper;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class BlockRegistry {
	private static ArrayList<Block> blockList = new ArrayList<Block>();

	// Blocks
	public static RMModBlock runeEssenceFinite = new RMModBlock("rune_Essence_Block_Finite", Material.ROCK, 1.5f, 10.0f,
			"pickaxe", 1, true, ItemRegistry.runeEssence);
	public static RMModBlock ruinBlock = new RMModBlock("ruin_Block", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	public static RMModBlock templeBlock = new RMModBlock("temple_Block", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1,
			true);

	// Tile Entities
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

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		LogHelper.info("Registering blocks...");
		makeBlockList();
		for (Block block : blockList) {
			ItemBlock itemBlock = new ItemBlock(block);
			itemBlock.setRegistryName(block.getRegistryName());
			ItemRegistry.addItemBlock(itemBlock);
			event.getRegistry().register(block);
			LogHelper.info(block.getRegistryName() + " registered");
		}
		LogHelper.info("All blocks registered successfully");
	}

	private static void makeBlockList() {
		blockList.add(runeEssence);
		blockList.add(runeEssenceFinite);
		blockList.add(ruinBlock);
		blockList.add(templeBlock);

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

	}
}
