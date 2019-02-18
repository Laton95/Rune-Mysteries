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
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = RuneMysteries.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {
	
	public static final ModItem RUNE_ESSENCE = new ModItem("rune_essence");
	
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
	
	public static final ModItem BLANK_TALISMAN = new ModItem("blank_talisman", new Properties().maxStackSize(1));
	
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
	
	public static final ModItem GLASS_ORB = new ModItem("glass_orb", new Properties().maxStackSize(1));
	
	public static final ModItem AIR_ORB = new ModItem("air_orb", new Properties().maxStackSize(1));
	
	public static final ModItem EARTH_ORB = new ModItem("earth_orb", new Properties().maxStackSize(1));
	
	public static final ModItem FIRE_ORB = new ModItem("fire_orb", new Properties().maxStackSize(1));
	
	public static final ModItem WATER_ORB = new ModItem("water_orb", new Properties().maxStackSize(1));
	
	public static final ModItem BATTLESTAFF = new ModItem("battlestaff", new Properties().maxStackSize(1));
	
	public static final ItemBattlestaff AIR_BATTLESTAFF = new ItemBattlestaff(EnumRuneType.AIR);
	
	public static final ItemBattlestaff EARTH_BATTLESTAFF = new ItemBattlestaff(EnumRuneType.EARTH);
	
	public static final ItemBattlestaff FIRE_BATTLESTAFF = new ItemBattlestaff(EnumRuneType.FIRE);
	
	public static final ItemBattlestaff WATER_BATTLESTAFF = new ItemBattlestaff(EnumRuneType.WATER);
	
	public static final ModItem AIR_SHARD = new ModItem("air_shard");
	
	public static final ModItem EARTH_SHARD = new ModItem("earth_shard");
	
	public static final ModItem FIRE_SHARD = new ModItem("fire_shard");
	
	public static final ModItem WATER_SHARD = new ModItem("water_shard");
	
	public static final ItemDebug DEBUG = new ItemDebug();
	
	public static final ItemSpellbook SPELLBOOK = new ItemSpellbook();
	
	public static final ItemRuneBag RUNE_BAG = new ItemRuneBag();
	
	public static final ModItem EX_PARROT = new ModItem("ex_parrot");
	
	public static final ItemScroll MINE_SCROLL = new ItemScroll("mine_scroll", 1);
	
	public static final ModFood BANANA = new ModFood("banana", 4, 4, false);
	
	private static Item[] items = {
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
			AIR_SHARD,
			EARTH_SHARD,
			FIRE_SHARD,
			WATER_SHARD,
			DEBUG,
			SPELLBOOK,
			RUNE_BAG,
			EX_PARROT,
			MINE_SCROLL,
			BANANA
	};
	
	private static ArrayList<Block> blocks = new ArrayList<>();
	
	private static ArrayList<ItemBlock> extraItems = new ArrayList<>();
	
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) {
		ModLog.info("Registering items");
		
		
		for(Block block : blocks) {
			Properties builder = new Properties();
			
			if(block instanceof ModBlock) {
				if(((ModBlock) block).hasItem) {
					builder.group(RuneMysteries.RUNE_GROUP);
				}
			}
			else {
				builder.group(RuneMysteries.RUNE_GROUP);
			}
			
			ItemBlock itemBlock = new ItemBlock(block, builder);
			itemBlock.setRegistryName(block.getRegistryName());
			event.getRegistry().register(itemBlock);
		}
		
		for(ItemBlock item : extraItems) {
			item.setRegistryName(item.getBlock().getRegistryName());
			event.getRegistry().register(item);
		}
		
		for(Item item : items) {
			event.getRegistry().register(item);
		}
	}
	
	public static void addBlock(Block block) {
		blocks.add(block);
	}
	
	public static void addExtraItemBlock(ItemBlock item) {
		extraItems.add(item);
	}
}