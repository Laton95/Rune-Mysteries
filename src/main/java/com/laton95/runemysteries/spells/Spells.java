package com.laton95.runemysteries.spells;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.laton95.runemysteries.reference.NamesReference;
import com.laton95.runemysteries.spells.SpellBase.SpellCost;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Spells
{

	public static final SpellBase NONE_SPELL = new SpellBase(new ArrayList<>(), 0, NamesReference.Spells.NO_SPELL_NAME,
			NamesReference.Spells.NO_SPELL_DESCRIPTION, null)
	{

		@Override
		public boolean fireSpell(World world, EntityPlayer player)
		{
			return false;
		}
	};

	public static List<SpellBase> spellList = ImmutableList.of(new TeleportBasicSpell(), new SnowballSpell(),
			new ExplosionSpell(), new DeathSpell(), new BouncingSpell(), new FollowingSpell());

	public SpellBase registerSpell(SpellBase spell)
	{
		spellList.add(spell);
		return spell;
	}

	public static void checkSpells()
	{
		for (SpellBase spell : spellList)
		{
			List<ItemStack> items = new ArrayList<>();
			for (SpellCost spellCost : spell.getCosts())
			{
				for (ItemStack itemStack : items)
				{
					if (itemStack.getItem() == spellCost.getItem()
							&& spellCost.getMetadata() == itemStack.getItemDamage())
					{
						throw new IllegalArgumentException(
								"Spell has multiple costs with the same item: " + spell.getName());
					}
				}
				items.add(new ItemStack(spellCost.getItem(), 1, spellCost.getMetadata()));
			}
			if (spell.getCosts().size() > 40 + 14)
			{
				throw new IllegalArgumentException(
						"Spell is uncastable, too many costs to fit in player inventory: " + spell.getName());
			}
		}
	}
}
