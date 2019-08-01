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

public class ObeliskRecipe implements IRecipe<RuneCraftingInventory> {
	
	public static final IRecipeType<ObeliskRecipe> OBELISK_RECIPE = new ObeliskRecipeType();
	
	public static final Serializer SERIALIZER = new Serializer();
	
	private final ResourceLocation id;
	
	private final Ingredient input;
	
	private final ItemStack output;
	
	private final EnumRuneType obeliskType;
	
	private final boolean anyObelisk;
	
	public ObeliskRecipe(ResourceLocation id, Ingredient input, ItemStack output, EnumRuneType obeliskType, boolean anyObelisk) {
		this.id = id;
		this.input = input;
		this.output = output;
		this.obeliskType = obeliskType;
		this.anyObelisk = anyObelisk;
	}
	
	@Override
	public boolean matches(RuneCraftingInventory inv, World worldIn) {
		return this.input.test(inv.getInput()) && (inv.getRuneType() == obeliskType || anyObelisk);
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
		return SERIALIZER;
	}
	
	@Override
	public IRecipeType<?> getType() {
		return OBELISK_RECIPE;
	}
	
	@Override
	public ItemStack getIcon() {
		return new ItemStack(ModBlocks.AIR_OBELISK);
	}
	
	public static class ObeliskRecipeType implements IRecipeType<ObeliskRecipe> {
		
		@Override
		public String toString() {
			return RuneMysteries.MOD_ID + ":obelisk";
		}
	}
	
	private static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<ObeliskRecipe> {
		
		Serializer() {
			setRegistryName(new ResourceLocation(RuneMysteries.MOD_ID, "obelisk"));
		}
		
		@Override
		public ObeliskRecipe read(ResourceLocation recipeId, JsonObject json) {
			boolean anyObelisk = false;
			
			Ingredient ingredient;
			if(JSONUtils.isJsonArray(json, "ingredient")) {
				ingredient = Ingredient.deserialize(JSONUtils.getJsonArray(json, "ingredient"));
			}
			else {
				ingredient = Ingredient.deserialize(JSONUtils.getJsonObject(json, "ingredient"));
			}
			
			String obeliskName = JSONUtils.getString(json, "obelisk");
			EnumRuneType obeliskType;
			try {
				obeliskType = EnumRuneType.valueOf(obeliskName.toUpperCase());
			}
			catch(IllegalArgumentException e) {
				if(obeliskName.equals("any")) {
					obeliskType = EnumRuneType.AIR;
					anyObelisk = true;
				}
				else {
					throw new IllegalArgumentException("Invalid obelisk type: " + obeliskName);
				}
			}
			
			if(!obeliskType.hasObelisk()) {
				throw new IllegalArgumentException("Invalid obelisk type: " + obeliskName);
			}
			
			ItemStack itemstack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
			
			if(itemstack.getCount() > 1) {
				throw new IllegalArgumentException("Obelisk recipes cannot result in more than 1 item " + recipeId);
			}
			
			return new ObeliskRecipe(recipeId, ingredient, itemstack, obeliskType, anyObelisk);
		}
		
		@Override
		public ObeliskRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
			Ingredient ingredient = Ingredient.read(buffer);
			ItemStack itemstack = buffer.readItemStack();
			EnumRuneType runeType = EnumRuneType.values()[buffer.readVarInt()];
			boolean anyObelisk = buffer.readBoolean();
			return new ObeliskRecipe(recipeId, ingredient, itemstack, runeType, anyObelisk);
		}
		
		@Override
		public void write(PacketBuffer buffer, ObeliskRecipe recipe) {
			recipe.input.write(buffer);
			buffer.writeItemStack(recipe.output);
			buffer.writeVarInt(recipe.obeliskType.ordinal());
			buffer.writeBoolean(recipe.anyObelisk);
		}
	}
}
