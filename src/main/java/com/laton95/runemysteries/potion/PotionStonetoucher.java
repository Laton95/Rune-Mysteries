package com.laton95.runemysteries.potion;

import com.laton95.runemysteries.reference.ModReference;
import net.minecraft.util.ResourceLocation;

public class PotionStonetoucher extends RMModPotion
{
	public PotionStonetoucher()
	{
		super("stonetoucher", false, 1, new ResourceLocation(ModReference.MOD_ID, "textures/gui/potion/stonetoucher_icon.png"));
		setBeneficial();
	}
}
