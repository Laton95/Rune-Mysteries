package com.laton95.runemysteries.block;

import java.util.ArrayList;
import java.util.Random;

import com.laton95.runemysteries.init.ItemRegistry;
import com.laton95.runemysteries.item.ItemRune;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockOuraniaAltar extends BlockRuneAltar {

	public BlockOuraniaAltar() {
		super("ourania_Altar_Block", null);
		setBlockUnbreakable();
	}

	@Override
	protected void spawnItem(World worldIn, Entity entityIn, Item item) {
		while (((EntityItem) entityIn).getItem().getCount() > 0) {
			ItemStack itemstack = new ItemStack(item);
			if (item == null) {
				itemstack = new ItemStack(getRandomRune());
			}
			spawnAsEntity(worldIn, entityIn.getPosition(), itemstack);
			((EntityItem) entityIn).getItem().setCount(((EntityItem) entityIn).getItem().getCount() - 1);
		}
		entityIn.setDead();

	}

	private ItemRune getRandomRune() {
		ArrayList<ItemRune> runes = new ArrayList<ItemRune>();
		runes.add(ItemRegistry.airRune);
		runes.add(ItemRegistry.astralRune);
		runes.add(ItemRegistry.bloodRune);
		runes.add(ItemRegistry.bodyRune);
		runes.add(ItemRegistry.chaosRune);
		runes.add(ItemRegistry.cosmicRune);
		runes.add(ItemRegistry.deathRune);
		runes.add(ItemRegistry.earthRune);
		runes.add(ItemRegistry.fireRune);
		runes.add(ItemRegistry.lawRune);
		runes.add(ItemRegistry.mindRune);
		runes.add(ItemRegistry.natureRune);
		runes.add(ItemRegistry.soulRune);
		runes.add(ItemRegistry.waterRune);

		ItemRune rune = runes.get(new Random().nextInt(14));
		return rune;
	}
}
