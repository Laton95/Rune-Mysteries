package com.laton95.runemysteries.init;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.item.*;
import com.laton95.runemysteries.util.ModLog;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = RuneMysteries.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(RuneMysteries.MOD_ID)
public class ModItems {
	
	public static final Item RUNE_ESSENCE = null;
	
	public static final RuneItem AIR_RUNE = null;
	
	public static final RuneItem ASTRAL_RUNE = null;
	
	public static final RuneItem BLOOD_RUNE = null;
	
	public static final RuneItem BODY_RUNE = null;
	
	public static final RuneItem CHAOS_RUNE = null;
	
	public static final RuneItem COSMIC_RUNE = null;
	
	public static final RuneItem DEATH_RUNE = null;
	
	public static final RuneItem EARTH_RUNE = null;
	
	public static final RuneItem FIRE_RUNE = null;
	
	public static final RuneItem LAW_RUNE = null;
	
	public static final RuneItem MIND_RUNE = null;
	
	public static final RuneItem NATURE_RUNE = null;
	
	public static final RuneItem SOUL_RUNE = null;
	
	public static final RuneItem WATER_RUNE = null;
	
	public static final Item BLANK_TALISMAN = null;
	
	public static final TalismanItem AIR_TALISMAN = null;
	
	public static final TalismanItem ASTRAL_TALISMAN = null;
	
	public static final TalismanItem BLOOD_TALISMAN = null;
	
	public static final TalismanItem BODY_TALISMAN = null;
	
	public static final TalismanItem CHAOS_TALISMAN = null;
	
	public static final TalismanItem COSMIC_TALISMAN = null;
	
	public static final TalismanItem DEATH_TALISMAN = null;
	
	public static final TalismanItem EARTH_TALISMAN = null;
	
	public static final TalismanItem FIRE_TALISMAN = null;
	
	public static final TalismanItem LAW_TALISMAN = null;
	
	public static final TalismanItem MIND_TALISMAN = null;
	
	public static final TalismanItem NATURE_TALISMAN = null;
	
	public static final TalismanItem SOUL_TALISMAN = null;
	
	public static final TalismanItem WATER_TALISMAN = null;
	
	public static final Item GLASS_ORB = null;
	
	public static final Item AIR_ORB = null;
	
	public static final Item EARTH_ORB = null;
	
	public static final Item FIRE_ORB = null;
	
	public static final Item WATER_ORB = null;
	
	public static final Item BATTLESTAFF = null;
	
	public static final Item AIR_BATTLESTAFF = null;
	
	public static final Item EARTH_BATTLESTAFF = null;
	
	public static final Item FIRE_BATTLESTAFF = null;
	
	public static final Item WATER_BATTLESTAFF = null;
	
	public static final Item AIR_SHARD = null;
	
	public static final Item EARTH_SHARD = null;
	
	public static final Item FIRE_SHARD = null;
	
	public static final Item WATER_SHARD = null;
	
	public static final DebugItem DEBUG = null;
	
	public static final SpellbookItem SPELLBOOK = null;
	
	public static final RuneBagItem RUNE_BAG = null;
	
	public static final Item EX_PARROT = null;
	
	public static final ScrollItem MINE_SCROLL = null;
	
	public static final Item BANANA = null;
	
