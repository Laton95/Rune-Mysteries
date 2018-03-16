package com.laton95.runemysteries.item;

import java.util.ArrayList;
import java.util.List;

import com.laton95.runemysteries.GuiHandler.GuiIDs;
import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.inventory.InventoryRuneBag;
import com.laton95.runemysteries.reference.NamesReference;
import com.laton95.runemysteries.spells.SpellBase;
import com.laton95.runemysteries.spells.Spells;
import com.laton95.runemysteries.util.ItemHelper;
import com.laton95.runemysteries.util.ItemNBTHelper;
import com.laton95.runemysteries.util.LogHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemSpellbook extends RMModItem
{

	public ItemSpellbook()
	{
		super("spellbook", true);
		setMaxStackSize(1);
	}

	private boolean hasItems(EntityPlayer player, List<SpellBase.SpellCost> costs, ArrayList<InventoryRuneBag> bags)
	{
		ArrayList<SpellBase.SpellCost> tempCosts = new ArrayList<>(costs);

		for (SpellBase.SpellCost spellCost : costs)
		{
			if (hasItem(player, spellCost, bags))
			{
				tempCosts.remove(spellCost);
			}
		}
		if (tempCosts.isEmpty())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private boolean hasItem(EntityPlayer player, SpellBase.SpellCost cost, ArrayList<InventoryRuneBag> bags)
	{
		int count = 0;
		if (bags.size() > 0 && cost.getItem() instanceof ItemRune)
		{
			for (InventoryRuneBag bag : bags)
			{
				count += bag.getRuneCount((ItemRune) cost.getItem(), cost.getMetadata());
			}
		}

		for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
		{
			ItemStack itemstack = player.inventory.getStackInSlot(i);
			if (itemstack.getItem().equals(cost.getItem())
					&& (!cost.usesMetadata() || itemstack.getItemDamage() == cost.getMetadata()))
			{
				count += itemstack.getCount();
			}
		}
		return count >= cost.getCount();
	}

	private void removeItems(EntityPlayer player, List<SpellBase.SpellCost> costs, ArrayList<InventoryRuneBag> bags)
	{
		for (SpellBase.SpellCost spellCost : costs)
		{
			removeItem(player, spellCost, bags);
		}
	}

	private void removeItem(EntityPlayer player, SpellBase.SpellCost cost, ArrayList<InventoryRuneBag> bags)
	{
		int count = cost.getCount();

		if (bags.size() > 0 && cost.getItem() instanceof ItemRune)
		{
			for (InventoryRuneBag bag : bags)
			{
				count = bag.removeRune((ItemRune) cost.getItem(), count, cost.getMetadata());
			}
		}

		int i = 0;
		while (count > 0 && i < player.inventory.getSizeInventory())
		{
			ItemStack itemstack = player.inventory.getStackInSlot(i);
			if (itemstack.getItem().equals(cost.getItem()) && (!cost.usesMetadata() || itemstack.getItemDamage() == cost.getMetadata()))
			{
				int temp = count;
				count -= itemstack.getCount();
				itemstack.shrink(temp);
			}
			i++;
		}

		if (count > 0)
		{
			LogHelper.info("Error: Spell cost not fully paid");
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		ItemStack spellbook = playerIn.getHeldItem(handIn);
		SpellBase spell = ItemNBTHelper.getSpell(spellbook);
		if (playerIn.isSneaking() || spell == Spells.NONE_SPELL)
		{
			playerIn.openGui(RuneMysteries.instance, GuiIDs.SPELLBOOK.ordinal(), worldIn, (int) playerIn.posX,
					(int) playerIn.posY, (int) playerIn.posZ);
			return new ActionResult<>(EnumActionResult.SUCCESS, spellbook);
		}
		else
		{

			if (playerIn.isCreative())
			{
				CastSpell(worldIn, playerIn, spell);
				return new ActionResult<>(EnumActionResult.SUCCESS, spellbook);
			}
			else
			{
				ArrayList<InventoryRuneBag> bagInventories = ItemHelper.getBagInventories(playerIn);

				if (hasItems(playerIn, spell.getCosts(), bagInventories))
				{
					removeItems(playerIn, spell.getCosts(), bagInventories);
					CastSpell(worldIn, playerIn, spell);
					return new ActionResult<>(EnumActionResult.SUCCESS, spellbook);
				}
				else
				{
					if (!worldIn.isRemote)
					{
						playerIn.sendMessage(new TextComponentTranslation(NamesReference.Spellbook.NO_RUNES));
					}
					return new ActionResult<>(EnumActionResult.FAIL, spellbook);
				}
			}
		}
	}

	public void CastSpell(World worldIn, EntityPlayer playerIn, SpellBase spell)
	{
		if (!worldIn.isRemote)
		{
			if (spell.fireSpell(worldIn, playerIn))
			{
				playerIn.getCooldownTracker().setCooldown(this, spell.getCooldown());
			}
			else
			{
				playerIn.getCooldownTracker().setCooldown(this, 5);
				playerIn.sendMessage(new TextComponentTranslation(NamesReference.Talisman.FAIL));
			}
		}
	}
}
