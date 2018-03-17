package com.laton95.runemysteries.item;

import com.laton95.runemysteries.GuiHandler.GuiIDs;
import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.reference.NamesReference;
import com.laton95.runemysteries.util.ItemNBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemRuneBag extends RMModItem
{
	
	public ItemRuneBag()
	{
		super("rune_bag", true);
		setMaxStackSize(1);
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 1;
	}
	
	/*
	 * InvProvider class taken from Vaskii's Botania mod under the Botania
	 * licence modified to check for runes instead of flowers
	 */
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound oldCapNbt)
	{
		return new InvProvider();
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		ItemStack stack = playerIn.getHeldItem(handIn);
		if (!worldIn.isRemote)
		{
			if (!playerIn.isSneaking())
			{
				playerIn.openGui(RuneMysteries.instance, GuiIDs.RUNE_BAG.ordinal(), worldIn, (int) playerIn.posX, (int) playerIn.posY, (int) playerIn.posZ);
			} else
			{
				ItemNBTHelper.toggleBoolean(stack, "autoPickup");
				if (ItemNBTHelper.getBoolean(stack, "autoPickup", true))
				{
					playerIn.sendMessage(new TextComponentTranslation(NamesReference.RuneBag.AUTO_TURN_ON));
				} else
				{
					playerIn.sendMessage(new TextComponentTranslation(NamesReference.RuneBag.AUTO_TURN_OFF));
				}
			}
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}
	
	private static class InvProvider implements ICapabilitySerializable<NBTBase>
	{
		
		private final IItemHandler inv = new ItemStackHandler(14)
		{
			
			@Nonnull
			@Override
			public ItemStack insertItem(int slot, @Nonnull ItemStack toInsert, boolean simulate)
			{
				if (!toInsert.isEmpty() && toInsert.getItem() instanceof ItemRune)
				{
					return super.insertItem(slot, toInsert, simulate);
				} else
				{
					return toInsert;
				}
			}
		};
		
		@Override
		public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
		{
			return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
		}
		
		@Override
		public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing)
		{
			if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			{
				return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inv);
			} else
			{
				return null;
			}
		}
		
		@Override
		public NBTBase serializeNBT()
		{
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.writeNBT(inv, null);
		}
		
		@Override
		public void deserializeNBT(NBTBase nbt)
		{
			CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.readNBT(inv, null, nbt);
		}
	}
}
