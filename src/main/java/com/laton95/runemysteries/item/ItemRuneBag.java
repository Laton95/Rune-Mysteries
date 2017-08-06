package com.laton95.runemysteries.item;

import com.laton95.runemysteries.GuiHandler.GuiIDs;
import com.laton95.runemysteries.inventory.InventoryRuneBag;
import com.laton95.runemysteries.reference.NamesReference;
import com.laton95.runemysteries.util.LogHelper;
import com.laton95.runemysteries.RuneMysteries;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemPickupEvent;
import scala.collection.immutable.StreamViewLike.EmptyView;

public class ItemRuneBag extends RMModItem {

	public ItemRuneBag() {
		super("rune_bag", true);
		setMaxStackSize(1);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 1;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		if (!worldIn.isRemote) {
			if (!playerIn.isSneaking()) {
				playerIn.openGui(RuneMysteries.instance, GuiIDs.RUNE_BAG.ordinal(), worldIn, (int)playerIn.posX, (int)playerIn.posY, (int)playerIn.posZ);
			} else {
				if (!stack.hasTagCompound()) {
					NBTTagCompound tagCompound = new NBTTagCompound();
					tagCompound.setBoolean("autoPickup", true);
					stack.setTagCompound(tagCompound);
				}
				NBTTagCompound tagCompound = stack.getTagCompound();
				if (tagCompound.getBoolean("autoPickup")) {
					tagCompound.setBoolean("autoPickup", false);
					tagCompound.setString("Lore", "test");
					playerIn.sendMessage(new TextComponentTranslation(NamesReference.RuneBag.AUTO_TURN_OFF));
				} else {
					tagCompound.setBoolean("autoPickup", true);
					playerIn.sendMessage(new TextComponentTranslation(NamesReference.RuneBag.AUTO_TURN_ON));
				} 
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
	}
}
