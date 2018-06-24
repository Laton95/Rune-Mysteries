package com.laton95.runemysteries.item;

import com.laton95.runemysteries.config.ModConfig;

public class ItemEarthBattlestaff extends RMModItem implements IRuneSource
{
	public ItemEarthBattlestaff()
	{
		super("earth_battlestaff", true);
		setMaxStackSize(1);
		setMaxDamage(ModConfig.GAMEPLAY.battlestaffDurability);
	}
	
	@Override
	public ItemRune.EnumRuneType getRuneType()
	{
		return ItemRune.EnumRuneType.EARTH;
	}
}
