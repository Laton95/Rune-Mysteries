package com.laton95.runemysteries.init;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.block.ModBlock;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.item.*;
import com.laton95.runemysteries.util.ModLog;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemWallOrFloor;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = RuneMysteries.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":rune_essence")
	public static ModItem RUNE_ESSENCE;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":air_rune")
	public static ItemRune AIR_RUNE;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":astral_rune")
	public static ItemRune ASTRAL_RUNE;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":blood_rune")
	public static ItemRune BLOOD_RUNE;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":body_rune")
	public static ItemRune BODY_RUNE;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":chaos_rune")
	public static ItemRune CHAOS_RUNE;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":cosmic_rune")
	public static ItemRune COSMIC_RUNE;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":death_rune")
	public static ItemRune DEATH_RUNE;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":earth_rune")
	public static ItemRune EARTH_RUNE;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":fire_rune")
	public static ItemRune FIRE_RUNE;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":law_rune")
	public static ItemRune LAW_RUNE;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":mind_rune")
	public static ItemRune MIND_RUNE;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":nature_rune")
	public static ItemRune NATURE_RUNE;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":soul_rune")
	public static ItemRune SOUL_RUNE;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":water_rune")
	public static ItemRune WATER_RUNE;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":blank_talisman")
	public static ModItem BLANK_TALISMAN;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":air_talisman")
	public static ItemTalisman AIR_TALISMAN;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":astral_talisman")
	public static ItemTalisman ASTRAL_TALISMAN;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":blood_talisman")
	public static ItemTalisman BLOOD_TALISMAN;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":body_talisman")
	public static ItemTalisman BODY_TALISMAN;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":chaos_talisman")
	public static ItemTalisman CHAOS_TALISMAN;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":cosmic_talisman")
	public static ItemTalisman COSMIC_TALISMAN;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":death_talisman")
	public static ItemTalisman DEATH_TALISMAN;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":earth_talisman")
	public static ItemTalisman EARTH_TALISMAN;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":fire_talisman")
	public static ItemTalisman FIRE_TALISMAN;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":law_talisman")
	public static ItemTalisman LAW_TALISMAN;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":mind_talisman")
	public static ItemTalisman MIND_TALISMAN;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":nature_talisman")
	public static ItemTalisman NATURE_TALISMAN;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":soul_talisman")
	public static ItemTalisman SOUL_TALISMAN;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":water_talisman")
	public static ItemTalisman WATER_TALISMAN;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":glass_orb")
	public static ModItem GLASS_ORB;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":air_orb")
	public static ModItem AIR_ORB;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":earth_orb")
	public static ModItem EARTH_ORB;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":fire_orb")
	public static ModItem FIRE_ORB;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":water_orb")
	public static ModItem WATER_ORB;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":battlestaff")
	public static ModItem BATTLESTAFF;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":air_battlestaff")
	public static ModItem AIR_BATTLESTAFF;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":earth_battlestaff")
	public static ModItem EARTH_BATTLESTAFF;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":fire_battlestaff")
	public static ModItem FIRE_BATTLESTAFF;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":water_battlestaff")
	public static ModItem WATER_BATTLESTAFF;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":air_shard")
	public static ModItem AIR_SHARD;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":earth_shard")
	public static ModItem EARTH_SHARD;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":fire_shard")
	public static ModItem FIRE_SHARD;
	
	@ObjectHolder(RuneMysteries.MOD_ID + ":water_shard")
	public static ModItem WATER_SHARD;
	
	@ObjectHolder(RuneMysteries.MOD_ID + "debug")
	public static ItemDebug DEBUG;
	
	@ObjectHolder(RuneMysteries.MOD_ID + "spellbook")
	public static ItemSpellbook SPELLBOOK;
	
	@ObjectHolder(RuneMysteries.MOD_ID + "rune_bag")
	public static ItemRuneBag RUNE_BAG;
	
	@ObjectHolder(RuneMysteries.MOD_ID + "ex_parrot")
	public static ModItem EX_PARROT;
	
	@ObjectHolder(RuneMysteries.MOD_ID + "mine_scroll")
	public static ItemScroll MINE_SCROLL;
	
	@ObjectHolder(RuneMysteries.MOD_ID + "banana")
	public static ModFood BANANA;
	
	private static ArrayList<Block> blocks = new ArrayList<>();
	
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) {
		ModLog.info("Registering items");
		
		register(event, new ModItem(), "rune_essence");
		register(event, new ItemRune(EnumRuneType.AIR), "air_rune");
		register(event, new ItemRune(EnumRuneType.ASTRAL), "astral_rune");
		register(event, new ItemRune(EnumRuneType.BLOOD), "blood_rune");
		register(event, new ItemRune(EnumRuneType.BODY), "body_rune");
		register(event, new ItemRune(EnumRuneType.CHAOS), "chaos_rune");
		register(event, new ItemRune(EnumRuneType.COSMIC), "cosmic_rune");
		register(event, new ItemRune(EnumRuneType.DEATH), "death_rune");
		register(event, new ItemRune(EnumRuneType.EARTH), "earth_rune");
		register(event, new ItemRune(EnumRuneType.FIRE), "fire_rune");
		register(event, new ItemRune(EnumRuneType.LAW), "law_rune");
		register(event, new ItemRune(EnumRuneType.MIND), "mind_rune");
		register(event, new ItemRune(EnumRuneType.NATURE), "nature_rune");
		register(event, new ItemRune(EnumRuneType.SOUL), "soul_rune");
		register(event, new ItemRune(EnumRuneType.WATER), "water_rune");
		
		register(event, new ModItem(new Properties().maxStackSize(1)), "blank_talisman");
		register(event, new ItemTalisman(EnumRuneType.AIR), "air_talisman");
		register(event, new ItemTalisman(EnumRuneType.ASTRAL), "astral_talisman");
		register(event, new ItemTalisman(EnumRuneType.BLOOD), "blood_talisman");
		register(event, new ItemTalisman(EnumRuneType.BODY), "body_talisman");
		register(event, new ItemTalisman(EnumRuneType.CHAOS), "chaos_talisman");
		register(event, new ItemTalisman(EnumRuneType.COSMIC), "cosmic_talisman");
		register(event, new ItemTalisman(EnumRuneType.DEATH), "death_talisman");
		register(event, new ItemTalisman(EnumRuneType.EARTH), "earth_talisman");
		register(event, new ItemTalisman(EnumRuneType.FIRE), "fire_talisman");
		register(event, new ItemTalisman(EnumRuneType.LAW), "law_talisman");
		register(event, new ItemTalisman(EnumRuneType.MIND), "mind_talisman");
		register(event, new ItemTalisman(EnumRuneType.NATURE), "nature_talisman");
		register(event, new ItemTalisman(EnumRuneType.SOUL), "soul_talisman");
		register(event, new ItemTalisman(EnumRuneType.WATER), "water_talisman");
		
		register(event, new ModItem(new Properties().maxStackSize(1)), "glass_orb");
		register(event, new ModItem(new Properties().maxStackSize(1)), "air_orb");
		register(event, new ModItem(new Properties().maxStackSize(1)), "earth_orb");
		register(event, new ModItem(new Properties().maxStackSize(1)), "fire_orb");
		register(event, new ModItem(new Properties().maxStackSize(1)), "water_orb");
		
		register(event, new ModItem(new Properties().maxStackSize(1)), "battlestaff");
		register(event, new ItemBattlestaff(EnumRuneType.AIR), "air_battlestaff");
		register(event, new ItemBattlestaff(EnumRuneType.EARTH), "earth_battlestaff");
		register(event, new ItemBattlestaff(EnumRuneType.FIRE), "fire_battlestaff");
		register(event, new ItemBattlestaff(EnumRuneType.WATER), "water_battlestaff");
		
		register(event, new ModItem(), "air_shard");
		register(event, new ModItem(), "earth_shard");
		register(event, new ModItem(), "fire_shard");
		register(event, new ModItem(), "water_shard");
		
		register(event, new ItemDebug(), "debug");
		register(event, new ItemSpellbook(), "spellbook");
		register(event, new ItemRuneBag(), "rune_bag");
		register(event, new ModItem(), "ex_parrot");
		register(event, new ItemScroll(1), "mine_scroll");
		register(event, new ModFood(4, 4, false), "banana");
		register(event, new ItemWallOrFloor(ModBlocks.UNDERWATER_TORCH, ModBlocks.UNDERWATER_WALL_TORCH, new Item.Properties().group(RuneMysteries.RUNE_GROUP)), "underwater_torch");
		
		for(Block block : blocks) {
			Properties builder = new Properties();
			
			if(block instanceof ModBlock && ((ModBlock) block).showInCreative) {
				builder.group(RuneMysteries.RUNE_GROUP);
			}
			else {
				builder.group(RuneMysteries.RUNE_GROUP);
			}
			
			ItemBlock itemBlock = new ItemBlock(block, builder);
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