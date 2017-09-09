package com.laton95.runemysteries.spells;

import java.util.List;

import com.laton95.runemysteries.entity.projectiles.SpellProjectileBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public abstract class SpellBase
{

	protected final List<SpellBase.SpellCost> costs;
	protected final int cooldown;
	protected final String name;
	protected final String description;
	protected final ResourceLocation guiTexture;

	public SpellBase(List<SpellBase.SpellCost> costs, int cooldown, String name, String description,
			ResourceLocation guiTexture)
	{
		this.costs = costs;
		this.cooldown = cooldown;
		this.name = name;
		this.description = description;
		this.guiTexture = guiTexture;
	}

	public abstract boolean fireSpell(World world, EntityPlayer player);

	protected void putProjectileInWorld(World world, EntityPlayer player, SpellProjectileBase spellProjectile)
	{
		spellProjectile.setHeadingFromThrower(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
		world.spawnEntity(spellProjectile);
	}

	public List<SpellBase.SpellCost> getCosts()
	{
		return costs;
	}

	public int getCooldown()
	{
		return cooldown;
	}

	public String getName()
	{
		return name;
	}

	public String getDescription()
	{
		return description;
	}

	public ResourceLocation getGuiTexture()
	{
		return guiTexture;
	}

	public static class SpellCost
	{

		private final Item item;
		private final int count;
		private final int damage;
		private final boolean useMetadata;

		public SpellCost(Item item, int count, int damage)
		{
			this.item = item;
			this.count = count;
			this.damage = damage;
			useMetadata = true;
		}

		public SpellCost(Item item, int count)
		{
			this.item = item;
			this.count = count;
			damage = 0;
			useMetadata = false;
		}

		public Item getItem()
		{
			return item;
		}

		public int getCount()
		{
			return count;
		}

		public int getMetadata()
		{
			return damage;
		}

		public boolean usesMetadata()
		{
			return useMetadata;
		}
	}
}
