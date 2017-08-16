package com.laton95.runemysteries.spells.projectiles;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class DamageProjectile extends EntityThrowable{
	private Entity thrower;
	private int damage;
	
	public DamageProjectile(World worldIn) {
		super(worldIn);
	}
	
	public DamageProjectile(World worldIn, EntityLivingBase throwerIn, int damage) {
		super(worldIn, throwerIn);
		this.thrower = throwerIn;
		this.damage = damage;
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (!world.isRemote) {
			if (result.entityHit != null) {
				result.entityHit.attackEntityFrom(new EntityDamageSourceIndirect("runeMagicDamageProjectile", this, thrower).setProjectile(), damage);
			}
			for (int i = 0; i < 32; ++i)
	        {
	            this.world.spawnParticle(EnumParticleTypes.PORTAL, this.posX, this.posY + this.rand.nextDouble() * 2.0D, this.posZ, this.rand.nextGaussian(), 0.0D, this.rand.nextGaussian());
	        }
			this.setDead();
		}
	}
}
