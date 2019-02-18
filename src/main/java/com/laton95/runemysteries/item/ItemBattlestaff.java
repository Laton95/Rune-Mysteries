package com.laton95.runemysteries.item;

import com.laton95.runemysteries.config.Config;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.util.ModLog;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBattlestaff extends ModItem implements IRuneSource {
	
	private final EnumRuneType runeType;
	
	public ItemBattlestaff(EnumRuneType runeType) {
		super(runeType.toString().toLowerCase() + "_battlestaff", new Properties().maxStackSize(1));
		this.runeType = runeType;
	}
	
	@Override
	public int getMaxDamage(ItemStack stack) {
		return Config.battlestaffDurability;
	}
	
	@Override
	public EnumRuneType getRuneType() {
		return runeType;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(!worldIn.isRemote) {
			ItemStack itemstack = playerIn.getHeldItem(handIn);
			ModLog.info(String.valueOf(itemstack.getMaxDamage()));
			itemstack.damageItem(5, playerIn);
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TextComponentTranslation("item.runemysteries.battlestaff.tooltip", runeType.toString().toLowerCase()).applyTextStyle(TextFormatting.GRAY));
	}
}
