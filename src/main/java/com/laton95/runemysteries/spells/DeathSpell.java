package com.laton95.runemysteries.spells;

import com.google.common.collect.ImmutableList;
import com.laton95.runemysteries.entity.projectile.EntityProjectileSpellBase;
import com.laton95.runemysteries.entity.projectile.EntityProjectileSpellDamage;
import com.laton95.runemysteries.init.ModItems;
import com.laton95.runemysteries.item.ItemRune.EnumRuneType;
import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.reference.NamesReference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.List;

public class DeathSpell extends SpellBase
{
	
	private final static List<SpellCost> costs = ImmutableList.of(new SpellCost(ModItems.RUNE, 3, EnumRuneType.DEATH.ordinal()));
	
	public DeathSpell()
	{
		super(costs, 20, NamesReference.Spells.DEATH_SPELL_NAME, NamesReference.Spells.DEATH_SPELL_DESCRIPTION, new ResourceLocation(ModReference.MOD_ID, "textures/spells/gui/explosion.png"));
	}
	
	@Override
	public boolean fireSpell(World world, EntityPlayer player)
	{
		EntityProjectileSpellBase projectile = new EntityProjectileSpellDamage(world, player, 30);
		putProjectileInWorld(world, player, projectile);
		
		return true;
	}
	
}
