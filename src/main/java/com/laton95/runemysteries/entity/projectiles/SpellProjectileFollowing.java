package com.laton95.runemysteries.entity.projectiles;

import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.util.WorldHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class SpellProjectileFollowing extends SpellProjectileBase
{

	private Entity targetEntity;
	
	private final static ResourceLocation TEXTURE = new ResourceLocation(ModReference.MOD_ID, "textures/entity/projectile/pink-purple.png");
	private final static EnumParticleTypes TRAIL_PARTICLE = EnumParticleTypes.REDSTONE;
	private final static EnumParticleTypes IMPACT_PARTICLE = EnumParticleTypes.CRIT_MAGIC;

	public SpellProjectileFollowing(World worldIn, EntityLivingBase throwerIn, Entity target)
	{
		super(worldIn, throwerIn, TEXTURE, TRAIL_PARTICLE, IMPACT_PARTICLE);
		targetEntity = target;
	}

	@Override
	public void onEntityUpdate()
	{
		if (targetEntity.isDead)
		{
			setDead();
		}
		
		if (WorldHelper.isNearby(new BlockPos(this), new BlockPos(targetEntity), 1))
		{
			targetEntity.attackEntityFrom(
					new EntityDamageSourceIndirect("runeMagicDamageProjectile", this, getThrower()).setProjectile(),
					1);
		}
			
		if (targetEntity != null)
		{
			double followSpeed = 0.1;
			double x = targetEntity.posX - posX;
			double z = targetEntity.posZ - posZ;
			double y = targetEntity.posY - posY;
			double l = Math.sqrt(Math.pow(x, 2) + Math.pow(z, 2));
			double l2 = Math.sqrt(Math.pow(l, 2) + Math.pow(y, 2));
			double theta = Math.asin(z / l);
			double theta2 = Math.PI/2 - theta;
			double thetaY = Math.asin(y / l2);
			double zs = Math.copySign(followSpeed * Math.sin(theta), z);
			double xs = Math.copySign(followSpeed * Math.sin(theta2), x);
			double ys = Math.copySign(followSpeed * Math.sin(thetaY), y);
			motionX = xs;
			motionY = ys;
			motionZ = zs;
		} else {
			setDead();
		}
		
		super.onEntityUpdate();
	}

	@Override
	public void onImpact(RayTraceResult result)
	{
		
	}
}
