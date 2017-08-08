package com.laton95.runemysteries.item;

import java.util.ArrayList;
import java.util.List;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.GuiHandler.GuiIDs;
import com.laton95.runemysteries.inventory.InventoryRuneBag;
import com.laton95.runemysteries.reference.NamesReference;
import com.laton95.runemysteries.spells.DeathSpell;
import com.laton95.runemysteries.spells.EnderpearlSpell;
import com.laton95.runemysteries.spells.ExplosionSpell;
import com.laton95.runemysteries.spells.SnowballSpell;
import com.laton95.runemysteries.spells.Spell;
import com.laton95.runemysteries.spells.Spells;
import com.laton95.runemysteries.spells.Spells.EnumSpell;
import com.laton95.runemysteries.spells.projectiles.DamageProjectile;
import com.laton95.runemysteries.util.ItemHelper;
import com.laton95.runemysteries.util.LogHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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

	private boolean hasItems(EntityPlayer player, List<Spell.SpellCost> costs, InventoryRuneBag bag) {
		ArrayList<Spell.SpellCost> tempCosts = new ArrayList<>(costs);
		
		for (Spell.SpellCost spellCost : costs) {
			if (hasItem(player, spellCost, bag)) {
				tempCosts.remove(spellCost);
			}
		}
		if (tempCosts.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	private boolean hasItem(EntityPlayer player, Spell.SpellCost cost, InventoryRuneBag bag) {
		int count = 0;
		if (bag != null && cost.getItem() instanceof ItemRune) {
			count = bag.getRuneCount((ItemRune) cost.getItem());
		}
		
		for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
			ItemStack itemstack = player.inventory.getStackInSlot(i);
			if (itemstack.getItem().equals(cost.getItem())) {
				count += itemstack.getCount();
			}
		}
		return count >= cost.getCount();
	}

	private void removeItems(EntityPlayer player, List<Spell.SpellCost> costs, InventoryRuneBag bag) {
		for (Spell.SpellCost spellCost : costs) {
			removeItem(player, spellCost, bag);
		}
	}

	private void removeItem(EntityPlayer player, Spell.SpellCost cost, InventoryRuneBag bag) {
		int count = cost.getCount();
		
		if (bag != null && cost.getItem() instanceof ItemRune) {
			count = bag.removeRune((ItemRune) cost.getItem(), count);
		}
		
		int i = 0;
		while (count > 0 && i < player.inventory.getSizeInventory()) {
			ItemStack itemstack = player.inventory.getStackInSlot(i);
			if (itemstack.getItem().equals(cost.getItem())) {
				int temp = count;
				count -= itemstack.getCount();
				itemstack.shrink(temp);
			}
			i++;
		}
		
		if (count > 0) {
			LogHelper.info("Error: Spell cost not fully paid");
		}
	}
	
	public static Spells.EnumSpell getCurrentSpell(ItemStack spellbook) {
		if (spellbook.hasTagCompound()) {
			return EnumSpell.values()[spellbook.getTagCompound().getInteger("spell")];
		} else {
			return EnumSpell.NONE;
		}
	}
	
	public static void setCurrentSpell(ItemStack spellbook, EnumSpell spell) {
		if (!spellbook.hasTagCompound()) {
			spellbook.setTagCompound(new NBTTagCompound());
		}
		spellbook.getTagCompound().setInteger("spell", spell.ordinal());
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack spellbook = playerIn.getHeldItem(handIn);
		if (playerIn.isSneaking()) {
			playerIn.openGui(RuneMysteries.instance, GuiIDs.SPELLBOOK.ordinal(), worldIn, (int)playerIn.posX, (int)playerIn.posY, (int)playerIn.posZ);
			return new ActionResult<>(EnumActionResult.SUCCESS, spellbook);
		} else {
			Spell spell = Spells.getSpellFromEnum(getCurrentSpell(spellbook));
			
			if (spell == null) {
				playerIn.openGui(RuneMysteries.instance, GuiIDs.SPELLBOOK.ordinal(), worldIn, (int)playerIn.posX, (int)playerIn.posY, (int)playerIn.posZ);
				return new ActionResult<>(EnumActionResult.SUCCESS, spellbook);
			}

			if (playerIn.isCreative()) {
				spell.fireSpell(worldIn, playerIn);
				playerIn.getCooldownTracker().setCooldown(this, spell.getCooldown());
				return new ActionResult<>(EnumActionResult.SUCCESS, spellbook);
			} else {
				ItemStack bag = ItemHelper.getRuneBag(playerIn);
				InventoryRuneBag bagInventory = null;
				if (bag != null) {
					bagInventory = new InventoryRuneBag(bag);
				}
				
				if (hasItems(playerIn, spell.getCosts(), bagInventory)) {
					removeItems(playerIn, spell.getCosts(), bagInventory);
					spell.fireSpell(worldIn, playerIn);
					playerIn.getCooldownTracker().setCooldown(this, spell.getCooldown());	
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
}
