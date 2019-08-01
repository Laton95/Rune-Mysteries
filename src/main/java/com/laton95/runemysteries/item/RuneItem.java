package com.laton95.runemysteries.item;

import com.laton95.runemysteries.enums.EnumRuneType;

public class RuneItem extends ModItem {
	
	private final EnumRuneType runeType;
	
	public RuneItem(EnumRuneType runeType) {
		super();
		this.runeType = runeType;
	}
	
	public EnumRuneType getRuneType() {
		return runeType;
	}
}
