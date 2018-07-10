package com.laton95.runemysteries.util;

import com.google.common.collect.ImmutableList;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

import java.util.List;
import java.util.Random;

public class VillagerTradeHelper
{
	
	public static class Trade implements EntityVillager.ITradeList
	{
		
		public List<Item> buyingItems;
		
		public EntityVillager.PriceInfo buyingPriceInfo;
		
		public List<Item> sellingItems;
		
		public EntityVillager.PriceInfo sellingPriceInfo;
		
		public Trade(Item buyingItem, EntityVillager.PriceInfo buyingPriceInfo, Item sellingItem, EntityVillager.PriceInfo sellingPriceInfo)
		{
			this(ImmutableList.of(buyingItem), buyingPriceInfo, ImmutableList.of(sellingItem), sellingPriceInfo);
		}
		
		public Trade(List<Item> buyingItems, EntityVillager.PriceInfo buyingPriceInfo, List<Item> sellingItems, EntityVillager.PriceInfo sellingPriceInfo)
		{
			this.buyingItems = buyingItems;
			this.buyingPriceInfo = buyingPriceInfo;
			this.sellingItems = sellingItems;
			this.sellingPriceInfo = sellingPriceInfo;
		}
		
		public Trade(Item buyingItem, EntityVillager.PriceInfo buyingPriceInfo, List<Item> sellingItems, EntityVillager.PriceInfo sellingPriceInfo)
		{
			this(ImmutableList.of(buyingItem), buyingPriceInfo, sellingItems, sellingPriceInfo);
		}
		
		public Trade(List<Item> buyingItems, EntityVillager.PriceInfo buyingPriceInfo, Item sellingItem, EntityVillager.PriceInfo sellingPriceInfo)
		{
			this(buyingItems, buyingPriceInfo, ImmutableList.of(sellingItem), sellingPriceInfo);
		}
		
		@Override
		public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
		{
			int i = buyingPriceInfo.getPrice(random);
			int j = sellingPriceInfo.getPrice(random);
			Item buyingItem = buyingItems.get(random.nextInt(buyingItems.size()));
			Item sellingItem = sellingItems.get(random.nextInt(sellingItems.size()));
			recipeList.add(new MerchantRecipe(new ItemStack(buyingItem, i), new ItemStack(sellingItem, j)));
		}
	}
}
