package com.laton95.runemysteries.item;

import com.laton95.runemysteries.config.ModConfig;

public class ItemWaterBattlestaff extends RMModItem implements IRuneSource
{
	public ItemWaterBattlestaff()
	{
		super("water_battlestaff", true);
		setMaxStackSize(1);
		setMaxDamage(ModConfig.GAMEPLAY.battlestaffDurability);
	}
	
	@Override
	public ItemRune.EnumRuneType getRuneType()
	{
		return ItemRune.EnumRuneType.WATER;
	}
}
