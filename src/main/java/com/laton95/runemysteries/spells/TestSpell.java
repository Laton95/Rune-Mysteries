package com.laton95.runemysteries.spells;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.laton95.runemysteries.init.ItemRegistry;
import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.reference.NamesReference;
import com.laton95.runemysteries.spells.Spell.SpellCost;

import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class TestSpell extends Spell {
	private final static List<SpellCost> costs = ImmutableList.of(
			new SpellCost(Items.IRON_CHESTPLATE, 1)
			);
	
	public TestSpell() {
		super(costs, 20, "Test Spell 1", "Test", new ResourceLocation(ModReference.MOD_ID, "textures/spells/gui/explosion.png"));
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
