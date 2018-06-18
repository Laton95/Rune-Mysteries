package com.laton95.runemysteries.init;

import com.laton95.runemysteries.item.*;
import com.laton95.runemysteries.item.ItemRune.EnumRuneType;
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

import java.util.ArrayList;
import java.util.Arrays;

@Mod.EventBusSubscriber
public class ModItems
{
	
	public static final ItemRune RUNE = new ItemRune();
	public static final ItemTalisman RUNE_TALISMAN = new ItemTalisman();
	public static final ItemSpellbook SPELLBOOK = new ItemSpellbook();
	public static final ItemRuneBag RUNE_BAG = new ItemRuneBag();
	public static final ItemScroll MINE_SCROLL = new ItemScroll();
	public static final ItemBanana BANANA = new ItemBanana();
	public static final ItemExParrot EX_PARROT = new ItemExParrot();
	
	private static ArrayList<Item> items = new ArrayList<Item>(
			Arrays.asList(
					RUNE,
					RUNE_TALISMAN,
					SPELLBOOK,
					RUNE_BAG,
					MINE_SCROLL,
					BANANA,
					EX_PARROT
			));
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		LogHelper.info("Registering items");
		for (Item item : items)
		{
			event.getRegistry().register(item);
		}
		registerRenders();
	}
	
	public static void registerRenders()
	{
		for (Item item : items)
		{
			if (item.getHasSubtypes() && item instanceof RMModItem)
			{
				Class<? extends Enum<?>> values = ((RMModItem) item).getValues();
				for (int i = 0; i < values.getEnumConstants().length; i++)
				{
					registerRenderVariants(item, i, item.getRegistryName().getResourcePath() + "_" + values.getEnumConstants()[i]);
				}
			} else if (item.getHasSubtypes() && item instanceof ItemBlock)
			{
				for (int i = 0; i < EnumRuneType.values().length; i++)
				{
					registerRenderVariants(item, i, item.getRegistryName().getResourcePath());
				}
			} else
			{
				ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
			}
		}
	}
	
	public static void registerRenderVariants(Item item, int meta, String fileName)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(ModReference.MOD_ID, fileName), "inventory"));
	}
	
	public static void addItemBlock(ItemBlock itemBlock)
	{
		items.add(itemBlock);
	}
	
	public static void addItem(Item item)
	{
		items.add(item);
	}
}
