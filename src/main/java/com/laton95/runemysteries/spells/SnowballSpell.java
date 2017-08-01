package com.laton95.runemysteries.spells;

import java.util.ArrayList;

import com.laton95.runemysteries.init.ItemRegistry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class SnowballSpell extends Spell {

	public SnowballSpell() {
		costs.add(new SpellCost(ItemRegistry.WATER_RUNE, 2));
		costs.add(new SpellCost(ItemRegistry.AIR_RUNE, 1));
		cooldown = 5;
	}

	@Override
	public void fireSpell(World world, EntityPlayer player) {
		world.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_SNOWBALL_THROW,
				SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.rand.nextFloat() * 0.4F + 0.8F));

		if (!world.isRemote) {
			EntitySnowball entitysnowball = new EntitySnowball(world, player);
			entitysnowball.setHeadingFromThrower(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
			world.spawnEntity(entitysnowball);
		}
	}

}
