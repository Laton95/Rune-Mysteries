package com.laton95.runemysteries.item;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.enums.EnumRuneType;
import net.minecraft.item.Item;

public class RuneItem extends Item {
	
	private final EnumRuneType runeType;
	
	public RuneItem(EnumRuneType runeType) {
		super(new Properties().group(RuneMysteries.RUNE_GROUP));
		this.runeType = runeType;
	}
	
	public EnumRuneType getRuneType() {
		return runeType;
	}
}
