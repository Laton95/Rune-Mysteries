package com.laton95.runemysteries.item;

import com.laton95.runemysteries.enums.EnumRuneType;

public class ItemRune extends RMModItem
{
	private final EnumRuneType runeType;
	
	public ItemRune(EnumRuneType runeType)
	{
		super(runeType.toString().toLowerCase() + "_rune", true);
		this.runeType = runeType;
	}
}
