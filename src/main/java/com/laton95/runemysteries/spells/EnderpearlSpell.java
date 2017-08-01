package com.laton95.runemysteries.spells;

import java.util.ArrayList;

import com.laton95.runemysteries.init.ItemRegistry;

import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EnderpearlSpell extends Spell {

	public EnderpearlSpell() {
		costs.add(new SpellCost(ItemRegistry.LAW_RUNE, 3));
		cooldown = 20;
	}

	@Override
	public void fireSpell(World world, EntityPlayer player) {
		if (!world.isRemote) {
			EntityEnderPearl entityenderpearl = new EntityEnderPearl(world, player);
			entityenderpearl.setHeadingFromThrower(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
			world.spawnEntity(entityenderpearl);
		}
	}

}
