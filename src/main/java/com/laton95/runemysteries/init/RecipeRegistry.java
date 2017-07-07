package com.laton95.runemysteries.init;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;
import com.laton95.runemysteries.reference.Reference;
import com.laton95.runemysteries.utility.LogHelper;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;

public class RecipeRegistry {
	private static Map<String, ResourceLocation> recipeMap = new HashMap<String, ResourceLocation>();

	public static void registerRecipes() {
		LogHelper.info("Registering recipes...");
		makeRecipeList();
		recipeMap.forEach((k, v) -> {
			CraftingHelper.register(v, new IRecipeFactory() {

				@Override
				public IRecipe parse(JsonContext context, JsonObject json) {
					return CraftingHelper.getRecipe(json, context);
				}
			});
			LogHelper.info(k + " recipe registered successfully");
		});
		LogHelper.info("All recipes registered successfully");
	}

	private static void makeRecipeList() {
		recipeMap.put("Ruin block", new ResourceLocation(Reference.MOD_ID + ":ruin_block.json"));
	}
}
