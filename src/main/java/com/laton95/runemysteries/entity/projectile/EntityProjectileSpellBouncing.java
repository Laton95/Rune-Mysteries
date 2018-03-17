package com.laton95.runemysteries.entity.projectile;

import com.laton95.runemysteries.util.LogHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityProjectileSpellBouncing extends EntityProjectileSpellBase
{
	public EntityProjectileSpellBouncing(World worldIn)
	{
		super(worldIn);
	}
	
	public EntityProjectileSpellBouncing(World worldIn, EntityLivingBase throwerIn)
	{
		super(worldIn, throwerIn);
	}
	
	@Override
	public void onImpact(RayTraceResult result)
	{
		super.onImpact(result);
		
		if (ticksExisted > 10)
		{
			double yDecay = 1.0;
			double hBoost = 1.0;
			if (result.typeOfHit == RayTraceResult.Type.BLOCK)
			{
				switch (result.sideHit)
				{
					case DOWN:
						motionY = -motionY;
						break;
					case EAST:
						motionX = -motionX * hBoost;
						break;
					case NORTH:
						motionZ = -motionZ * hBoost;
						break;
					case SOUTH:
						motionZ = -motionZ * hBoost;
						break;
					case UP:
						motionY = -motionY * yDecay;
						break;
					case WEST:
						motionX = -motionX * hBoost;
						break;
					default:
						break;
				}
			} else
			{
				if (!world.isRemote) LogHelper.info("Hit entity on server");
				if (world.isRemote) LogHelper.info("Hit entity on client");
				motionY = Math.abs(motionY) * yDecay + result.entityHit.motionY;
				motionX = -motionX * hBoost + result.entityHit.motionX;
				motionZ = -motionZ * hBoost + result.entityHit.motionZ;
			}
		}
		
		
		if (ticksExisted > 2000 && !world.isRemote)
		{
			setDead();
		}
	}
	
	@Override
	protected EnumParticleTypes getImpactParticles()
	{
		return EnumParticleTypes.CRIT_MAGIC;
	}
	
	@Override
	protected EnumParticleTypes getTrailParticles()
	{
		return EnumParticleTypes.CRIT;
	}
}
