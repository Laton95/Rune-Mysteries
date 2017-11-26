package com.laton95.runemysteries.item;

import com.laton95.runemysteries.block.IMetaBlock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class MetaItemBlock extends ItemBlock
{

	public MetaItemBlock(Block block)
	{
		super(block);
		if (!(block instanceof IMetaBlock))
		{
			throw new IllegalArgumentException(
					String.format("The given block %s is not an instance of IMetaBlock", block.getUnlocalizedName()));
		}
		setHasSubtypes(true);
		setMaxDamage(0);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return super.getUnlocalizedName() + "." + ((IMetaBlock) block).getSpecialName(stack);
	}

	@Override
	public int getMetadata(int damage)
	{
		return damage;
	}
}
