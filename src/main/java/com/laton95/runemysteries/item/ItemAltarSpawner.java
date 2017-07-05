package com.laton95.runemysteries.item;

import com.laton95.runemysteries.init.BlockRegistry;
import com.laton95.runemysteries.utility.LogHelper;
import com.laton95.runemysteries.world.StructureSpawner;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemAltarSpawner extends RMModItem {
	
	public ItemAltarSpawner() {
		super("altar_Spawner", true);
		setMaxStackSize(1);
	}

public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack spawner = playerIn.getHeldItem(handIn);

		int x = playerIn.getPosition().getX();
		int y = playerIn.getPosition().getY();
		int z = playerIn.getPosition().getZ();
		
		StructureSpawner.loadStructure(new BlockPos(x, y, z), worldIn, "runealtar");

		
		spawner.setCount(0);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, spawner);
	}
}
