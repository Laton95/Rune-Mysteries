package com.laton95.runemysteries.init;

import java.util.ArrayList;

import com.laton95.runemysteries.item.ItemRune;
import com.laton95.runemysteries.item.ItemRuneBag;
import com.laton95.runemysteries.item.ItemScroll;
import com.laton95.runemysteries.item.ItemSpellbook;
import com.laton95.runemysteries.item.ItemTalisman;
import com.laton95.runemysteries.item.RMModItem;
import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.util.LogHelper;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ItemRegistry
{

	private static ArrayList<Item> itemList = new ArrayList<>();
	public static final ItemRune RUNE = new ItemRune("rune");
	public static final RMModItem RUNE_ESSENCE = new RMModItem("rune_Essence", true);
	public static final ItemTalisman RUNE_TALISMAN = new ItemTalisman("talisman", true);
	public static final ItemSpellbook SPELLBOOK = new ItemSpellbook();
	public static final ItemRuneBag RUNE_BAG = new ItemRuneBag();
	public static final ItemScroll MINE_SCROLL = new ItemScroll("mine_scroll", true);

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		LogHelper.info("Registering items");
		makeItemList();
		for (Item item : itemList)
		{
			event.getRegistry().register(item);
		}
		registerRenders();
	}

	public static void registerRenders()
	{
		for (Item item : itemList)
		{
			if (item.getHasSubtypes() && item instanceof RMModItem)
			{
				Class<? extends Enum<?>> values = ((RMModItem) item).getValues();
				for (int i = 0; i < values.getEnumConstants().length; i++)
				{
					registerRenderVariants(item, i, item.getRegistryName().getResourcePath() + "_"
							+ values.getEnumConstants()[i]);
				}
			}
			else
			{
				ModelLoader.setCustomModelResourceLocation(item, 0,
						new ModelResourceLocation(item.getRegistryName(), "inventory"));
			}
		}
	}

	public static void registerRenderVariants(Item item, int meta, String fileName)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta,
				new ModelResourceLocation(new ResourceLocation(ModReference.MOD_ID, fileName), "inventory"));
	}

	private static void makeItemList()
	{
		itemList.add(RUNE);
		itemList.add(RUNE_ESSENCE);
		itemList.add(RUNE_TALISMAN);

		itemList.add(SPELLBOOK);
		itemList.add(RUNE_BAG);
		itemList.add(MINE_SCROLL);
	}

	public static void addItemBlock(ItemBlock itemBlock)
	{
		itemList.add(itemBlock);
	}

	public static void addItem(Item item)
	{
		itemList.add(item);
	}
}
