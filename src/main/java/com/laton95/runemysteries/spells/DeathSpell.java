package com.laton95.runemysteries.spells;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.laton95.runemysteries.init.ItemRegistry;
import com.laton95.runemysteries.reference.NamesReference;
import com.laton95.runemysteries.spells.projectiles.DamageProjectile;

import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;

public class DeathSpell extends Spell {
	private final static List<SpellCost> costs = ImmutableList.of(
			new SpellCost(ItemRegistry.DEATH_RUNE, 3)
			);

	public DeathSpell() {
		super(costs, 20, NamesReference.Spells.DEATH_SPELL_NAME, NamesReference.Spells.DEATH_SPELL_DESCRIPTION);
	}

	@Override
	public void fireSpell(World world, EntityPlayer player) {
		if (!world.isRemote) {
			DamageProjectile entityenderpearl = new DamageProjectile(world, player,10);
			entityenderpearl.setHeadingFromThrower(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
			world.spawnEntity(entityenderpearl);
		}
	}

}
