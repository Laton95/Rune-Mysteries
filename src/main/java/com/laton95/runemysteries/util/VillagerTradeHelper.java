package com.laton95.runemysteries.util;

import java.util.Random;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

public class VillagerTradeHelper
{

	public static class Trade implements EntityVillager.ITradeList
	{

		public Item buyingItem;
		public EntityVillager.PriceInfo buyingPriceInfo;
		int buyingMaxMeta;
		public Item sellingItem;
		public EntityVillager.PriceInfo sellingPriceInfo;
		public int sellingMaxMeta;

		public Trade(Item buyingItem, EntityVillager.PriceInfo buyingPriceInfo, int buyingMaxMeta, Item sellingItem,
				EntityVillager.PriceInfo sellingPriceInfo, int sellingMaxMeta)
		{
			this.buyingItem = buyingItem;
			this.buyingPriceInfo = buyingPriceInfo;
			this.buyingMaxMeta = buyingMaxMeta;
			this.sellingItem = sellingItem;
			this.sellingPriceInfo = sellingPriceInfo;
			this.sellingMaxMeta = sellingMaxMeta;
		}

		@Override
		public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
		{
			int i = buyingPriceInfo.getPrice(random);
			int j = sellingPriceInfo.getPrice(random);
			recipeList.add(new MerchantRecipe(new ItemStack(buyingItem, i, random.nextInt(buyingMaxMeta + 1)),
					new ItemStack(sellingItem, j, random.nextInt(sellingMaxMeta + 1))));
		}
	}
}
