package com.laton95.runemysteries.item.crafting;

import com.google.gson.JsonObject;
import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.inventory.InventoryObelisk;
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

public class ObeliskRecipe implements IRecipe {
	
	private final ResourceLocation id;
	private final Ingredient input;
	private final ItemStack output;
	private final EnumRuneType obeliskType;
	private final boolean anyObelisk;
	
	public static final RecipeType<ObeliskRecipe> OBELISK_TYPE = RecipeType.get(new ResourceLocation(RuneMysteries.MOD_ID, "obelisk"), ObeliskRecipe.class);
	
	public static final IRecipeSerializer<ObeliskRecipe> OBELISK_SERIALIZER = new ObeliskRecipe.Serializer();
	
	public ObeliskRecipe(ResourceLocation id, Ingredient input, ItemStack output, EnumRuneType obeliskType, boolean anyObelisk) {
		this.id = id;
		this.input = input;
		this.output = output;
		this.obeliskType = obeliskType;
		this.anyObelisk = anyObelisk;
	}
	
	@Override
	public boolean matches(IInventory inventory, World worldIn) {
		if(inventory instanceof InventoryObelisk) {
			boolean validObelisk = anyObelisk || ((InventoryObelisk) inventory).getRuneType() == obeliskType;
			return validObelisk && input.test(((InventoryObelisk) inventory).getContents());
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
		return OBELISK_SERIALIZER;
	}
	
	@Override
	public RecipeType<? extends IRecipe> getType() {
		return OBELISK_TYPE;
	}
	
	public static class Serializer implements IRecipeSerializer<ObeliskRecipe> {
		
		private static ResourceLocation NAME = new ResourceLocation(RuneMysteries.MOD_ID, "obelisk");
		
		public ObeliskRecipe read(ResourceLocation recipeId, JsonObject json) {
			boolean anyObelisk = false;
			
			Ingredient ingredient;
			if (JsonUtils.isJsonArray(json, "ingredient")) {
				ingredient = Ingredient.fromJson(JsonUtils.getJsonArray(json, "ingredient"));
			} else {
				ingredient = Ingredient.fromJson(JsonUtils.getJsonObject(json, "ingredient"));
			}
			
			String obeliskName = JsonUtils.getString(json, "obelisk");
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
			
			ItemStack itemstack = ShapedRecipe.deserializeItem(JsonUtils.getJsonObject(json, "result"));
			
			if(itemstack.getCount() > 1) {
				throw new IllegalArgumentException("Obelisk recipes cannot result in more than 1 item " + recipeId);
			}
			
			return new ObeliskRecipe(recipeId, ingredient, itemstack, obeliskType, anyObelisk);
		}
		
		public ObeliskRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
			Ingredient ingredient = Ingredient.fromBuffer(buffer);
			ItemStack itemstack = buffer.readItemStack();
			EnumRuneType runeType = EnumRuneType.values()[buffer.readVarInt()];
			boolean anyObelisk = buffer.readBoolean();
			return new ObeliskRecipe(recipeId, ingredient, itemstack, runeType, anyObelisk);
		}
		
		public void write(PacketBuffer buffer, ObeliskRecipe recipe) {
			recipe.input.writeToBuffer(buffer);
			buffer.writeItemStack(recipe.output);
			buffer.writeVarInt(recipe.obeliskType.ordinal());
			buffer.writeBoolean(recipe.anyObelisk);
		}
		
		@Override
		public ResourceLocation getName() {
			return NAME;
		}
	}
}
