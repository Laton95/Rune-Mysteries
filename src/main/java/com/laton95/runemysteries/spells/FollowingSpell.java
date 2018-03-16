package com.laton95.runemysteries.spells;

import com.google.common.collect.ImmutableList;
import com.laton95.runemysteries.entity.projectile.EntityProjectileSpellBase;
import com.laton95.runemysteries.entity.projectile.EntityProjectileSpellFollowing;
import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.reference.NamesReference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.List;

public class FollowingSpell extends SpellBase
{
	
	private final static List<SpellCost> costs = ImmutableList.of(
	
	);
	
	public FollowingSpell()
	{
		super(costs, 20, NamesReference.Spells.FOLLOWING_SPELL_NAME, NamesReference.Spells.FOLLOWING_SPELL_DESCRIPTION, new ResourceLocation(ModReference.MOD_ID, "textures/spells/gui/explosion.png"));
	}
	
	@Override
	public boolean fireSpell(World world, EntityPlayer player)
	{
		double radius = 20;
		RayTraceResult test = player.rayTrace(200, 1.0f);
		Entity target = null;
		if (test != null)
		{
			target = world.findNearestEntityWithinAABB(EntityLiving.class, new AxisAlignedBB(test.hitVec.x - radius, test.hitVec.y - radius, test.hitVec.z - radius, test.hitVec.x + radius, test.hitVec.y + radius, test.hitVec.z + radius), player);
		}
		
		if (target != null)
		{
			EntityProjectileSpellBase projectile = new EntityProjectileSpellFollowing(world, player, target);
			putProjectileInWorld(world, player, projectile);
			return true;
		} else
		{
			return false;
		}
	}
}
