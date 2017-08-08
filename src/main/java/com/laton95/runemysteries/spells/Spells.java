package com.laton95.runemysteries.spells;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.laton95.runemysteries.init.ItemRegistry;
import com.laton95.runemysteries.spells.Spell.SpellCost;
import com.laton95.runemysteries.util.LogHelper;

import net.minecraft.item.Item;

public class Spells {
	public enum EnumSpell {
		ENDERPEARL_SPELL, SNOWBALL_SPELL, EXPLOSION_SPELL, DEATH_SPELL, NONE
	}
	
	public static final EnderpearlSpell ENDERPEARL_SPELL = new EnderpearlSpell();
	public static final SnowballSpell SNOWBALL_SPELL = new SnowballSpell();
	public static final ExplosionSpell EXPLOSION_SPELL = new ExplosionSpell();
	public static final DeathSpell DEATH_SPELL = new DeathSpell();
	
	private final static List<Spell> SPELL_LIST = ImmutableList.of(
			ENDERPEARL_SPELL, SNOWBALL_SPELL, EXPLOSION_SPELL, DEATH_SPELL
			);
	
	public static Spell getSpellFromEnum(EnumSpell enumSpell) {
		switch (enumSpell) {
		case ENDERPEARL_SPELL:
			return ENDERPEARL_SPELL;
		case SNOWBALL_SPELL:
			return SNOWBALL_SPELL;
		case EXPLOSION_SPELL:
			return EXPLOSION_SPELL;
		case DEATH_SPELL:
			return DEATH_SPELL;
		default:
			return null;
		}
	}
	
	public static void checkSpells() {
		for (Spell spell : SPELL_LIST) {
			List<Item> items = new ArrayList<>();
			for (SpellCost spellCost : spell.getCosts()) {
				if (items.contains(spellCost.getItem())) {
					throw new IllegalArgumentException("Spell has multiple costs with the same item: " + spell.getName());
				}
				items.add(spellCost.getItem());
				LogHelper.info(spellCost.getItem().getRegistryName());
			}
		}
	}
}
