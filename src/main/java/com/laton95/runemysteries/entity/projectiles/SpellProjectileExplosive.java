package com.laton95.runemysteries.entity.projectiles;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class SpellProjectileExplosive extends SpellProjectileBase
{

	private int power = 0;

	public SpellProjectileExplosive(World worldIn)
	{
		super(worldIn);
	}
	
	public SpellProjectileExplosive(World worldIn, EntityLivingBase throwerIn)
	{
		super(worldIn, throwerIn);
	}
	
	public SpellProjectileExplosive(World worldIn, double x, double y, double z)
	{
		super(worldIn, x, y, z);
	}

	@Override
	public void onImpact(RayTraceResult result)
	{
		if (!world.isRemote)
		{
			world.createExplosion(getThrower(), posX, posY, posZ, power, true);
		}
		
		super.onImpact(result);
	}
	
	public void setExplosivePower(int power) {
		this.power = power;
	}
}
