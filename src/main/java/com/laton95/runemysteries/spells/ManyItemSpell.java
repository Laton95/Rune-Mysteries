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

public class ManyItemSpell extends Spell {
	private final static List<SpellCost> costs = ImmutableList.of(
			new SpellCost(ItemRegistry.AIR_RUNE, 1),
			new SpellCost(ItemRegistry.ASTRAL_RUNE, 7),
			new SpellCost(ItemRegistry.BLOOD_RUNE, 8),
			new SpellCost(ItemRegistry.BODY_RUNE, 2),
			new SpellCost(ItemRegistry.CHAOS_RUNE, 5),
			new SpellCost(ItemRegistry.COSMIC_RUNE, 1),
			new SpellCost(ItemRegistry.DEATH_RUNE, 5),
			new SpellCost(ItemRegistry.EARTH_RUNE, 5),
			new SpellCost(ItemRegistry.FIRE_RUNE, 5),
			new SpellCost(ItemRegistry.LAW_RUNE, 5),
			new SpellCost(ItemRegistry.MIND_RUNE, 5),
			new SpellCost(ItemRegistry.NATURE_RUNE, 5),
			new SpellCost(ItemRegistry.SOUL_RUNE, 5),
			new SpellCost(ItemRegistry.WATER_RUNE, 5),
			new SpellCost(ItemRegistry.AIR_TALISMAN, 5),
			new SpellCost(ItemRegistry.ASTRAL_TALISMAN, 5),
			new SpellCost(ItemRegistry.BLOOD_TALISMAN, 5),
			new SpellCost(ItemRegistry.BODY_TALISMAN, 5),
			new SpellCost(ItemRegistry.CHAOS_TALISMAN, 5),
			new SpellCost(ItemRegistry.COSMIC_TALISMAN, 5),
			new SpellCost(ItemRegistry.DEATH_TALISMAN, 5),
			new SpellCost(ItemRegistry.EARTH_TALISMAN, 5),
			new SpellCost(ItemRegistry.FIRE_TALISMAN, 5),
			new SpellCost(ItemRegistry.LAW_TALISMAN, 5),
			new SpellCost(ItemRegistry.MIND_TALISMAN, 5),
			new SpellCost(ItemRegistry.NATURE_TALISMAN, 5),
			new SpellCost(ItemRegistry.SOUL_TALISMAN, 5),
			new SpellCost(ItemRegistry.WATER_TALISMAN, 5),
			new SpellCost(Items.APPLE, 5),
			new SpellCost(Items.IRON_CHESTPLATE, 1)
			);
	
	public ManyItemSpell() {
		super(costs, 20, "Test Spell 3", "Test", new ResourceLocation(ModReference.MOD_ID, "textures/spells/gui/explosion.png"));
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
