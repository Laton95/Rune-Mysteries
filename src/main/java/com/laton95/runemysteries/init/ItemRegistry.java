package com.laton95.runemysteries.init;

import java.util.ArrayList;

import com.laton95.runemysteries.item.ItemRune;
import com.laton95.runemysteries.item.ItemSpellbook;
import com.laton95.runemysteries.item.ItemTalisman;
import com.laton95.runemysteries.item.RMModItem;
import com.laton95.runemysteries.util.LogHelper;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ItemRegistry {
	private static ArrayList<Item> itemList = new ArrayList<>();

	public static final ItemRune AIR_RUNE = new ItemRune("air_Rune");
	public static final ItemRune ASTRAL_RUNE = new ItemRune("astral_Rune");
	public static final ItemRune BLOOD_RUNE = new ItemRune("blood_Rune");
	public static final ItemRune BODY_RUNE = new ItemRune("body_Rune");
	public static final ItemRune CHAOS_RUNE = new ItemRune("chaos_Rune");
	public static final ItemRune COSMIC_RUNE = new ItemRune("cosmic_Rune");
	public static final ItemRune DEATH_RUNE = new ItemRune("death_Rune");
	public static final ItemRune EARTH_RUNE = new ItemRune("earth_Rune");
	public static final ItemRune FIRE_RUNE = new ItemRune("fire_Rune");
	public static final ItemRune LAW_RUNE = new ItemRune("law_Rune");
	public static final ItemRune MIND_RUNE = new ItemRune("mind_Rune");
	public static final ItemRune NATURE_RUNE = new ItemRune("nature_Rune");
	public static final ItemRune SOUL_RUNE = new ItemRune("soul_Rune");
	public static final ItemRune WATER_RUNE = new ItemRune("water_Rune");
	public static final RMModItem RUNE_ESSENCE = new RMModItem("rune_Essence", true);
	
	public static final ItemTalisman AIR_TALISMAN = new ItemTalisman("air_talisman", "air_altar", 0);
	public static final ItemTalisman ASTRAL_TALISMAN = new ItemTalisman("astral_talisman", "astral_altar", 0);
	public static final ItemTalisman BLOOD_TALISMAN = new ItemTalisman("blood_talisman", "blood_altar", 0);
	public static final ItemTalisman BODY_TALISMAN = new ItemTalisman("body_talisman", "body_altar", 0);
	public static final ItemTalisman CHAOS_TALISMAN = new ItemTalisman("chaos_talisman", "chaos_altar", -1);
	public static final ItemTalisman COSMIC_TALISMAN = new ItemTalisman("cosmic_talisman", "cosmic_altar", 1);
	public static final ItemTalisman DEATH_TALISMAN = new ItemTalisman("death_talisman", "death_altar", 0);
	public static final ItemTalisman EARTH_TALISMAN = new ItemTalisman("earth_talisman", "earth_altar", 0);
	public static final ItemTalisman FIRE_TALISMAN = new ItemTalisman("fire_talisman", "fire_altar", 0);
	public static final ItemTalisman LAW_TALISMAN = new ItemTalisman("law_talisman", "law_altar", 0);
	public static final ItemTalisman MIND_TALISMAN = new ItemTalisman("mind_talisman", "mind_altar", 0);
	public static final ItemTalisman NATURE_TALISMAN = new ItemTalisman("nature_talisman", "nature_altar", 0);
	public static final ItemTalisman SOUL_TALISMAN = new ItemTalisman("soul_talisman", "soul_altar", 0);
	public static final ItemTalisman WATER_TALISMAN = new ItemTalisman("water_talisman", "water_altar", 0);
	
	public static final ItemSpellbook SPELLBOOK = new ItemSpellbook();

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		LogHelper.info("Registering items");
		makeItemList();
		for (Item item : itemList) {
			event.getRegistry().register(item);
			ModelLoader.setCustomModelResourceLocation(item, 0,
					new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}
	}

	private static void makeItemList() {
		itemList.add(AIR_RUNE);
		itemList.add(ASTRAL_RUNE);
		itemList.add(BLOOD_RUNE);
		itemList.add(BODY_RUNE);
		itemList.add(CHAOS_RUNE);
		itemList.add(COSMIC_RUNE);
		itemList.add(DEATH_RUNE);
		itemList.add(EARTH_RUNE);
		itemList.add(FIRE_RUNE);
		itemList.add(LAW_RUNE);
		itemList.add(MIND_RUNE);
		itemList.add(NATURE_RUNE);
		itemList.add(SOUL_RUNE);
		itemList.add(WATER_RUNE);
		itemList.add(RUNE_ESSENCE);

		itemList.add(AIR_TALISMAN);
		itemList.add(ASTRAL_TALISMAN);
		itemList.add(BLOOD_TALISMAN);
		itemList.add(BODY_TALISMAN);
		itemList.add(CHAOS_TALISMAN);
		itemList.add(COSMIC_TALISMAN);
		itemList.add(DEATH_TALISMAN);
		itemList.add(EARTH_TALISMAN);
		itemList.add(FIRE_TALISMAN);
		itemList.add(LAW_TALISMAN);
		itemList.add(MIND_TALISMAN);
		itemList.add(NATURE_TALISMAN);
		itemList.add(SOUL_TALISMAN);
		itemList.add(WATER_TALISMAN);

		itemList.add(SPELLBOOK);

	}

	public static void addItemBlock(ItemBlock itemBlock) {
		itemList.add(itemBlock);
	}
}
