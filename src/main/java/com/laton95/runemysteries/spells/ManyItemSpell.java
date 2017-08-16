package com.laton95.runemysteries.spells;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.laton95.runemysteries.init.ItemRegistry;
import com.laton95.runemysteries.item.ItemRune.EnumRuneType;
import com.laton95.runemysteries.reference.ModReference;

import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ManyItemSpell extends Spell {
	private final static List<SpellCost> costs = ImmutableList.of(
			new SpellCost(ItemRegistry.RUNE, 1, EnumRuneType.AIR.ordinal()),
			new SpellCost(ItemRegistry.RUNE, 7, EnumRuneType.ASTRAL.ordinal()),
			new SpellCost(ItemRegistry.RUNE, 8, EnumRuneType.BLOOD.ordinal()),
			new SpellCost(ItemRegistry.RUNE, 2, EnumRuneType.BODY.ordinal()),
			new SpellCost(ItemRegistry.RUNE, 5, EnumRuneType.CHAOS.ordinal()),
			new SpellCost(ItemRegistry.RUNE, 1, EnumRuneType.COSMIC.ordinal()),
			new SpellCost(ItemRegistry.RUNE, 5, EnumRuneType.DEATH.ordinal()),
			new SpellCost(ItemRegistry.RUNE, 5, EnumRuneType.EARTH.ordinal()),
			new SpellCost(ItemRegistry.RUNE, 5, EnumRuneType.FIRE.ordinal()),
			new SpellCost(ItemRegistry.RUNE, 5, EnumRuneType.LAW.ordinal()),
			new SpellCost(ItemRegistry.RUNE, 5, EnumRuneType.MIND.ordinal()),
			new SpellCost(ItemRegistry.RUNE, 5, EnumRuneType.NATURE.ordinal()),
			new SpellCost(ItemRegistry.RUNE, 5, EnumRuneType.SOUL.ordinal()),
			new SpellCost(ItemRegistry.RUNE, 5, EnumRuneType.WATER.ordinal()),
			new SpellCost(ItemRegistry.RUNE_TALISMAN, 5, EnumRuneType.AIR.ordinal()),
			new SpellCost(ItemRegistry.RUNE_TALISMAN, 5, EnumRuneType.ASTRAL.ordinal()),
			new SpellCost(ItemRegistry.RUNE_TALISMAN, 5, EnumRuneType.BLOOD.ordinal()),
			new SpellCost(ItemRegistry.RUNE_TALISMAN, 5, EnumRuneType.BODY.ordinal()),
			new SpellCost(ItemRegistry.RUNE_TALISMAN, 5, EnumRuneType.CHAOS.ordinal()),
			new SpellCost(ItemRegistry.RUNE_TALISMAN, 5, EnumRuneType.COSMIC.ordinal()),
			new SpellCost(ItemRegistry.RUNE_TALISMAN, 5, EnumRuneType.DEATH.ordinal()),
			new SpellCost(ItemRegistry.RUNE_TALISMAN, 5, EnumRuneType.EARTH.ordinal()),
			new SpellCost(ItemRegistry.RUNE_TALISMAN, 5, EnumRuneType.FIRE.ordinal()),
			new SpellCost(ItemRegistry.RUNE_TALISMAN, 5, EnumRuneType.LAW.ordinal()),
			new SpellCost(ItemRegistry.RUNE_TALISMAN, 5, EnumRuneType.MIND.ordinal()),
			new SpellCost(ItemRegistry.RUNE_TALISMAN, 5, EnumRuneType.NATURE.ordinal()),
			new SpellCost(ItemRegistry.RUNE_TALISMAN, 5, EnumRuneType.SOUL.ordinal()),
			new SpellCost(ItemRegistry.RUNE_TALISMAN, 5, EnumRuneType.WATER.ordinal()),
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
