package com.laton95.runemysteries.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityProjectileSpellDamage extends EntityProjectileSpellBase
{
	
	private final int damage;
	
	public EntityProjectileSpellDamage(World worldIn)
	{
		super(worldIn);
		damage = 0;
	}
	
	public EntityProjectileSpellDamage(World worldIn, EntityLivingBase throwerIn, int damage)
	{
		super(worldIn, throwerIn);
		this.damage = damage;
	}
	
	@Override
	protected EnumParticleTypes getImpactParticles()
	{
		return EnumParticleTypes.CRIT;
	}
	
	@Override
	public void onImpact(RayTraceResult result)
	{
		if(!world.isRemote)
		{
			if(result.entityHit != null)
			{
				result.entityHit.attackEntityFrom(new EntityDamageSourceIndirect("runeMagicDamageProjectile", this, getThrower()).setProjectile(), damage);
			}
			setDead();
		}
		super.onImpact(result);
	}
	
	@Override
	protected EnumParticleTypes getTrailParticles()
	{
		return EnumParticleTypes.FIREWORKS_SPARK;
	}
}
