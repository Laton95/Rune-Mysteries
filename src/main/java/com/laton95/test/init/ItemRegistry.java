package com.laton95.test.init;

import java.util.ArrayList;

import com.laton95.test.item.ItemAir_Rune;
import com.laton95.test.item.ItemAstral_Rune;
import com.laton95.test.item.ItemBlood_Rune;
import com.laton95.test.item.ItemBody_Rune;
import com.laton95.test.item.ItemChaos_Rune;
import com.laton95.test.item.ItemCosmic_Rune;
import com.laton95.test.item.ItemDeathRune;
import com.laton95.test.item.ItemEarthRune;
import com.laton95.test.item.ItemFireRune;
import com.laton95.test.item.ItemLawRune;
import com.laton95.test.item.ItemMindRune;
import com.laton95.test.item.ItemNatureRune;
import com.laton95.test.item.ItemRune_Essence;
import com.laton95.test.item.ItemSoulRune;
import com.laton95.test.item.ItemTest;
import com.laton95.test.item.ItemWater_Rune;
import com.laton95.test.reference.Reference;
import com.laton95.test.utility.LogHelper;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ItemRegistry {
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event){		
		LogHelper.info("Registering items...");
		for(ItemTest item : makeItemList()){
			
			item.setUnlocalizedName(Reference.MOD_ID + ":" + item.getName());
			item.setRegistryName(Reference.MOD_ID, item.getName().toLowerCase());
			
			event.getRegistry().register(item);
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		    LogHelper.info(item.getRegistryName() + " registered");
		}
	    LogHelper.info("All items registered successfully");
	}
	
	private static ArrayList<ItemTest> makeItemList(){
		ArrayList<ItemTest> itemList = new ArrayList<ItemTest>();
		
		itemList.add(new ItemAir_Rune());
		itemList.add(new ItemAstral_Rune());
		itemList.add(new ItemBlood_Rune());
		itemList.add(new ItemBody_Rune());
		itemList.add(new ItemChaos_Rune());
		itemList.add(new ItemCosmic_Rune());
		itemList.add(new ItemDeathRune());
		itemList.add(new ItemEarthRune());
		itemList.add(new ItemFireRune());
		itemList.add(new ItemLawRune());
		itemList.add(new ItemMindRune());
		itemList.add(new ItemNatureRune());
		itemList.add(new ItemRune_Essence());
		itemList.add(new ItemSoulRune());
		itemList.add(new ItemWater_Rune());
		
		return itemList;
	}
}
