package com.laton95.runemysteries.block;

import java.util.ArrayList;
import java.util.List;

import com.laton95.runemysteries.init.BlockRegistry;
import com.laton95.runemysteries.init.LootRegistry;
import com.laton95.runemysteries.item.ItemRune.EnumRuneType;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;

public class BlockOuraniaAltar extends BlockRuneAltar
{

	public BlockOuraniaAltar()
	{
		super("ourania_Altar_Block", EnumRuneType.AIR);
		setBlockUnbreakable();
		item = null;
	}

	@Override
	protected void spawnItem(World worldIn, Entity entityIn, Item item, int metadata)
	{
		while (((EntityItem) entityIn).getItem().getCount() > 0)
		{
			if (item == null)
			{
				List<ItemStack> items = getRandomRune(worldIn);
				for (ItemStack rune : items)
				{
					spawnAsEntity(worldIn, entityIn.getPosition(), rune);
				}
			}
			else
			{
				ItemStack itemstack = new ItemStack(item);
				spawnAsEntity(worldIn, entityIn.getPosition(), itemstack);

			}
			((EntityItem) entityIn).getItem().setCount(((EntityItem) entityIn).getItem().getCount() - 1);
		}
		entityIn.setDead();
	}

	@Override
	protected void giveAdvancements(EntityItem entityIn, World worldIn)
	{
		super.giveAdvancements(entityIn, worldIn);
		String thrower = entityIn.getThrower();
		if (thrower != null)
		{
			EntityPlayer player = worldIn.getPlayerEntityByName(thrower);
			if (player instanceof EntityPlayerMP)
			{
				CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP) player,
						new ItemStack(BlockRegistry.OURANIA_ALTAR));
			}
		}
	}

	private List<ItemStack> getRandomRune(World world)
	{
		List<ItemStack> items = new ArrayList<>();
		try
		{
			ResourceLocation resourcelocation = LootRegistry.OURANIA_ALTAR;
			LootTable loottable = world.getLootTableManager().getLootTableFromLocation(resourcelocation);
			LootContext.Builder lootBuilder = new LootContext.Builder((WorldServer) world);
			items = loottable.generateLootForPools(world.rand, lootBuilder.build());
		}
		catch (NullPointerException e)
		{
			items.add(new ItemStack(Blocks.DIRT));
		}
		return items;
	}
}
