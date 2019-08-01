package com.laton95.runemysteries.item;

import com.laton95.runemysteries.config.Config;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.util.ModLog;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class BattlestaffItem extends ModItem implements IRuneSource {
	
	private final EnumRuneType runeType;
	
	public BattlestaffItem(EnumRuneType runeType) {
		super(new Properties().maxStackSize(1));
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
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		if(!world.isRemote) {
			ItemStack itemstack = player.getHeldItem(hand);
			ModLog.info(String.valueOf(itemstack.getMaxDamage()));
			itemstack.attemptDamageItem(1, world.rand, (ServerPlayerEntity) player);
		}
		return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(new TranslationTextComponent("item.runemysteries.battlestaff.tooltip", runeType.toString().toLowerCase()).applyTextStyle(TextFormatting.GRAY));
	}
}
