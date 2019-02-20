package com.laton95.runemysteries.item;

import com.laton95.runemysteries.enums.EnumRuneType;

public class ItemRune extends ModItem {
	
	private final EnumRuneType runeType;
	
	public ItemRune(EnumRuneType runeType) {
		super();
		this.runeType = runeType;
	}
	
	public EnumRuneType getRuneType() {
		return runeType;
	}
}
