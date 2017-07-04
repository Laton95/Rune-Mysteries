package com.laton95.test.init;

import java.util.ArrayList;

import com.laton95.test.item.ItemRune;
import com.laton95.test.item.ItemSpellbook;
import com.laton95.test.item.ItemTest;
import com.laton95.test.utility.LogHelper;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
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
	public static ItemTest runeEssence = new ItemTest("rune_Essence",true);
	public static ItemSpellbook spellbook = new ItemSpellbook();
	
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event){		
		LogHelper.info("Registering items...");
		makeItemList();
		for(Item item : itemList){
			event.getRegistry().register(item);
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		    LogHelper.info(item.getRegistryName() + " registered");
		}
	    LogHelper.info("All items registered successfully");
	}
	
	private static void makeItemList(){
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
		itemList.add(spellbook);
	}
	
	public static void addItemBlock(ItemBlock itemBlock){
		itemList.add(itemBlock);
	}
}
