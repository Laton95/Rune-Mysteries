package com.laton95.runemysteries.block;

import com.laton95.runemysteries.advancements.ModCriteriaTriggers;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.init.ModItems;
import com.laton95.runemysteries.inventory.crafting.RuneCraftingInventory;
import com.laton95.runemysteries.item.crafting.ObeliskRecipe;
import com.laton95.runemysteries.reference.StringReference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.Optional;

public class ElementalObeliskBlock extends ModBlock {
	
	public static final BooleanProperty TOP = BooleanProperty.create("top");
	
	private final Item shardDrop;
	
	private final EnumRuneType runeType;
	
	public ElementalObeliskBlock(Item shardDrop, EnumRuneType runeType) {
		super(Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0f).lightValue(15));
		this.shardDrop = shardDrop;
		this.runeType = runeType;
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
		boolean isTop = world.getBlockState(currentPos.up()).getBlock() != this;
		return getDefaultState().with(TOP, isTop);
	}
	
	@Override
	public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if(getBlockFromItem(player.getHeldItem(hand).getItem()) == this) {
			return false;
		}
		
		if(!world.isRemote) {
			if(isCompleteObelisk(world, pos)) {
				ObeliskRecipe recipe = getRecipe(world, player.getHeldItem(hand), runeType);
				
				if(recipe != null) {
					if(player.getHeldItem(hand).getItem() == ModItems.GLASS_ORB) {
						ModCriteriaTriggers.CHARGE_ORB.trigger((ServerPlayerEntity) player);
					}
					
					ItemStack result = recipe.getCraftingResult();
					result.setCount(player.getHeldItem(hand).getCount());
					player.setHeldItem(hand, result);
				}
				else {
					player.sendMessage(new TranslationTextComponent(StringReference.BlockInteraction.OBELISK_INTERACT));
				}
			}
			else {
				player.sendMessage(new TranslationTextComponent(StringReference.BlockInteraction.OBELISK_INTERACT_FAIL));
			}
		}
		
		return true;
	}
	
	private ObeliskRecipe getRecipe(World world, ItemStack input, EnumRuneType runeType) {
		RuneCraftingInventory inventory = new RuneCraftingInventory(input, runeType);
		Optional<ObeliskRecipe> recipe = world.getRecipeManager().getRecipe(ObeliskRecipe.OBELISK_RECIPE, inventory, world);
		return recipe.orElse(null);
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		boolean isTop = context.getWorld().getBlockState(context.getPos().up()).getBlock() != this;
		return super.getStateForPlacement(context).with(TOP, isTop);
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(TOP);
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
