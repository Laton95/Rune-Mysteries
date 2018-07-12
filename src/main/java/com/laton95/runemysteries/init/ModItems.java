package com.laton95.runemysteries.init;

import com.laton95.runemysteries.block.RMModSlab;
import com.laton95.runemysteries.block.RMRandomRotationBlock;
import com.laton95.runemysteries.config.ModConfig;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.item.*;
import com.laton95.runemysteries.util.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;

@Mod.EventBusSubscriber
public class ModItems
{
	
	public static final RMModItem RUNE_ESSENCE = new RMModItem("rune_essence", true);
	
	public static final ItemRune AIR_RUNE = new ItemRune(EnumRuneType.AIR);
	
	public static final ItemRune ASTRAL_RUNE = new ItemRune(EnumRuneType.ASTRAL);
	
	public static final ItemRune BLOOD_RUNE = new ItemRune(EnumRuneType.BLOOD);
	
	public static final ItemRune BODY_RUNE = new ItemRune(EnumRuneType.BODY);
	
	public static final ItemRune CHAOS_RUNE = new ItemRune(EnumRuneType.CHAOS);
	
	public static final ItemRune COSMIC_RUNE = new ItemRune(EnumRuneType.COSMIC);
	
	public static final ItemRune DEATH_RUNE = new ItemRune(EnumRuneType.DEATH);
	
	public static final ItemRune EARTH_RUNE = new ItemRune(EnumRuneType.EARTH);
	
	public static final ItemRune FIRE_RUNE = new ItemRune(EnumRuneType.FIRE);
	
	public static final ItemRune LAW_RUNE = new ItemRune(EnumRuneType.LAW);
	
	public static final ItemRune MIND_RUNE = new ItemRune(EnumRuneType.MIND);
	
	public static final ItemRune NATURE_RUNE = new ItemRune(EnumRuneType.NATURE);
	
	public static final ItemRune SOUL_RUNE = new ItemRune(EnumRuneType.SOUL);
	
	public static final ItemRune WATER_RUNE = new ItemRune(EnumRuneType.WATER);
	
	public static final RMModItem BLANK_TALISMAN = new RMModItem("blank_talisman", true, 1);
	
	public static final ItemTalisman AIR_TALISMAN = new ItemTalisman(EnumRuneType.AIR);
	
	public static final ItemTalisman ASTRAL_TALISMAN = new ItemTalisman(EnumRuneType.ASTRAL);
	
	public static final ItemTalisman BLOOD_TALISMAN = new ItemTalisman(EnumRuneType.BLOOD);
	
	public static final ItemTalisman BODY_TALISMAN = new ItemTalisman(EnumRuneType.BODY);
	
	public static final ItemTalisman CHAOS_TALISMAN = new ItemTalisman(EnumRuneType.CHAOS);
	
	public static final ItemTalisman COSMIC_TALISMAN = new ItemTalisman(EnumRuneType.COSMIC);
	
	public static final ItemTalisman DEATH_TALISMAN = new ItemTalisman(EnumRuneType.DEATH);
	
	public static final ItemTalisman EARTH_TALISMAN = new ItemTalisman(EnumRuneType.EARTH);
	
	public static final ItemTalisman FIRE_TALISMAN = new ItemTalisman(EnumRuneType.FIRE);
	
	public static final ItemTalisman LAW_TALISMAN = new ItemTalisman(EnumRuneType.LAW);
	
	public static final ItemTalisman MIND_TALISMAN = new ItemTalisman(EnumRuneType.MIND);
	
	public static final ItemTalisman NATURE_TALISMAN = new ItemTalisman(EnumRuneType.NATURE);
	
	public static final ItemTalisman SOUL_TALISMAN = new ItemTalisman(EnumRuneType.SOUL);
	
	public static final ItemTalisman WATER_TALISMAN = new ItemTalisman(EnumRuneType.WATER);
	
	public static final ItemSpellbook SPELLBOOK = new ItemSpellbook();
	
	public static final ItemRuneBag RUNE_BAG = new ItemRuneBag();
	
	public static final ItemScroll MINE_SCROLL = new ItemScroll("mine_scroll", ModConfig.DIMENSIONS.essenceMineDimID);
	
	public static final RMModFood BANANA = new RMModFood("banana", true, 4, 4);
	
	public static final RMModItem EX_PARROT = new RMModItem("ex_parrot", true);
	
	public static final RMModItem AIR_OBELISK_SHARD = new RMModItem("air_obelisk_shard", true);
	
	public static final RMModItem EARTH_OBELISK_SHARD = new RMModItem("earth_obelisk_shard", true);
	
	public static final RMModItem FIRE_OBELISK_SHARD = new RMModItem("fire_obelisk_shard", true);
	
	public static final RMModItem WATER_OBELISK_SHARD = new RMModItem("water_obelisk_shard", true);
	
	public static final RMModItem GLASS_ORB = new RMModItem("glass_orb", true, 1);
	
