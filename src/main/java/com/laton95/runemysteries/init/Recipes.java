package com.laton95.runemysteries.init;

import com.google.gson.JsonObject;
import com.laton95.runemysteries.reference.Reference;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Recipes {
	public static void init() {
		CraftingHelper.register(new ResourceLocation(Reference.MOD_ID + ":spellbook.json"), new IRecipeFactory() {
			
			@Override
			public IRecipe parse(JsonContext context, JsonObject json) {
				return CraftingHelper.getRecipe(json, context);
			}
		});
	}
}
