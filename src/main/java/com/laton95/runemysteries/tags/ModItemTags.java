package com.laton95.runemysteries.tags;

import com.laton95.runemysteries.RuneMysteries;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public class ModItemTags {
	
	public static final Tag<Item> RUNES = new ItemTags.Wrapper(new ResourceLocation(RuneMysteries.MOD_ID, "runes"));
}
