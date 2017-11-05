package com.laton95.runemysteries.spells;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.laton95.runemysteries.entity.projectiles.SpellProjectileBase;
import com.laton95.runemysteries.entity.projectiles.SpellProjectileTeleportBasic;
import com.laton95.runemysteries.init.ItemRegistry;
import com.laton95.runemysteries.item.ItemRune.EnumRuneType;
import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.reference.NamesReference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class TeleportBasicSpell extends SpellBase
{

	private final static List<SpellCost> costs = ImmutableList.of(
			new SpellCost(ItemRegistry.RUNE, 3, EnumRuneType.LAW.ordinal()));

	public TeleportBasicSpell()
	{
		super(costs, 20, NamesReference.Spells.ENDERPEARL_SPELL_NAME, NamesReference.Spells.ENDERPEARL_SPELL_DESCRIPTION, new ResourceLocation(ModReference.MOD_ID, "textures/spells/gui/explosion.png"));
	}

	@Override
	public boolean fireSpell(World world, EntityPlayer player)
	{
		SpellProjectileBase projectile = new SpellProjectileTeleportBasic(world, player);
		putProjectileInWorld(world, player, projectile);
		
		return true;
	}

}
