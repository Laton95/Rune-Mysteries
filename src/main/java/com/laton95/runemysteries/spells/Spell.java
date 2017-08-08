package com.laton95.runemysteries.spells;

import java.util.ArrayList;
import java.util.List;

import com.laton95.runemysteries.util.LogHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public abstract class Spell {
	protected final List<Spell.SpellCost> costs;
	protected final int cooldown;
	protected final String name;
	protected final String description;

	public Spell(List<Spell.SpellCost> costs, int cooldown, String name, String description) {
		this.costs = costs;
		this.cooldown = cooldown;
		this.name = name;
		this.description = description;
	}

	public abstract void fireSpell(World world, EntityPlayer player);

	public List<Spell.SpellCost> getCosts() {
		return costs;
	}

	public int getCooldown() {
		return cooldown;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}

	public static class SpellCost {
		private final Item item;
		private final int count;

		public SpellCost(Item item, int count) {
			this.item = item;
			this.count = count;
		}

		public Item getItem() {
			return item;
		}

		public int getCount() {
			return count;
		}
	}
}
