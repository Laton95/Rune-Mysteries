package com.laton95.runemysteries.spells;

import com.google.common.collect.ImmutableList;
import com.laton95.runemysteries.init.ModItems;
import com.laton95.runemysteries.item.ItemRune;
import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.reference.NamesReference;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;

public class BonesToBananas extends SpellBase
{
	private final static List<SpellCost> costs = ImmutableList.of(
			new SpellCost(ModItems.RUNE, 2, ItemRune.EnumRuneType.WATER.ordinal()),
			new SpellCost(ModItems.RUNE, 2, ItemRune.EnumRuneType.EARTH.ordinal()),
			new SpellCost(ModItems.RUNE, 1, ItemRune.EnumRuneType.NATURE.ordinal())
	);
	
	public BonesToBananas()
	{
		super(costs, 20, NamesReference.Spells.BONES_TO_BANANAS_SPELL_NAME, NamesReference.Spells.BONES_TO_BANANAS_SPELL_DESCRIPTION, new ResourceLocation(ModReference.MOD_ID, "textures/spells/gui/explosion.png"));
	}
	
	@Override
	public boolean fireSpell(World world, EntityPlayer player)
	{
		for (int i = 0; i < player.inventory.getSizeInventory(); i++)
		{
			if (player.inventory.getStackInSlot(i).getItem() == Items.BONE)
			{
				ItemStack bananas = new ItemStack(ModItems.BANANA, player.inventory.getStackInSlot(i).getCount());
				player.inventory.setInventorySlotContents(i, bananas);
			}
		}
		
		int radius = 5;
		List<EntityItem> items = world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(player.posX - radius, player.posY - radius, player.posZ - radius, player.posX + radius, player.posY + radius, player.posZ + radius));
		
		for (EntityItem item : items)
		{
			if (item.getItem().getItem() == Items.BONE)
			{
				ItemStack bananas = new ItemStack(ModItems.BANANA, item.getItem().getCount());
				item.setItem(bananas);
			}
		}
		
		return  true;
	}
}
