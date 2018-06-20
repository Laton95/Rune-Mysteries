package com.laton95.runemysteries.item;

import com.laton95.runemysteries.config.ModConfig;
import com.laton95.runemysteries.reference.NamesReference;
import com.laton95.runemysteries.util.TeleportHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemScroll extends RMModItem
{
	
	public ItemScroll()
	{
		super("scroll", true, EnumScrollType.class);
		maxStackSize = 16;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		ItemStack scroll = playerIn.getHeldItem(handIn);
		int dimID = getDimIDFromMetadata(scroll.getItemDamage());
		if(!worldIn.isRemote)
		{
			if(worldIn.provider.getDimension() != dimID)
			{
				if(!playerIn.isCreative())
				{
					playerIn.getHeldItem(handIn).shrink(1);
				}
				playerIn.getCooldownTracker().setCooldown(this, 500);
				TeleportHelper.teleportEntity(playerIn, dimID, 0, 64, 0);
			}
			else
			{
				playerIn.sendMessage(new TextComponentTranslation(NamesReference.Scroll.FAIL));
			}
			
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}
	
	private int getDimIDFromMetadata(int metadata)
	{
		switch(EnumScrollType.values()[metadata])
		{
			case MINE:
				return ModConfig.DIMENSIONS.essenceMineDimID;
		}
		
		return 0;
	}
	
	public enum EnumScrollType implements IStringSerializable
	{
		MINE("mine");
		
		private final String name;
		
		EnumScrollType(String name)
		{
			this.name = name;
		}
		
		@Override
		public String getName()
		{
			return name;
		}
		
		@Override
		public String toString()
		{
			return name;
		}
	}
}