	private static final ArrayList<Block> blocks = new ArrayList<>();
	
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) {
		ModLog.info("Registering items");
		
		register(event, new Item(new Properties().group(RuneMysteries.RUNE_GROUP)), "rune_essence");
		register(event, new RuneItem(EnumRuneType.AIR), "air_rune");
		register(event, new RuneItem(EnumRuneType.ASTRAL), "astral_rune");
		register(event, new RuneItem(EnumRuneType.BLOOD), "blood_rune");
		register(event, new RuneItem(EnumRuneType.BODY), "body_rune");
		register(event, new RuneItem(EnumRuneType.CHAOS), "chaos_rune");
		register(event, new RuneItem(EnumRuneType.COSMIC), "cosmic_rune");
		register(event, new RuneItem(EnumRuneType.DEATH), "death_rune");
		register(event, new RuneItem(EnumRuneType.EARTH), "earth_rune");
		register(event, new RuneItem(EnumRuneType.FIRE), "fire_rune");
		register(event, new RuneItem(EnumRuneType.LAW), "law_rune");
		register(event, new RuneItem(EnumRuneType.MIND), "mind_rune");
		register(event, new RuneItem(EnumRuneType.NATURE), "nature_rune");
		register(event, new RuneItem(EnumRuneType.SOUL), "soul_rune");
		register(event, new RuneItem(EnumRuneType.WATER), "water_rune");
		
		register(event, new Item(new Properties().group(RuneMysteries.RUNE_GROUP).maxStackSize(1)), "blank_talisman");
		register(event, new TalismanItem(EnumRuneType.AIR), "air_talisman");
		register(event, new TalismanItem(EnumRuneType.ASTRAL), "astral_talisman");
		register(event, new TalismanItem(EnumRuneType.BLOOD), "blood_talisman");
		register(event, new TalismanItem(EnumRuneType.BODY), "body_talisman");
		register(event, new TalismanItem(EnumRuneType.CHAOS), "chaos_talisman");
		register(event, new TalismanItem(EnumRuneType.COSMIC), "cosmic_talisman");
		register(event, new TalismanItem(EnumRuneType.DEATH), "death_talisman");
		register(event, new TalismanItem(EnumRuneType.EARTH), "earth_talisman");
		register(event, new TalismanItem(EnumRuneType.FIRE), "fire_talisman");
		register(event, new TalismanItem(EnumRuneType.LAW), "law_talisman");
		register(event, new TalismanItem(EnumRuneType.MIND), "mind_talisman");
		register(event, new TalismanItem(EnumRuneType.NATURE), "nature_talisman");
		register(event, new TalismanItem(EnumRuneType.SOUL), "soul_talisman");
		register(event, new TalismanItem(EnumRuneType.WATER), "water_talisman");
		
		register(event, new Item(new Properties().group(RuneMysteries.RUNE_GROUP).maxStackSize(1)), "glass_orb");
		register(event, new Item(new Properties().group(RuneMysteries.RUNE_GROUP).maxStackSize(1)), "air_orb");
		register(event, new Item(new Properties().group(RuneMysteries.RUNE_GROUP).maxStackSize(1)), "earth_orb");
		register(event, new Item(new Properties().group(RuneMysteries.RUNE_GROUP).maxStackSize(1)), "fire_orb");
		register(event, new Item(new Properties().group(RuneMysteries.RUNE_GROUP).maxStackSize(1)), "water_orb");
		
		register(event, new Item(new Properties().group(RuneMysteries.RUNE_GROUP).maxStackSize(1)), "battlestaff");
		register(event, new BattlestaffItem(EnumRuneType.AIR), "air_battlestaff");
		register(event, new BattlestaffItem(EnumRuneType.EARTH), "earth_battlestaff");
		register(event, new BattlestaffItem(EnumRuneType.FIRE), "fire_battlestaff");
		register(event, new BattlestaffItem(EnumRuneType.WATER), "water_battlestaff");
		
		register(event, new Item(new Properties().group(RuneMysteries.RUNE_GROUP)), "air_shard");
		register(event, new Item(new Properties().group(RuneMysteries.RUNE_GROUP)), "earth_shard");
		register(event, new Item(new Properties().group(RuneMysteries.RUNE_GROUP)), "fire_shard");
		register(event, new Item(new Properties().group(RuneMysteries.RUNE_GROUP)), "water_shard");
		
		register(event, new DebugItem(), "debug");
		register(event, new SpellbookItem(), "spellbook");
		register(event, new RuneBagItem(), "rune_bag");
		register(event, new Item(new Properties().group(RuneMysteries.RUNE_GROUP)), "ex_parrot");
		register(event, new ScrollItem(1), "mine_scroll");
		register(event, new Item(new Properties().group(ItemGroup.FOOD).food(new Food.Builder().hunger(4).saturation(4).build())), "banana");
		
		for(Block block : blocks) {
			Properties builder = new Properties();
			builder.group(RuneMysteries.RUNE_GROUP);
			
			BlockItem itemBlock = new BlockItem(block, builder);
			itemBlock.setRegistryName(block.getRegistryName());
			event.getRegistry().register(itemBlock);
		}
	}
	
	public static void addBlock(Block block) {
		blocks.add(block);
	}
	
	private static void register(RegistryEvent.Register<Item> event, Item item, String name) {
		item.setRegistryName(RuneMysteries.MOD_ID, name);
		event.getRegistry().register(item);
	}
}