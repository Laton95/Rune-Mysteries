package com.laton95.runemysteries.spells;

import java.util.ArrayList;

import com.laton95.runemysteries.init.ItemRegistry;
import com.laton95.runemysteries.spells.projectiles.DamageProjectile;

import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class DeathSpell extends Spell {

	public DeathSpell() {
		costs.add(new SpellCost(ItemRegistry.LAW_RUNE, 3));
		cooldown = 20;
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
