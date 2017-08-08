package com.laton95.runemysteries.spells;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.laton95.runemysteries.init.ItemRegistry;
import com.laton95.runemysteries.reference.NamesReference;
import com.laton95.runemysteries.spells.Spell.SpellCost;

import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EnderpearlSpell extends Spell {
	private final static List<SpellCost> costs = ImmutableList.of(
			new SpellCost(ItemRegistry.LAW_RUNE, 3)
			);
	
	public EnderpearlSpell() {
		super(costs, 20, NamesReference.Spells.ENDERPEARL_SPELL_NAME, NamesReference.Spells.ENDERPEARL_SPELL_DESCRIPTION);
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
