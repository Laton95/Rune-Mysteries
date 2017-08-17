package com.laton95.runemysteries.entity.projectiles;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class SpellProjectileExplosive extends SpellProjectileBase
{

	private final int power;

	public SpellProjectileExplosive(World worldIn, EntityLivingBase throwerIn, int power)
	{
		super(worldIn, throwerIn);
		this.power = power;
	}

	@Override
	public void onImpact(RayTraceResult result)
	{
		if (!world.isRemote)
		{
			world.createExplosion(getThrower(), posX, posY, posZ, power, true);
			setDead();
		}

		super.onImpact(result);
	}
}
