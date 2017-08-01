package com.laton95.runemysteries.spells;

import java.util.ArrayList;

import com.laton95.runemysteries.init.ItemRegistry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ExplosionSpell extends Spell {

	public ExplosionSpell() {
		costs.add(new SpellCost(Items.GUNPOWDER, 2));
		cooldown = 40;
	}

	@Override
	public void fireSpell(World world, EntityPlayer player) {
		if (!world.isRemote) {
			world.createExplosion(player, player.posX, player.posY + (double)(player.height / 16.0F), player.posZ, 4.0F, false);
		}
	}

}
