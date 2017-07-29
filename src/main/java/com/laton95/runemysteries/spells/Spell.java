package com.laton95.runemysteries.spells;

import java.util.ArrayList;

import com.laton95.runemysteries.item.ItemRune;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public abstract class Spell {
	protected ArrayList<Spell.SpellCost> costs = new ArrayList<>();;
	protected int cooldown = 0;

	public Spell() {
	}

	public Spell(ArrayList<Spell.SpellCost> costs, int cooldown) {
		this.costs = costs;
		this.cooldown = cooldown;
	}

	public abstract void fireSpell(World world, EntityPlayer player);

	public ArrayList<Spell.SpellCost> getCosts() {
		return costs;
	}

	public int getCooldown() {
		return cooldown;
	}

	public class SpellCost {
		private final ItemRune rune;
		private final int count;

		public SpellCost(ItemRune rune, int count) {
			this.rune = rune;
			this.count = count;
		}

		public ItemRune getRune() {
			return rune;
		}

		public int getCount() {
			return count;
		}
	}
}
