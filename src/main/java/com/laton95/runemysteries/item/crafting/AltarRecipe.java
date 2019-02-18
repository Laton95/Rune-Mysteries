package com.laton95.runemysteries.item.crafting;

import com.google.gson.JsonObject;
import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.inventory.InventoryAltar;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.RecipeType;

public class AltarRecipe implements IRecipe {
	
	private final ResourceLocation id;
	
	private final Ingredient input;
	
	private final ItemStack output;
	
	private final EnumRuneType altarType;
	
	private final boolean anyAltar;
	
	public static final RecipeType<AltarRecipe> RUNE_ALTAR_TYPE = RecipeType.get(new ResourceLocation(RuneMysteries.MOD_ID, "rune_altar"), AltarRecipe.class);
	
	public static final IRecipeSerializer<AltarRecipe> RUNE_ALTAR_SERIALIZER = new AltarRecipe.Serializer();
	
	public AltarRecipe(ResourceLocation id, Ingredient input, ItemStack output, EnumRuneType altarType, boolean anyAltar) {
		this.id = id;
		this.input = input;
		this.output = output;
		this.altarType = altarType;
		this.anyAltar = anyAltar;
	}
	
	@Override
	public boolean matches(IInventory inventory, World worldIn) {
		if(inventory instanceof InventoryAltar) {
			boolean validAltar = anyAltar || ((InventoryAltar) inventory).getRuneType() == altarType;
			return validAltar && input.test(((InventoryAltar) inventory).getContents());
		}
		return false;
	}
	
	@Override
	public ItemStack getCraftingResult(IInventory inventory) {
		return output.copy();
	}
	
	@Override
	public boolean canFit(int width, int height) {
		return true;
	}
	
	@Override
	public ItemStack getRecipeOutput() {
		return output;
	}
	
	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> ingredients = NonNullList.create();
		ingredients.add(this.input);
		return ingredients;
	}
	
	@Override
	public ResourceLocation getId() {
		return id;
	}
	
	@Override
	public IRecipeSerializer<?> getSerializer() {
		return RUNE_ALTAR_SERIALIZER;
	}
	
	@Override
	public RecipeType<? extends IRecipe> getType() {
		return RUNE_ALTAR_TYPE;
	}
	
	public static class Serializer implements IRecipeSerializer<AltarRecipe> {
		
		private static ResourceLocation NAME = new ResourceLocation(RuneMysteries.MOD_ID, "rune_altar");
		
		public AltarRecipe read(ResourceLocation recipeId, JsonObject json) {
			boolean anyAltar = false;
			
			Ingredient ingredient;
			if(JsonUtils.isJsonArray(json, "ingredient")) {
				ingredient = Ingredient.fromJson(JsonUtils.getJsonArray(json, "ingredient"));
			}
			else {
				ingredient = Ingredient.fromJson(JsonUtils.getJsonObject(json, "ingredient"));
			}
			
			String altarName = JsonUtils.getString(json, "altar");
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
			
			ItemStack itemstack = ShapedRecipe.deserializeItem(JsonUtils.getJsonObject(json, "result"));
			
			return new AltarRecipe(recipeId, ingredient, itemstack, altarType, anyAltar);
		}
		
		public AltarRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
			Ingredient ingredient = Ingredient.fromBuffer(buffer);
			ItemStack itemstack = buffer.readItemStack();
			EnumRuneType runeType = EnumRuneType.values()[buffer.readVarInt()];
			boolean anyAltar = buffer.readBoolean();
			return new AltarRecipe(recipeId, ingredient, itemstack, runeType, anyAltar);
		}
		
		public void write(PacketBuffer buffer, AltarRecipe recipe) {
			recipe.input.writeToBuffer(buffer);
			buffer.writeItemStack(recipe.output);
			buffer.writeVarInt(recipe.altarType.ordinal());
			buffer.writeBoolean(recipe.anyAltar);
		}
		
		@Override
		public ResourceLocation getName() {
			return NAME;
		}
	}
}
