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
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RecipeRegistry {
	private static Map<String, ResourceLocation> recipeMap = new HashMap<>();

	@SubscribeEvent
	public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
		LogHelper.info("Registering recipies");
		makeRecipeList();
		recipeMap.forEach((k, v) -> {
			CraftingHelper.register(v, new IRecipeFactory() {

				@Override
				public IRecipe parse(JsonContext context, JsonObject json) {
					return CraftingHelper.getRecipe(json, context);
				}
			});
		});
	}

	private static void makeRecipeList() {
		recipeMap.put("Ruin block", new ResourceLocation(Reference.MOD_ID + ":ruin_block.json"));
		recipeMap.put("Temple block", new ResourceLocation(Reference.MOD_ID + ":temple_block.json"));
		recipeMap.put("Blood block", new ResourceLocation(Reference.MOD_ID + ":blood_block.json"));
		recipeMap.put("Flesh block", new ResourceLocation(Reference.MOD_ID + ":flesh_block.json"));
		recipeMap.put("Temple block stairs", new ResourceLocation(Reference.MOD_ID + ":temple_block_stairs.json"));
		recipeMap.put("Blood block stairs", new ResourceLocation(Reference.MOD_ID + ":blood_block_stairs.json"));
		recipeMap.put("Temple block slab", new ResourceLocation(Reference.MOD_ID + ":temple_block_slab.json"));
		recipeMap.put("Blood block slab", new ResourceLocation(Reference.MOD_ID + ":blood_block_slab.json"));
		recipeMap.put("Temple block rail", new ResourceLocation(Reference.MOD_ID + ":temple_block_rail.json"));
		recipeMap.put("Blood block rail", new ResourceLocation(Reference.MOD_ID + ":blood_block_rail.json"));
		recipeMap.put("Stonebrick rail", new ResourceLocation(Reference.MOD_ID + ":stonebrick_rail.json"));
	}
}
