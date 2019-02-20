package com.laton95.runemysteries.item;

import com.laton95.runemysteries.init.ModBlocks;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceFluidMode;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ItemSpellbook extends ModItem {
	
	
	public ItemSpellbook() {
		super(new Properties().maxStackSize(1));
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		RayTraceResult rayTrace = playerIn.rayTrace(20, 0.0f, RayTraceFluidMode.NEVER);
		
		if(rayTrace != null && rayTrace.type == RayTraceResult.Type.BLOCK) {
			BlockPos pos = rayTrace.getBlockPos();
			
			EnumFacing facing = rayTrace.sideHit;
			
			tryPlace(new BlockItemUseContext(new ItemUseContext(playerIn, new ItemStack(Blocks.STONE.asItem()), pos, facing, (float) rayTrace.hitVec.x, (float) rayTrace.hitVec.y, (float) rayTrace.hitVec.z)));
		}
		
		
		return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}
	
	private EnumActionResult tryPlace(BlockItemUseContext p_195942_1_) {
		if(!p_195942_1_.canPlace()) {
			return EnumActionResult.FAIL;
		}
		else {
			IBlockState iblockstate = ModBlocks.WHITE_LIGHT.getStateForPlacement(p_195942_1_);
			if(iblockstate == null) {
				return EnumActionResult.FAIL;
			}
			else if(!this.placeBlock(p_195942_1_, iblockstate)) {
				return EnumActionResult.FAIL;
			}
			else {
				BlockPos blockpos = p_195942_1_.getPos();
				
				World world = p_195942_1_.getWorld();
				EntityPlayer entityplayer = p_195942_1_.getPlayer();
				ItemStack itemstack = p_195942_1_.getItem();
				IBlockState iblockstate1 = world.getBlockState(blockpos);
				Block block = iblockstate1.getBlock();
				if(block == iblockstate.getBlock()) {
					block.onBlockPlacedBy(world, blockpos, iblockstate1, entityplayer, itemstack);
					if(entityplayer instanceof EntityPlayerMP) {
						CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP) entityplayer, blockpos, itemstack);
					}
				}
				
				SoundType soundtype = iblockstate1.getSoundType(world, blockpos, p_195942_1_.getPlayer());
				world.playSound(entityplayer, blockpos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
				itemstack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
		}
	}
	
	private boolean placeBlock(BlockItemUseContext p_195941_1_, IBlockState p_195941_2_) {
		return p_195941_1_.getWorld().setBlockState(p_195941_1_.getPos(), p_195941_2_, 11);
	}
}
