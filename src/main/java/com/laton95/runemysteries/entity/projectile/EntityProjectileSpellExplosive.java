package com.laton95.runemysteries.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityProjectileSpellExplosive extends EntityProjectileSpellBase
{
	
	private int power = 0;
	
	public EntityProjectileSpellExplosive(World worldIn)
	{
		super(worldIn);
	}
	
	public EntityProjectileSpellExplosive(World worldIn, EntityLivingBase throwerIn)
	{
		super(worldIn, throwerIn);
	}
	
	@Override
	protected EnumParticleTypes getImpactParticles()
	{
		return EnumParticleTypes.EXPLOSION_HUGE;
	}
	
	@Override
	public void onImpact(RayTraceResult result)
	{
		if(!world.isRemote)
		{
			world.createExplosion(getThrower(), posX, posY, posZ, power, true);
			setDead();
		}
		
		super.onImpact(result);
	}
	
	@Override
	protected EnumParticleTypes getTrailParticles()
	{
		return EnumParticleTypes.FIREWORKS_SPARK;
	}
	
	public void setExplosivePower(int power)
	{
		this.power = power;
	}
}
