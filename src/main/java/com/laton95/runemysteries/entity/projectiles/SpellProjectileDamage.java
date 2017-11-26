package com.laton95.runemysteries.entity.projectiles;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SpellProjectileDamage extends SpellProjectileBase
{

	private final int damage;

	public SpellProjectileDamage(World worldIn)
	{
		super(worldIn);
		damage = 0;
	}
	
	public SpellProjectileDamage(World worldIn, EntityLivingBase throwerIn, int damage)
	{
		super(worldIn, throwerIn);
		this.damage = damage;
	}
	
	@SideOnly(Side.CLIENT)
	public SpellProjectileDamage(World worldIn, double x, double y, double z)
	{
		super(worldIn, x, y, z);
		damage = 0;
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
						damage);
			}
			setDead();
		}
		super.onImpact(result);
	}
}
