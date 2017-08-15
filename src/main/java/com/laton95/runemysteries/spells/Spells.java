package com.laton95.runemysteries.spells;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.laton95.runemysteries.init.ItemRegistry;
import com.laton95.runemysteries.inventory.InventoryRuneBag;
import com.laton95.runemysteries.reference.NamesReference;
import com.laton95.runemysteries.spells.Spell.SpellCost;
import com.laton95.runemysteries.util.LogHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.event.EntityViewRenderEvent.CameraSetup;

public class Spells {
	public static final Spell NONE_SPELL = new Spell(new ArrayList<>(), 0, NamesReference.Spells.NO_SPELL_NAME, NamesReference.Spells.NO_SPELL_DESCRIPTION, null) {	
		@Override
		public void fireSpell(World world, EntityPlayer player) {
		}
	};
	
	public static List<Spell> spellList = ImmutableList.of(
			new EnderpearlSpell(),
			new SnowballSpell(),
			new ExplosionSpell(),
			new DeathSpell(),
			new TestSpell(),
			new SixItemSpell(),
			new ManyItemSpell(),
			new TalismanSpell()
			);
	
	public Spell registerSpell(Spell spell) {
		spellList.add(spell);
		return spell;
	}
	
	public static void checkSpells() {
		for (Spell spell : spellList) {
			List<ItemStack> items = new ArrayList<>();
			for (SpellCost spellCost : spell.getCosts()) {
				for (ItemStack itemStack : items) {
					if (itemStack.getItem() == spellCost.getItem() && (spellCost.getMetadata() == itemStack.getItemDamage())) {
						throw new IllegalArgumentException("Spell has multiple costs with the same item: " + spell.getName());
					}
				}
				items.add(new ItemStack(spellCost.getItem(), 1, spellCost.getMetadata()));
			}
			if (spell.getCosts().size() > 40 + 14) {
				throw new IllegalArgumentException("Spell is uncastable, too many costs to fit in player inventory: " + spell.getName());
			}
		}
	}
}
