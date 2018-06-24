package com.laton95.runemysteries.item;

import com.laton95.runemysteries.GuiHandler.GuiIDs;
import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.init.ModPotions;
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

import java.util.ArrayList;
import java.util.List;

public class ItemSpellbook extends RMModItem
{
	
	public ItemSpellbook()
	{
		super("spellbook", true);
		setMaxStackSize(1);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		ItemStack spellbook = playerIn.getHeldItem(handIn);
		SpellBase spell = ItemNBTHelper.getSpell(spellbook);
		if(playerIn.isSneaking() || spell == Spells.NONE_SPELL)
		{
			playerIn.openGui(RuneMysteries.instance, GuiIDs.SPELLBOOK.ordinal(), worldIn, (int) playerIn.posX, (int) playerIn.posY, (int) playerIn.posZ);
			return new ActionResult<>(EnumActionResult.SUCCESS, spellbook);
		}
		else if(spell.canCast(worldIn, playerIn))
		{
			if(playerIn.isCreative() || ModPotions.STONETOUCHER.hasEffect(playerIn))
			{
				CastSpell(worldIn, playerIn, spell);
				return new ActionResult<>(EnumActionResult.SUCCESS, spellbook);
			}
			else
			{
				ArrayList<InventoryRuneBag> bagInventories = ItemHelper.getBagInventories(playerIn);
				
				if(hasItems(playerIn, spell.getCosts(), bagInventories))
				{
					removeItems(playerIn, spell.getCosts(), bagInventories);
					CastSpell(worldIn, playerIn, spell);
					return new ActionResult<>(EnumActionResult.SUCCESS, spellbook);
				}
				else
				{
					if(!worldIn.isRemote)
					{
						playerIn.sendMessage(new TextComponentTranslation(NamesReference.Spellbook.NO_RUNES));
					}
					return new ActionResult<>(EnumActionResult.FAIL, spellbook);
				}
			}
		}
		
		if(!worldIn.isRemote)
		{
			playerIn.sendMessage(new TextComponentTranslation(NamesReference.Spellbook.SPELL_FAIL));
			playerIn.getCooldownTracker().setCooldown(this, 10);
		}
		
		return new ActionResult<>(EnumActionResult.FAIL, spellbook);
	}
	
	public void CastSpell(World worldIn, EntityPlayer playerIn, SpellBase spell)
	{
		if(!worldIn.isRemote)
		{
			spell.fireSpell(worldIn, playerIn);
			playerIn.getCooldownTracker().setCooldown(this, spell.getCooldown());
		}
	}
	
	private boolean hasItems(EntityPlayer player, List<SpellBase.SpellCost> costs, ArrayList<InventoryRuneBag> bags)
	{
		ArrayList<SpellBase.SpellCost> tempCosts = new ArrayList<>(costs);
		
		for(SpellBase.SpellCost spellCost : costs)
		{
			if(hasItem(player, spellCost, bags))
			{
				tempCosts.remove(spellCost);
			}
		}
		return tempCosts.isEmpty();
	}
	
	private void removeItems(EntityPlayer player, List<SpellBase.SpellCost> costs, ArrayList<InventoryRuneBag> bags)
	{
		for(SpellBase.SpellCost spellCost : costs)
		{
			removeItem(player, spellCost, bags);
		}
	}
	
	private boolean hasItem(EntityPlayer player, SpellBase.SpellCost cost, ArrayList<InventoryRuneBag> bags)
	{
		int count = 0;
		
		ItemStack source = getHeldRuneSources(player);
		
		if(cost.getItem() instanceof ItemRune && source != null)
		{
			if(cost.getMetadata() == ((IRuneSource) source.getItem()).getRuneType().ordinal())
			{
				return true;
			}
		}
		
		if(bags.size() > 0 && cost.getItem() instanceof ItemRune)
		{
			for(InventoryRuneBag bag : bags)
			{
				count += bag.getRuneCount((ItemRune) cost.getItem(), cost.getMetadata());
			}
		}
		
		for(int i = 0; i < player.inventory.getSizeInventory(); ++i)
		{
			ItemStack itemstack = player.inventory.getStackInSlot(i);
			if(itemstack.getItem().equals(cost.getItem()) && (!cost.usesMetadata() || itemstack.getItemDamage() == cost.getMetadata()))
			{
				count += itemstack.getCount();
			}
		}
		return count >= cost.getCount();
	}
	
	private ItemStack getHeldRuneSources(EntityPlayer player)
	{
		if(player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof IRuneSource)
		{
			return player.getHeldItem(EnumHand.MAIN_HAND);
		}
		else if(player.getHeldItem(EnumHand.OFF_HAND).getItem() instanceof IRuneSource)
		{
			return player.getHeldItem(EnumHand.OFF_HAND);
		}
		
		return null;
	}
	
	private void removeItem(EntityPlayer player, SpellBase.SpellCost cost, ArrayList<InventoryRuneBag> bags)
	{
		int count = cost.getCount();
		
		ItemStack source = getHeldRuneSources(player);
		
		if(cost.getItem() instanceof ItemRune && source != null)
		{
			if(cost.getMetadata() == ((IRuneSource) source.getItem()).getRuneType().ordinal())
			{
				int temp = count;
				count -= source.getMaxDamage() - source.getItemDamage();
				source.damageItem(temp, player);
			}
		}
		
		if(count > 0 && bags.size() > 0 && cost.getItem() instanceof ItemRune)
		{
			for(InventoryRuneBag bag : bags)
			{
				count = bag.removeRune((ItemRune) cost.getItem(), count, cost.getMetadata());
			}
		}
		
		int i = 0;
		while(count > 0 && i < player.inventory.getSizeInventory())
		{
			ItemStack itemstack = player.inventory.getStackInSlot(i);
			if(itemstack.getItem().equals(cost.getItem()) && (!cost.usesMetadata() || itemstack.getItemDamage() == cost.getMetadata()))
			{
				int temp = count;
				count -= itemstack.getCount();
				itemstack.shrink(temp);
			}
			i++;
		}
		
		if(count > 0)
		{
			LogHelper.error("Error: Spell cost not fully paid");
		}
	}
}
