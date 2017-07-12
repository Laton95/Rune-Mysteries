package com.laton95.runemysteries.init;

import java.util.ArrayList;

import com.laton95.runemysteries.item.ItemAltarSpawner;
import com.laton95.runemysteries.item.ItemRune;
import com.laton95.runemysteries.item.ItemSpellbook;
import com.laton95.runemysteries.item.ItemTalisman;
import com.laton95.runemysteries.item.RMModItem;
import com.laton95.runemysteries.utility.LogHelper;
import com.laton95.runemysteries.world.ChunkGenerator;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ItemRegistry {
	private static ArrayList<Item> itemList = new ArrayList<Item>();

	public static ItemRune airRune = new ItemRune("air_Rune");
	public static ItemRune astralRune = new ItemRune("astral_Rune");
	public static ItemRune bloodRune = new ItemRune("blood_Rune");
	public static ItemRune bodyRune = new ItemRune("body_Rune");
	public static ItemRune chaosRune = new ItemRune("chaos_Rune");
	public static ItemRune cosmicRune = new ItemRune("cosmic_Rune");
	public static ItemRune deathRune = new ItemRune("death_Rune");
	public static ItemRune earthRune = new ItemRune("earth_Rune");
	public static ItemRune fireRune = new ItemRune("fire_Rune");
	public static ItemRune lawRune = new ItemRune("law_Rune");
	public static ItemRune mindRune = new ItemRune("mind_Rune");
	public static ItemRune natureRune = new ItemRune("nature_Rune");
	public static ItemRune soulRune = new ItemRune("soul_Rune");
	public static ItemRune waterRune = new ItemRune("water_Rune");
	public static RMModItem runeEssence = new RMModItem("rune_Essence", true);
	public static ItemSpellbook spellbook = new ItemSpellbook();
	public static ItemAltarSpawner altarSpawner = new ItemAltarSpawner();
	public static ItemTalisman airTalisman = new ItemTalisman("air_talisman", "air_altar", ChunkGenerator.dimType.OVERWORLD);
	public static ItemTalisman astralTalisman = new ItemTalisman("astral_talisman", "astral_altar", ChunkGenerator.dimType.OVERWORLD);
	public static ItemTalisman bloodTalisman = new ItemTalisman("blood_talisman", "blood_altar", ChunkGenerator.dimType.OVERWORLD);
	public static ItemTalisman bodyTalisman = new ItemTalisman("body_talisman", "body_altar", ChunkGenerator.dimType.OVERWORLD);
	public static ItemTalisman chaosTalisman = new ItemTalisman("chaos_talisman", "chaos_altar", ChunkGenerator.dimType.OVERWORLD);
	public static ItemTalisman cosmicTalisman = new ItemTalisman("cosmic_talisman", "cosmic_altar", ChunkGenerator.dimType.OVERWORLD);
	public static ItemTalisman deathTalisman = new ItemTalisman("death_talisman", "death_altar", ChunkGenerator.dimType.OVERWORLD);
	public static ItemTalisman earthTalisman = new ItemTalisman("earth_talisman", "earth_altar", ChunkGenerator.dimType.OVERWORLD);
	public static ItemTalisman fireTalisman = new ItemTalisman("fire_talisman", "fire_altar", ChunkGenerator.dimType.OVERWORLD);
	public static ItemTalisman lawTalisman = new ItemTalisman("law_talisman", "law_altar", ChunkGenerator.dimType.OVERWORLD);
	public static ItemTalisman mindTalisman = new ItemTalisman("mind_talisman", "mind_altar", ChunkGenerator.dimType.OVERWORLD);
	public static ItemTalisman natureTalisman = new ItemTalisman("nature_talisman", "nature_altar", ChunkGenerator.dimType.OVERWORLD);
	public static ItemTalisman soulTalisman = new ItemTalisman("soul_talisman", "soul_altar", ChunkGenerator.dimType.OVERWORLD);
	public static ItemTalisman waterTalisman = new ItemTalisman("water_talisman", "water_altar", ChunkGenerator.dimType.OVERWORLD);

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		LogHelper.info("Registering items...");
		makeItemList();
		for (Item item : itemList) {
			event.getRegistry().register(item);
			ModelLoader.setCustomModelResourceLocation(item, 0,
					new ModelResourceLocation(item.getRegistryName(), "inventory"));
			LogHelper.info(item.getRegistryName() + " registered");
		}
		LogHelper.info("All items registered successfully");
	}

	private static void makeItemList() {
		itemList.add(airRune);
		itemList.add(astralRune);
		itemList.add(bloodRune);
		itemList.add(bodyRune);
		itemList.add(chaosRune);
		itemList.add(cosmicRune);
		itemList.add(deathRune);
		itemList.add(earthRune);
		itemList.add(fireRune);
		itemList.add(lawRune);
		itemList.add(mindRune);
		itemList.add(natureRune);
		itemList.add(soulRune);
		itemList.add(waterRune);
		itemList.add(runeEssence);
		
		itemList.add(airTalisman);
		itemList.add(astralTalisman);
		itemList.add(bloodTalisman);
		itemList.add(bodyTalisman);
		itemList.add(chaosTalisman);
		itemList.add(cosmicTalisman);
		itemList.add(deathTalisman);
		itemList.add(earthTalisman);
		itemList.add(fireTalisman);
		itemList.add(lawTalisman);
		itemList.add(mindTalisman);
		itemList.add(natureTalisman);
		itemList.add(soulTalisman);
		itemList.add(waterTalisman);
		
		itemList.add(spellbook);
		itemList.add(altarSpawner);
		
		
	}

	public static void addItemBlock(ItemBlock itemBlock) {
		itemList.add(itemBlock);
	}
}
