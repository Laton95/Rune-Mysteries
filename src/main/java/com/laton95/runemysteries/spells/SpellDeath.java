package com.laton95.runemysteries.spells;

import com.google.common.collect.ImmutableList;
import com.laton95.runemysteries.entity.projectile.EntityProjectileSpellBase;
import com.laton95.runemysteries.entity.projectile.EntityProjectileSpellDamage;
import com.laton95.runemysteries.init.ModItems;
import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.reference.NamesReference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.List;

public class SpellDeath extends SpellBase
{
	
	private final static List<SpellCost> costs = ImmutableList.of
			(
					new SpellCost(ModItems.DEATH_RUNE, 3)
			);
	
	public SpellDeath()
	{
		super(costs, 20, NamesReference.Spells.DEATH_SPELL_NAME, NamesReference.Spells.DEATH_SPELL_DESCRIPTION, new ResourceLocation(ModReference.MOD_ID, "textures/spells/gui/explosion.png"));
	}
	
	@Override
	public void fireSpell(World world, EntityPlayer player)
	{
		EntityProjectileSpellBase projectile = new EntityProjectileSpellDamage(world, player, 30);
		putProjectileInWorld(world, player, projectile);
	}
	
	@Override
	public boolean canCast(World world, EntityPlayer player)
	{
		return true;
	}
	
}
