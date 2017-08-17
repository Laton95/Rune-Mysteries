package com.laton95.runemysteries.spells;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.laton95.runemysteries.init.ItemRegistry;
import com.laton95.runemysteries.item.ItemRune.EnumRuneType;
import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.reference.NamesReference;
import com.laton95.runemysteries.spells.projectiles.SpellProjectileBase;
import com.laton95.runemysteries.spells.projectiles.SpellProjectileBouncing;
import com.laton95.runemysteries.spells.projectiles.SpellProjectileDamage;
import com.laton95.runemysteries.spells.projectiles.SpellProjectileExplosive;
import com.laton95.runemysteries.spells.projectiles.SpellProjectileFollowing;
import com.laton95.runemysteries.util.LogHelper;

import jline.internal.Log;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class DeathSpell extends Spell {
	private final static List<SpellCost> costs = ImmutableList.of(
			new SpellCost(ItemRegistry.RUNE, 3, EnumRuneType.DEATH.ordinal())
			);
	
	public DeathSpell() {
		super(costs, 20, NamesReference.Spells.DEATH_SPELL_NAME, NamesReference.Spells.DEATH_SPELL_DESCRIPTION, new ResourceLocation(ModReference.MOD_ID, "textures/spells/gui/explosion.png"));
	}

	@Override
	public void fireSpell(World world, EntityPlayer player) {
//		SpellProjectileBase projectile = new SpellProjectileDamage(world, player, 30);
//		SpellProjectileBase projectile = new SpellProjectileBouncing(world, player);
		SpellProjectileBase projectile = new SpellProjectileExplosive(world, player);
		double radius = 20;
//		SpellProjectileBase projectile = new SpellProjectileFollowing(world, player, world.findNearestEntityWithinAABB(EntityLiving.class, new AxisAlignedBB(player.posX - radius, player.posY - radius, player.posZ - radius, player.posX + radius, player.posY + radius, player.posZ + radius), player));
		projectile.setHeadingFromThrower(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
		world.spawnEntity(projectile);
	}

}
