package com.laton95.runemysteries.item;

import com.laton95.runemysteries.util.WorldHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class ItemDebug extends ModItem {
	
	public ItemDebug() {
		super(new Properties().maxStackSize(1));
	}
	
	@Override
	public EnumActionResult onItemUse(ItemUseContext context) {
		if(!context.getWorld().isRemote) {
			IBlockState state = context.getWorld().getBlockState(context.getPos());
			
			EntityPlayer player = context.getPlayer();
			player.sendMessage(new TextComponentString("----------"));
			player.sendMessage(new TextComponentTranslation(state.getBlock().getTranslationKey()));
			player.sendMessage(new TextComponentString("isFullCube:" + state.isFullCube()));
			player.sendMessage(new TextComponentString("isSolid:" + state.isSolid()));
			player.sendMessage(new TextComponentString("top:" + WorldHelper.getTopSolidBlock(context.getWorld(), context.getPos()).getY()));
			player.sendMessage(new TextComponentString("----------"));
		}
		
		return EnumActionResult.SUCCESS;
	}
}
