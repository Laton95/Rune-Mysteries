package com.laton95.runemysteries.spells.projectiles;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class SpellProjectileDamage extends SpellProjectileBase {
	private final int damage;
	
	public SpellProjectileDamage(World worldIn, EntityLivingBase throwerIn, int damage) {
		super(worldIn, throwerIn);
		this.damage = damage;
	}

	@Override
	public void onImpact(RayTraceResult result) {
		if (!world.isRemote) {
			if (result.entityHit != null) {
				result.entityHit.attackEntityFrom(new EntityDamageSourceIndirect("runeMagicDamageProjectile", this, this.getThrower()).setProjectile(), damage);
			}
			this.setDead();
		}
		super.onImpact(result);
	}
}
