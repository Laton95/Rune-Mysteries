package com.laton95.runemysteries.block;

import com.laton95.runemysteries.advancements.ModCriteriaTriggers;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.init.ModItems;
import com.laton95.runemysteries.inventory.InventoryObelisk;
import com.laton95.runemysteries.item.crafting.ObeliskRecipe;
import com.laton95.runemysteries.reference.StringReference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockElementalObelisk extends ModBlock {
	
	public static final BooleanProperty TOP = BooleanProperty.create("top");
	
	private final Item shardDrop;
	
	private final EnumRuneType runeType;
	
	public BlockElementalObelisk(String name, Item shardDrop, EnumRuneType runeType) {
		super(name, Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0f).lightValue(15));
		this.shardDrop = shardDrop;
		this.runeType = runeType;
	}
	
	@Override
	public IBlockState updatePostPlacement(IBlockState stateIn, EnumFacing facing, IBlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		boolean isTop = worldIn.getBlockState(currentPos.up()).getBlock() != this;
		return getDefaultState().with(TOP, isTop);
	}
	
	@Override
	public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune) {
		return shardDrop;
	}
	
	@Override
	public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(getBlockFromItem(player.getHeldItem(hand).getItem()) == this) {
			return false;
		}
		
		if(!worldIn.isRemote) {
			if(isCompleteObelisk(worldIn, pos)) {
				InventoryObelisk inventory = new InventoryObelisk(player.getHeldItem(hand), runeType);
				ObeliskRecipe recipe = worldIn.getRecipeManager().getRecipe(inventory, worldIn, ObeliskRecipe.OBELISK_TYPE);
				
				if(recipe != null) {
					if(player.getHeldItem(hand).getItem() == ModItems.GLASS_ORB) {
						ModCriteriaTriggers.CHARGE_ORB.trigger((EntityPlayerMP) player);
					}
					
					ItemStack result = recipe.getCraftingResult(inventory);
					result.setCount(player.getHeldItem(hand).getCount());
					player.setHeldItem(hand, result);
				}
				else {
					player.sendMessage(new TextComponentTranslation(StringReference.BlockInteraction.OBELISK_INTERACT));
				}
			}
			else {
				player.sendMessage(new TextComponentTranslation(StringReference.BlockInteraction.OBELISK_INTERACT_FAIL));
			}
		}
		
		return true;
	}
	
	@Nullable
	@Override
	public IBlockState getStateForPlacement(BlockItemUseContext context) {
		boolean isTop = context.getWorld().getBlockState(context.getPos().up()).getBlock() != this;
		return super.getStateForPlacement(context).with(TOP, isTop);
	}
	
	@Override
	public int getItemsToDropCount(IBlockState state, int fortune, World worldIn, BlockPos pos, Random random) {
		return 1;
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder) {
		builder.add(TOP);
	}
	
	@Override
	public boolean canSilkHarvest(IBlockState state, IWorldReader world, BlockPos pos, EntityPlayer player) {
		return false;
	}
	
	private boolean isCompleteObelisk(World world, BlockPos pos) {
		BlockPos bottom = pos;
		while(world.getBlockState(bottom.down()).getBlock() == this) {
			bottom = bottom.down();
		}
		
		int height = 1;
		
		while(world.getBlockState(bottom.up()).getBlock() == this) {
			height++;
			bottom = bottom.up();
		}
		
		return height >= 3;
	}
}
