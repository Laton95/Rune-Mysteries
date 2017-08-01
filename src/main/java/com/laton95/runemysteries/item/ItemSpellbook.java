package com.laton95.runemysteries.item;

import java.util.ArrayList;

import com.laton95.runemysteries.spells.DeathSpell;
import com.laton95.runemysteries.spells.EnderpearlSpell;
import com.laton95.runemysteries.spells.ExplosionSpell;
import com.laton95.runemysteries.spells.SnowballSpell;
import com.laton95.runemysteries.spells.Spell;
import com.laton95.runemysteries.spells.Spells;
import com.laton95.runemysteries.spells.projectiles.DamageProjectile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemSpellbook extends RMModItem {

	public ItemSpellbook() {
		super("spellbook", true);
		setMaxStackSize(1);
	}

	private boolean hasItems(EntityPlayer player, ArrayList<Spell.SpellCost> costs) {
		ArrayList<Spell.SpellCost> tempCosts = new ArrayList<>(costs);
		for (Spell.SpellCost spellCost : costs) {
			if (hasItem(player, spellCost)) {
				tempCosts.remove(spellCost);
			}
		}
		if (tempCosts.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	private boolean hasItem(EntityPlayer player, Spell.SpellCost cost) {
		int count = 0;
		for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
			ItemStack itemstack = player.inventory.getStackInSlot(i);
			if (itemstack.getItem().equals(cost.getItem())) {
				count += itemstack.getCount();
			}
		}
		if (count >= cost.getCount()) {
			return true;
		} else {
			return false;
		}
	}

	private void removeItems(EntityPlayer player, ArrayList<Spell.SpellCost> costs) {
		for (Spell.SpellCost spellCost : costs) {
			removeItem(player, spellCost);
		}
	}

	private void removeItem(EntityPlayer player, Spell.SpellCost cost) {
		int count = cost.getCount();
		int i = 0;
		while (count > 0) {
			ItemStack itemstack = player.inventory.getStackInSlot(i);
			if (itemstack.getItem().equals(cost.getItem())) {
				int temp = count;
				count -= itemstack.getCount();
				itemstack.shrink(temp);
			}
			i++;
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack spellbook = playerIn.getHeldItem(handIn);
		Spell spell = Spells.DEATH_SPELL;

		if (playerIn.isCreative()) {
			spell.fireSpell(worldIn, playerIn);
			playerIn.getCooldownTracker().setCooldown(this, spell.getCooldown());
			return new ActionResult<>(EnumActionResult.SUCCESS, spellbook);
		} else {
			if (hasItems(playerIn, spell.getCosts())) {
				removeItems(playerIn, spell.getCosts());
				spell.fireSpell(worldIn, playerIn);
				playerIn.getCooldownTracker().setCooldown(this, spell.getCooldown());
				return new ActionResult<>(EnumActionResult.SUCCESS, spellbook);
			} else {
				if (!worldIn.isRemote) {
					playerIn.sendMessage(new TextComponentTranslation("item.runemysteries.spellbook.norunes"));
				}
				return new ActionResult<>(EnumActionResult.FAIL, spellbook);
			}
		}
	}
}
