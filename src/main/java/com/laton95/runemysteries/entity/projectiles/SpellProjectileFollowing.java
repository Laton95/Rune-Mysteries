package com.laton95.runemysteries.entity.projectiles;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class SpellProjectileFollowing extends SpellProjectileBase
{

	private final Entity targetEntity;

	public SpellProjectileFollowing(World worldIn, EntityLivingBase throwerIn, Entity target)
	{
		super(worldIn, throwerIn);
		targetEntity = target;
	}

	@Override
	public void onEntityUpdate()
	{
		if (targetEntity.isDead)
		{
			setDead();
		}
		double followSpeed = 0.1;
		double x = targetEntity.posX - posX;
		double z = targetEntity.posZ - posZ;
		double y = targetEntity.posY + targetEntity.getEyeHeight() - 0.1 - posY;
		double l = Math.sqrt(Math.pow(x, 2) + Math.pow(z, 2));
		double l2 = Math.sqrt(Math.pow(l, 2) + Math.pow(y, 2));
		double theta = Math.asin(z / l);
		double theta2 = 90 - theta;
		double thetaY = Math.asin(y / l2);
		double zs = Math.copySign(followSpeed * Math.sin(theta), z);
		double xs = Math.copySign(followSpeed * Math.sin(theta2), x);
		double ys = Math.copySign(followSpeed * Math.sin(thetaY), y);
		motionX = xs;
		motionY = ys;
		motionZ = zs;

		super.onEntityUpdate();
	}

	@Override
	public void onImpact(RayTraceResult result)
	{
		if (!world.isRemote)
		{
			if (result.entityHit != null)
			{
				result.entityHit.attackEntityFrom(
						new EntityDamageSourceIndirect("runeMagicDamageProjectile", this, getThrower()).setProjectile(),
						1);
			}
		}
		super.onImpact(result);
	}
}
