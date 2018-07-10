package com.laton95.runemysteries.item;

import com.laton95.runemysteries.config.ModConfig;
import com.laton95.runemysteries.enums.EnumRuneType;

public class ItemChargedBattlestaff extends RMModItem implements IRuneSource
{
	private final EnumRuneType runeType;
	
	public ItemChargedBattlestaff(String name, boolean showInCreative, EnumRuneType runeType)
	{
		super(name, showInCreative);
		setMaxStackSize(1);
		setMaxDamage(ModConfig.GAMEPLAY.battlestaffDurability);
		this.runeType = runeType;
	}
	
	@Override
	public EnumRuneType getRuneType()
	{
		return runeType;
	}
}
