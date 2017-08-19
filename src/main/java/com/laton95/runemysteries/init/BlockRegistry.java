package com.laton95.runemysteries.init;

import java.util.ArrayList;

import com.laton95.runemysteries.block.BlockAltarPortal;
import com.laton95.runemysteries.block.BlockParticleLight;
import com.laton95.runemysteries.block.BlockRuneAltar;
import com.laton95.runemysteries.block.BlockRuneAltarEntrance;
import com.laton95.runemysteries.block.BlockRuneAltar;
import com.laton95.runemysteries.block.BlockRuneEssence;
import com.laton95.runemysteries.block.BlockStationStone;
import com.laton95.runemysteries.block.IMetaBlock;
import com.laton95.runemysteries.block.RMModBlock;
import com.laton95.runemysteries.block.RMModRail;
import com.laton95.runemysteries.block.RMModSlab;
import com.laton95.runemysteries.block.RMModSlab.Half;
import com.laton95.runemysteries.block.RMModStairs;
import com.laton95.runemysteries.item.MetaItemBlock;
import com.laton95.runemysteries.item.ItemRune.EnumRuneType;
import com.laton95.runemysteries.util.LogHelper;
import com.laton95.runemysteries.util.ModConfig;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class BlockRegistry
{

	private static ArrayList<Block> blockList = new ArrayList<>();

	public static final BlockRuneAltar RUNE_ALTAR = new BlockRuneAltar();
	public static final BlockRuneAltarEntrance RUNE_ALTAR_ENTRANCE = new BlockRuneAltarEntrance();
	public static final BlockAltarPortal ALTAR_PORTAL = new BlockAltarPortal("altar_Exit_Portal");
	
	public static final BlockRuneEssence RUNE_ESSENCE = new BlockRuneEssence();
	public static final RMModBlock RUNE_ESSENCE_FINITE = new RMModBlock("rune_Essence_Block_Finite", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true, new ItemStack(ItemRegistry.RUNE, 1, EnumRuneType.ESSENCE.ordinal()));
	
	public static final RMModBlock RUIN_BLOCK = new RMModBlock("ruin_Block", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	public static final BlockStationStone STATION_STONE = new BlockStationStone();
	
	public static final RMModBlock TEMPLE_BLOCK = new RMModBlock("temple_Block", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	public static final RMModStairs TEMPLE_BLOCK_STAIRS = new RMModStairs("temple_Block_Stairs", "pickaxe", 1, true, TEMPLE_BLOCK.getDefaultState());
	public static final RMModSlab.Half TEMPLE_BLOCK_SLAB = new RMModSlab.Half("temple_Block_Slab", Material.ROCK, .5f, 10.0f, "pickaxe", 1, true);
	public static final RMModRail TEMPLE_BLOCK_RAIL = new RMModRail("temple_Block_Rail", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	
	public static final RMModBlock BLOOD_BLOCK = new RMModBlock("blood_Block", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	public static final RMModStairs BLOOD_BLOCK_STAIRS = new RMModStairs("blood_Block_Stairs", "pickaxe", 1, true, BLOOD_BLOCK.getDefaultState());
	public static final RMModSlab.Half BLOOD_BLOCK_SLAB = new RMModSlab.Half("blood_Block_Slab", Material.ROCK, .5f, 10.0f, "pickaxe", 1, true);
	public static final RMModRail BLOOD_BLOCK_RAIL = new RMModRail("blood_Block_Rail", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	
	public static final RMModBlock FLESH_BLOCK = new RMModBlock("flesh_Block", Material.CAKE, 1.5f, 10.0f, "pickaxe", 1, true);
	
	public static final RMModRail STONEBRICK_RAIL = new RMModRail("stonebrick_Rail", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	
	public static final BlockParticleLight PARTICLE_LIGHT = new BlockParticleLight("particle_Light", Material.BARRIER, 0f, 0f, "pickaxe", 0, true);

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		LogHelper.info("Registering blocks");
		makeBlockList();
		for (Block block : blockList)
		{
			if (block instanceof IMetaBlock)
			{
				ItemBlock itemBlock = new MetaItemBlock(block);
				itemBlock.setRegistryName(block.getRegistryName());
				ItemRegistry.addItemBlock(itemBlock);
				event.getRegistry().register(block);
			} 
			else if (block instanceof RMModSlab)
			{
				RMModSlab.Half slab = (Half) block;
				RMModSlab.Double doubleSlab = slab.doubleSlab;
				ItemBlock itemBlock = new ItemSlab(slab, slab, doubleSlab);
				itemBlock.setRegistryName(slab.getRegistryName());
				ItemRegistry.addItemBlock(itemBlock);
				slab.setDroppedItem(itemBlock);
				doubleSlab.setDroppedItem(itemBlock);
				event.getRegistry().register(slab);
				event.getRegistry().register(doubleSlab);
			}
			else
			{
				ItemBlock itemBlock = new ItemBlock(block);
				itemBlock.setRegistryName(block.getRegistryName());
				ItemRegistry.addItemBlock(itemBlock);
				event.getRegistry().register(block);
				
			}
		}
	}

	private static void makeBlockList()
	{
		blockList.add(RUNE_ALTAR);
		blockList.add(RUNE_ALTAR_ENTRANCE);
		blockList.add(ALTAR_PORTAL);
		
		blockList.add(RUNE_ESSENCE);
		blockList.add(RUNE_ESSENCE_FINITE);
		
		blockList.add(RUIN_BLOCK);
		blockList.add(STATION_STONE);
		
		blockList.add(TEMPLE_BLOCK);
		blockList.add(TEMPLE_BLOCK_STAIRS);
		blockList.add(TEMPLE_BLOCK_SLAB);
		blockList.add(TEMPLE_BLOCK_RAIL);
		
		blockList.add(BLOOD_BLOCK);
		blockList.add(BLOOD_BLOCK_STAIRS);
		blockList.add(BLOOD_BLOCK_SLAB);
		blockList.add(BLOOD_BLOCK_RAIL);
		
		blockList.add(FLESH_BLOCK);
		
		blockList.add(STONEBRICK_RAIL);

		blockList.add(PARTICLE_LIGHT);
	}
}
