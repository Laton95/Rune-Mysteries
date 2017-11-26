package com.laton95.runemysteries.entity.projectiles;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SpellProjectileBouncing extends SpellProjectileBase
{
	public SpellProjectileBouncing(World worldIn)
	{
		super(worldIn);
	}
	
	public SpellProjectileBouncing(World worldIn, EntityLivingBase throwerIn)
	{
		super(worldIn, throwerIn);
	}
	
	@SideOnly(Side.CLIENT)
	public SpellProjectileBouncing(World worldIn, double x, double y, double z)
	{
		super(worldIn, x, y, z);
	}

	@Override
	public void onImpact(RayTraceResult result)
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
		}
		else
		{
			motionY = Math.abs(motionY) * yDecay + result.entityHit.motionY;
			motionX = -motionX * hBoost + result.entityHit.motionX;
			motionZ = -motionZ * hBoost + result.entityHit.motionZ;
		}

		if (ticksExisted > 2000)
		{
			setDead();
		}

		super.onImpact(result);
	}
}
