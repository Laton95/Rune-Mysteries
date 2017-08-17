package com.laton95.runemysteries.spells.projectiles;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class SpellProjectileExplosive extends SpellProjectileBase {

	public SpellProjectileExplosive(World worldIn, EntityLivingBase throwerIn) {
		super(worldIn, throwerIn);
	}

	@Override
	public void onImpact(RayTraceResult result) {
		if (!world.isRemote) {
			world.createExplosion(this.getThrower(), this.posX, this.posY, this.posZ, 2, true);
			this.setDead();
		}
		
		super.onImpact(result);
	}
}
