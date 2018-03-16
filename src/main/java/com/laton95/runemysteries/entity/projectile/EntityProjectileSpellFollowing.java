package com.laton95.runemysteries.entity.projectile;

import com.laton95.runemysteries.util.WorldHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityProjectileSpellFollowing extends EntityProjectileSpellBase
{
	private Entity targetEntity;
	
	public EntityProjectileSpellFollowing(World worldIn)
	{
		super(worldIn);
	}
	
	public EntityProjectileSpellFollowing(World worldIn, EntityLivingBase throwerIn, Entity targetEntity)
	{
		super(worldIn, throwerIn);
		this.targetEntity = targetEntity;
	}
	
	@Override
	public void onUpdate()
	{
		
		
		if (targetEntity != null)
		{
			super.onUpdate();
			
			if (targetEntity.isDead)
			{
				setDead();
			}
			
			if (WorldHelper.isNearby(new BlockPos(this), new BlockPos(targetEntity), 1))
			{
				targetEntity.attackEntityFrom(new EntityDamageSourceIndirect("runeMagicDamageProjectile", this, getThrower()).setProjectile(), 1);
			}
			
			double followSpeed = 0.1;
			double x = targetEntity.posX - posX;
			double z = targetEntity.posZ - posZ;
			double y = targetEntity.posY - posY;
			double l = Math.sqrt(Math.pow(x, 2) + Math.pow(z, 2));
			double l2 = Math.sqrt(Math.pow(l, 2) + Math.pow(y, 2));
			double theta = Math.asin(z / l);
			double theta2 = Math.PI / 2 - theta;
			double thetaY = Math.asin(y / l2);
			double zs = Math.copySign(followSpeed * Math.sin(theta), z);
			double xs = Math.copySign(followSpeed * Math.sin(theta2), x);
			double ys = Math.copySign(followSpeed * Math.sin(thetaY), y);
			motionX = xs;
			motionY = ys;
			motionZ = zs;
		}
	}
	
	@Override
	public void onImpact(RayTraceResult result)
	{
	
	}
	
	@Override
	protected EnumParticleTypes getImpactParticles()
	{
		return EnumParticleTypes.CRIT;
	}
	
	@Override
	protected EnumParticleTypes getTrailParticles()
	{
		return EnumParticleTypes.CRIT;
	}
}
