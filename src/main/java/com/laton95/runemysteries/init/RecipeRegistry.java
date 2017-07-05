package com.laton95.runemysteries.init;

import java.util.ArrayList;

import com.google.gson.JsonObject;
import com.laton95.runemysteries.reference.Reference;
import com.laton95.runemysteries.utility.LogHelper;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RecipeRegistry {
	private static ArrayList<ResourceLocation> recipeList = new ArrayList<ResourceLocation>();

	public static ResourceLocation ruinBlock = new ResourceLocation(Reference.MOD_ID + ":ruin_block.json");

	public static void registerRecipes() {
		LogHelper.info("Registering recipes...");
		makeRecipeList();
		for (ResourceLocation recipe : recipeList) {
			CraftingHelper.register(recipe, new IRecipeFactory() {

				@Override
				public IRecipe parse(JsonContext context, JsonObject json) {
					return CraftingHelper.getRecipe(json, context);
				}
			});
		}
		LogHelper.info("All recipes registered successfully");
	}

	private static void makeRecipeList() {
		recipeList.add(ruinBlock);
	}
}
