package com.laton95.runemysteries.spells;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public abstract class Spell {
	protected List<Spell.SpellCost> costs = new ArrayList<Spell.SpellCost>();;
	protected int cooldown = 0;
	protected String name;

	public Spell(List<Spell.SpellCost> costs, int cooldown, String name) {
		this.costs = costs;
		this.cooldown = cooldown;
		this.name = name;
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
