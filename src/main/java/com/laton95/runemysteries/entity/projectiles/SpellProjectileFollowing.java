package com.laton95.runemysteries.entity.projectiles;

import com.laton95.runemysteries.util.WorldHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SpellProjectileFollowing extends SpellProjectileBase
{
	private Entity targetEntity;

	public SpellProjectileFollowing(World worldIn)
	{
		super(worldIn);
	}
	
	public SpellProjectileFollowing(World worldIn, EntityLivingBase throwerIn, Entity targetEntity)
	{
		super(worldIn, throwerIn);
		this.targetEntity = targetEntity;
	}
	
	@SideOnly(Side.CLIENT)
	public SpellProjectileFollowing(World worldIn, double x, double y, double z)
	{
		super(worldIn, x, y, z);
	}

	@Override
	public void onUpdate()
	{
		if (targetEntity.isDead)
		{
			setDead();
		}

		if (WorldHelper.isNearby(new BlockPos(this), new BlockPos(targetEntity), 1))
		{
			targetEntity.attackEntityFrom(
					new EntityDamageSourceIndirect("runeMagicDamageProjectile", this, getThrower()).setProjectile(), 1);
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
			double theta2 = Math.PI / 2 - theta;
			double thetaY = Math.asin(y / l2);
			double zs = Math.copySign(followSpeed * Math.sin(theta), z);
			double xs = Math.copySign(followSpeed * Math.sin(theta2), x);
			double ys = Math.copySign(followSpeed * Math.sin(thetaY), y);
			motionX = xs;
			motionY = ys;
			motionZ = zs;
		}
		else
		{
			setDead();
		}

		super.onUpdate();
	}

	@Override
	public void onImpact(RayTraceResult result)
	{

	}
}
