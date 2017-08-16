package com.laton95.runemysteries.spells;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.laton95.runemysteries.init.ItemRegistry;
import com.laton95.runemysteries.item.ItemRune.EnumRuneType;
import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.reference.NamesReference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ExplosionSpell extends Spell {
	private final static List<SpellCost> costs = ImmutableList.of(
			new SpellCost(Items.GUNPOWDER, 1),
			new SpellCost(ItemRegistry.RUNE, 2, EnumRuneType.FIRE.ordinal())
			);
	
	public ExplosionSpell() {
		super(costs, 40, NamesReference.Spells.EXPLOSION_SPELL_NAME, NamesReference.Spells.EXPLOSION_SPELL_DESCRIPTION, new ResourceLocation(ModReference.MOD_ID, "textures/spells/gui/explosion.png"));
	}

	@Override
	public void fireSpell(World world, EntityPlayer player) {
		if (!world.isRemote) {
			world.createExplosion(player, player.posX, player.posY + (double)(player.height / 16.0F), player.posZ, 4.0F, false);
		}
	}

}
