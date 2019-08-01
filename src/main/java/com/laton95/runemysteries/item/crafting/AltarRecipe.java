package com.laton95.runemysteries.item.crafting;

import com.google.gson.JsonObject;
import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.init.ModBlocks;
import com.laton95.runemysteries.inventory.crafting.RuneCraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class AltarRecipe implements IRecipe<RuneCraftingInventory> {
	
	public static final IRecipeType<AltarRecipe> RUNE_ALTAR_RECIPE = new RuneAltarRecipeType();
	
	public static final Serializer SERIALIZER = new Serializer();
	
	private final ResourceLocation id;
	
	private final Ingredient input;
	
	private final ItemStack output;
	
	private final EnumRuneType altarType;
	
	private final boolean anyAltar;
	
	public AltarRecipe(ResourceLocation id, Ingredient input, ItemStack output, EnumRuneType altarType, boolean anyAltar) {
		this.id = id;
		this.input = input;
		this.output = output;
		this.altarType = altarType;
		this.anyAltar = anyAltar;
	}
	
	@Override
	public boolean matches(RuneCraftingInventory inv, World worldIn) {
		return this.input.test(inv.getInput()) && (inv.getRuneType() == altarType || anyAltar);
	}
	
	@Override
	public ItemStack getCraftingResult(RuneCraftingInventory inv) {
		return output.copy();
	}
	
	@Override
	public boolean canFit(int width, int height) {
		return true;
	}
	
	public ItemStack getCraftingResult() {
		return output.copy();
	}
	
	@Override
	public ItemStack getRecipeOutput() {
		return output;
	}
	
	@Override
	public ResourceLocation getId() {
		return id;
	}
	
	@Override
	public IRecipeSerializer<?> getSerializer() {
		return null;
	}
	
	@Override
	public IRecipeType<?> getType() {
		return RUNE_ALTAR_RECIPE;
	}
	
	@Override
	public ItemStack getIcon() {
		return new ItemStack(ModBlocks.AIR_ALTAR);
	}
	
	public static class RuneAltarRecipeType implements IRecipeType<AltarRecipe> {
		
		@Override
		public String toString() {
			return RuneMysteries.MOD_ID + ":rune_altar";
		}
	}
	
	private static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<AltarRecipe> {
		
		Serializer() {
			setRegistryName(new ResourceLocation(RuneMysteries.MOD_ID, "rune_altar"));
		}
		
		@Override
		public AltarRecipe read(ResourceLocation recipeId, JsonObject json) {
			boolean anyAltar = false;
			
			Ingredient ingredient;
			if(JSONUtils.isJsonArray(json, "ingredient")) {
				ingredient = Ingredient.deserialize(JSONUtils.getJsonArray(json, "ingredient"));
			}
			else {
				ingredient = Ingredient.deserialize(JSONUtils.getJsonObject(json, "ingredient"));
			}
			
			String altarName = JSONUtils.getString(json, "altar");
			EnumRuneType altarType;
			try {
				altarType = EnumRuneType.valueOf(altarName.toUpperCase());
			}
			catch(IllegalArgumentException e) {
				if(altarName.equals("any")) {
					altarType = EnumRuneType.AIR;
					anyAltar = true;
				}
				else {
					throw new IllegalArgumentException("Invalid altar type: " + altarName);
				}
			}
			
			ItemStack itemstack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
			
			return new AltarRecipe(recipeId, ingredient, itemstack, altarType, anyAltar);
		}
		
		@Override
		public AltarRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
			Ingredient ingredient = Ingredient.read(buffer);
			ItemStack itemstack = buffer.readItemStack();
			EnumRuneType runeType = EnumRuneType.values()[buffer.readVarInt()];
			boolean anyAltar = buffer.readBoolean();
			return new AltarRecipe(recipeId, ingredient, itemstack, runeType, anyAltar);
		}
		
		@Override
		public void write(PacketBuffer buffer, AltarRecipe recipe) {
			recipe.input.write(buffer);
			buffer.writeItemStack(recipe.output);
			buffer.writeVarInt(recipe.altarType.ordinal());
			buffer.writeBoolean(recipe.anyAltar);
		}
	}
}
