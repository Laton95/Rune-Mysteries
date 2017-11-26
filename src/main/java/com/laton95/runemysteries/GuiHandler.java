package com.laton95.runemysteries;

import com.laton95.runemysteries.gui.GuiRuneBag;
import com.laton95.runemysteries.gui.GuiSpellbook;
import com.laton95.runemysteries.inventory.ContainerRuneBag;
import com.laton95.runemysteries.inventory.InventoryRuneBag;
import com.laton95.runemysteries.item.ItemRuneBag;
import com.laton95.runemysteries.item.ItemSpellbook;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{

	public enum GuiIDs
	{
		SPELLBOOK, RUNE_BAG
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (GuiIDs.values()[ID])
		{
		case SPELLBOOK:
			return null;
		case RUNE_BAG:
			if (player.getHeldItemMainhand().getItem() instanceof ItemRuneBag)
			{
				return new ContainerRuneBag(player.inventory, new InventoryRuneBag(player.getHeldItemMainhand()));
			}
			else if (player.getHeldItemOffhand().getItem() instanceof ItemRuneBag)
			{
				return new ContainerRuneBag(player.inventory, new InventoryRuneBag(player.getHeldItemOffhand()));
			}
			else
			{
				return null;
			}
		}
		throw new IllegalArgumentException("No guid with id " + ID);
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (GuiIDs.values()[ID])
		{
		case SPELLBOOK:
			if (player.getHeldItemMainhand().getItem() instanceof ItemSpellbook)
			{
				return new GuiSpellbook(player, player.getHeldItemMainhand());
			}
			else if (player.getHeldItemOffhand().getItem() instanceof ItemSpellbook)
			{
				return new GuiSpellbook(player, player.getHeldItemOffhand());
			}
			else
			{
				return null;
			}
		case RUNE_BAG:
			if (player.getHeldItemMainhand().getItem() instanceof ItemRuneBag)
			{
				return new GuiRuneBag(player.inventory, new InventoryRuneBag(player.getHeldItemMainhand()));
			}
			else if (player.getHeldItemOffhand().getItem() instanceof ItemRuneBag)
			{
				return new GuiRuneBag(player.inventory, new InventoryRuneBag(player.getHeldItemOffhand()));
			}
			else
			{
				return null;
			}
		}
		throw new IllegalArgumentException("No guid with id " + ID);
	}

}
