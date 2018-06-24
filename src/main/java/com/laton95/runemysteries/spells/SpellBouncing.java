package com.laton95.runemysteries.spells;

import com.google.common.collect.ImmutableList;
import com.laton95.runemysteries.entity.projectile.EntityProjectileSpellBase;
import com.laton95.runemysteries.entity.projectile.EntityProjectileSpellBouncing;
import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.reference.NamesReference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.List;

public class SpellBouncing extends SpellBase
{
	
	private final static List<SpellCost> costs = ImmutableList.of(
	
																 );
	
	public SpellBouncing()
	{
		super(costs, 20, NamesReference.Spells.BOUNCING_SPELL_NAME, NamesReference.Spells.BOUNCING_SPELL_DESCRIPTION, new ResourceLocation(ModReference.MOD_ID, "textures/spells/gui/explosion.png"));
	}
	
	@Override
	public void fireSpell(World world, EntityPlayer player)
	{
		EntityProjectileSpellBase projectile = new EntityProjectileSpellBouncing(world, player);
		putProjectileInWorld(world, player, projectile);
	}
	
	@Override
	public boolean canCast(World world, EntityPlayer player)
	{
		return true;
	}
	
}