	public static final RMModItem AIR_ORB = new RMModItem("air_orb", true, 1);
	
	public static final RMModItem EARTH_ORB = new RMModItem("earth_orb", true, 1);
	
	public static final RMModItem FIRE_ORB = new RMModItem("fire_orb", true, 1);
	
	public static final RMModItem WATER_ORB = new RMModItem("water_orb", true, 1);
	
	public static final RMModItem BATTLESTAFF = new RMModItem("battlestaff", true, 1);
	
	public static final ItemChargedBattlestaff AIR_BATTLESTAFF = new ItemChargedBattlestaff("air_battlestaff", true, EnumRuneType.AIR);
	
	public static final ItemChargedBattlestaff EARTH_BATTLESTAFF = new ItemChargedBattlestaff("earth_battlestaff", true, EnumRuneType.EARTH);
	
	public static final ItemChargedBattlestaff FIRE_BATTLESTAFF = new ItemChargedBattlestaff("fire_battlestaff", true, EnumRuneType.FIRE);
	
	public static final ItemChargedBattlestaff WATER_BATTLESTAFF = new ItemChargedBattlestaff("water_battlestaff", true, EnumRuneType.WATER);
	
	public static final ItemDebug DEBUG = new ItemDebug();
	
	private static Item[] items = new Item[]
			{
					RUNE_ESSENCE,
					AIR_RUNE,
					ASTRAL_RUNE,
					BLOOD_RUNE,
					BODY_RUNE,
					CHAOS_RUNE,
					COSMIC_RUNE,
					DEATH_RUNE,
					EARTH_RUNE,
					FIRE_RUNE,
					LAW_RUNE,
					MIND_RUNE,
					NATURE_RUNE,
					SOUL_RUNE,
					WATER_RUNE,
					
					BLANK_TALISMAN,
					AIR_TALISMAN,
					ASTRAL_TALISMAN,
					BLOOD_TALISMAN,
					BODY_TALISMAN,
					CHAOS_TALISMAN,
					COSMIC_TALISMAN,
					DEATH_TALISMAN,
					EARTH_TALISMAN,
					FIRE_TALISMAN,
					LAW_TALISMAN,
					MIND_TALISMAN,
					NATURE_TALISMAN,
					SOUL_TALISMAN,
					WATER_TALISMAN,
					
					
					SPELLBOOK,
					RUNE_BAG,
					MINE_SCROLL,
					BANANA,
					EX_PARROT,
					
					AIR_OBELISK_SHARD,
					EARTH_OBELISK_SHARD,
					FIRE_OBELISK_SHARD,
					WATER_OBELISK_SHARD,
					
					GLASS_ORB,
					AIR_ORB,
					EARTH_ORB,
					FIRE_ORB,
					WATER_ORB,
					
					BATTLESTAFF,
					AIR_BATTLESTAFF,
					EARTH_BATTLESTAFF,
					FIRE_BATTLESTAFF,
					WATER_BATTLESTAFF,
					
					DEBUG
			};
	
	private static ArrayList<Block> itemBlocks = new ArrayList<>();
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		LogHelper.info("Registering items");
		for(Item item : items)
		{
			event.getRegistry().register(item);
			registerItemRender(item);
		}
		
		for(Block block : itemBlocks)
		{
			if(block instanceof RMModSlab)
			{
				RMModSlab.Half slab = (RMModSlab.Half) block;
				RMModSlab.Double doubleSlab = slab.doubleSlab;
				ItemBlock itemBlock = new ItemSlab(slab, slab, doubleSlab);
				itemBlock.setRegistryName(slab.getRegistryName());
				slab.setDroppedItem(itemBlock);
				doubleSlab.setDroppedItem(itemBlock);
				event.getRegistry().register(itemBlock);
				registerBlockRender(itemBlock, "normal");
			}
			else if(block instanceof RMRandomRotationBlock)
			{
				ItemBlock itemBlock = new ItemBlock(block);
				itemBlock.setRegistryName(block.getRegistryName());
				event.getRegistry().register(itemBlock);
				registerBlockRender(itemBlock, "inventory");
			}
			else
			{
				ItemBlock itemBlock = new ItemBlock(block);
				itemBlock.setRegistryName(block.getRegistryName());
				event.getRegistry().register(itemBlock);
				registerBlockRender(itemBlock, "normal");
			}
		}
	}
	
	private static void registerItemRender(Item item)
	{
		final ModelResourceLocation fullModelLocation = new ModelResourceLocation(item.getRegistryName(), "inventory");
		ModelBakery.registerItemVariants(item, fullModelLocation);
		ModelLoader.setCustomMeshDefinition(item, stack -> fullModelLocation);
	}
	
	private static void registerBlockRender(ItemBlock block, String variant)
	{
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation(block.getRegistryName(), variant));
	}
	
	public static void addBlock(Block block)
	{
		itemBlocks.add(block);
	}
}
