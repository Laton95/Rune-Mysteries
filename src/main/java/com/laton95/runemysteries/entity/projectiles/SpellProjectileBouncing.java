package com.laton95.runemysteries.entity.projectiles;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class SpellProjectileBouncing extends SpellProjectileBase
{

	private int landedTick;

	public SpellProjectileBouncing(World worldIn, EntityLivingBase throwerIn)
	{
		super(worldIn, throwerIn);
	}

	@Override
	public void onImpact(RayTraceResult result)
	{

		if (landedTick > 200 || ticksExisted > 100)
		{
			setDead();
		}
		if (Math.abs(motionY) < 0.1)
		{
			landedTick++;
			motionY = 0;
			motionZ = 0;
			motionX = 0;
		}
		else
		{
			// landedTick = 0;
			double yDecay = 1.4;
			double hBoost = 1.1;
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
						motionY = -motionY / yDecay;
						break;
					case WEST:
						motionX = -motionX * hBoost;
						break;
					default:
						break;
				}
			}
			else
			{
				motionY = -motionY / yDecay;
				motionX = -motionX * hBoost;
				motionZ = -motionZ * hBoost;

				if (!world.isRemote)
				{
					if (result.entityHit != null)
					{
						result.entityHit.attackEntityFrom(
								new EntityDamageSourceIndirect("runeMagicDamageProjectile", this, getThrower()).setProjectile(),
								30);
					}
				}
			}
			if (!world.isRemote)
			{
				world.createExplosion(this, posX, posY, posZ, 10, true);
			}

			super.onImpact(result);
		}
	}
}
