package com.laton95.runemysteries.potion;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class ModEffect extends Effect {
	
	public ModEffect(EffectType type, int liquidColour) {
		super(type, liquidColour);
	}
	
	public boolean hasEffect(LivingEntity entity) {
		return entity.getActivePotionEffect(this) != null;
	}
}
