package com.laton95.runemysteries.item;

import java.util.ArrayList;
import java.util.List;

import com.laton95.runemysteries.inventory.InventoryRuneBag;
import com.laton95.runemysteries.reference.NamesReference;
import com.laton95.runemysteries.spells.DeathSpell;
import com.laton95.runemysteries.spells.EnderpearlSpell;
import com.laton95.runemysteries.spells.ExplosionSpell;
import com.laton95.runemysteries.spells.SnowballSpell;
import com.laton95.runemysteries.spells.Spell;
import com.laton95.runemysteries.spells.Spells;
import com.laton95.runemysteries.spells.projectiles.DamageProjectile;
import com.laton95.runemysteries.util.LogHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import scala.swing.event.BackgroundChanged;

public class ItemSpellbook extends RMModItem {

	public ItemSpellbook() {
		super("spellbook", true);
		setMaxStackSize(1);
	}

	private boolean hasItems(EntityPlayer player, ArrayList<Spell.SpellCost> costs, boolean hasBag, NonNullList<ItemStack> bagContents) {
		ArrayList<Spell.SpellCost> tempCosts = new ArrayList<>(costs);
		
		for (Spell.SpellCost spellCost : costs) {
			if (hasItem(player, spellCost, hasBag, bagContents)) {
				tempCosts.remove(spellCost);
			}
		}
		if (tempCosts.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	private boolean hasItem(EntityPlayer player, Spell.SpellCost cost, boolean hasBag, NonNullList<ItemStack> bagContents) {
		int count = 0;
		if (hasBag && cost.getItem() instanceof ItemRune) {
			for (ItemStack itemStack : bagContents) {
				if (itemStack.getItem().equals(cost.getItem())) {
					count += itemStack.getCount();
				}
			}
			
			if (count >= cost.getCount()) {
				return true;
			}
		}
		
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
	
	private ItemStack getRuneBag(EntityPlayer player) {
		for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
			if (player.inventory.getStackInSlot(i).getItem() instanceof ItemRuneBag) {
				return player.inventory.getStackInSlot(i);
			}
		}
		return null;
	}

	private void removeItems(EntityPlayer player, ArrayList<Spell.SpellCost> costs, boolean hasBag, NonNullList<ItemStack> bagContents) {
		for (Spell.SpellCost spellCost : costs) {
			removeItem(player, spellCost, hasBag, bagContents);
		}
	}

	private void removeItem(EntityPlayer player, Spell.SpellCost cost, boolean hasBag, NonNullList<ItemStack> bagContents) {
		int count = cost.getCount();
		
		if (hasBag && cost.getItem() instanceof ItemRune) {
			int i = 0;
			while (count > 0 && i < bagContents.size()) {
				ItemStack itemstack = bagContents.get(i);
				if (itemstack.getItem().equals(cost.getItem())) {
					int temp = count;
					count -= itemstack.getCount();
					itemstack.shrink(temp);
				}
				i++;
			}
			
			if (count <= 0) {
				return;
			}
		}
		
		int j = 0;
		LogHelper.info(count);
		while (count > 0 && j < player.inventory.getSizeInventory()) {
			ItemStack itemstack = player.inventory.getStackInSlot(j);
			if (itemstack.getItem().equals(cost.getItem())) {
				int temp = count;
				count -= itemstack.getCount();
				itemstack.shrink(temp);
			}
			j++;
		}
		
		if (count > 0) {
			LogHelper.info("Error: Spell cost not fully paid");
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
			ItemStack bag = getRuneBag(playerIn);
			NonNullList<ItemStack> bagContents = NonNullList.<ItemStack>withSize(InventoryRuneBag.INVENTORY_SIZE, ItemStack.EMPTY);
			if (bag != null) {
				ItemStackHelper.loadAllItems(bag.getTagCompound(), bagContents);
			}
			
			if (hasItems(playerIn, spell.getCosts(), bag != null, bagContents)) {
				removeItems(playerIn, spell.getCosts(), bag != null, bagContents);
				spell.fireSpell(worldIn, playerIn);
				playerIn.getCooldownTracker().setCooldown(this, spell.getCooldown());
				
				if (bag != null) {
					ItemStackHelper.saveAllItems(bag.getTagCompound(), bagContents);
				}
				
				return new ActionResult<>(EnumActionResult.SUCCESS, spellbook);
			} else {
				if (!worldIn.isRemote) {
					playerIn.sendMessage(new TextComponentTranslation(NamesReference.Spellbook.NO_RUNES));
				}
				return new ActionResult<>(EnumActionResult.FAIL, spellbook);
			}
		}
	}
}
