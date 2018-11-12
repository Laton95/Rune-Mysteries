package com.laton95.runemysteries.block;

import com.laton95.runemysteries.advancement.triggers.Triggers;
import com.laton95.runemysteries.block.properties.PropertyCorner;
import com.laton95.runemysteries.enums.EnumCorner;
import com.laton95.runemysteries.init.ModBlocks;
import com.laton95.runemysteries.init.ModPotions;
import com.laton95.runemysteries.reference.NamesReference;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.LinkedList;
import java.util.Random;

public class BlockElderStone extends RMModBlock
{
	public static final PropertyCorner CORNER = PropertyCorner.create("corner");
	
	public static final PropertyBool TOP = PropertyBool.create("top");
	
	protected static final AxisAlignedBB[] AXIS_ALIGNED_BBS = new AxisAlignedBB[] {
			new AxisAlignedBB(0.125D, 0.0D, 0.125D, 1.0D, 1.0D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.125D, 0.875D, 1.0D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.875D, 1.0D, 0.875D),
			new AxisAlignedBB(0.125D, 0.0D, 0.0D, 1.0D, 1.0D, 0.875D),
			new AxisAlignedBB(0.125D, 0.0D, 0.125D, 1.0D, 0.75D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.125D, 0.875D, 0.75D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.875D, 0.75D, 0.875D),
			new AxisAlignedBB(0.125D, 0.0D, 0.0D, 1.0D, 0.75D, 0.875D)
	};
	
	public BlockElderStone()
	{
		super("elder_stone", Material.ROCK, 0f, 0f, "pickaxe", 0, false, true);
		setBlockUnbreakable();
		setTickRandomly(true);
		this.setDefaultState(this.blockState.getBaseState().withProperty(CORNER, EnumCorner.NORTH_EAST).withProperty(TOP, true));
	}
	
	public int getMetaFromState(IBlockState state)
	{
		return 0;
	}
	
	/**
	 * Get the actual Block state of this Block at the given position. This applies properties not visible in the
	 * metadata, such as fence connections.
	 */
	@SuppressWarnings("deprecation")
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
	{
		boolean hasNorth = worldIn.getBlockState(pos.north()).getBlock() == this;
		boolean hasSouth = worldIn.getBlockState(pos.south()).getBlock() == this;
		boolean hasWest = worldIn.getBlockState(pos.west()).getBlock() == this;
		boolean hasEast = worldIn.getBlockState(pos.east()).getBlock() == this;
		
		EnumCorner corner = EnumCorner.NORTH_EAST;
		
		if(hasNorth && hasWest)
		{
			corner = EnumCorner.SOUTH_EAST;
		}
		
		if(hasNorth && hasEast)
		{
			corner = EnumCorner.SOUTH_WEST;
		}
		
		if(hasSouth && hasWest)
		{
			corner = EnumCorner.NORTH_EAST;
		}
		
		if(hasSouth && hasEast)
		{
			corner = EnumCorner.NORTH_WEST;
		}
		
		boolean isTop = worldIn.getBlockState(pos.up()).getBlock() != this;
		
		return state.withProperty(CORNER, corner).withProperty(TOP, isTop);
	}
	
	@SuppressWarnings("deprecation")
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		switch(state.getActualState(source, pos).getValue(CORNER))
		{
			case NORTH_WEST:
				if(state.getActualState(source, pos).getValue(TOP))
				{
					return AXIS_ALIGNED_BBS[4];
				}
				else
				{
					return AXIS_ALIGNED_BBS[0];
				}
			case NORTH_EAST:
				if(state.getActualState(source, pos).getValue(TOP))
				{
					return AXIS_ALIGNED_BBS[5];
				}
				else
				{
					return AXIS_ALIGNED_BBS[1];
				}
			case SOUTH_WEST:
				if(state.getActualState(source, pos).getValue(TOP))
				{
					return AXIS_ALIGNED_BBS[7];
				}
				else
				{
					return AXIS_ALIGNED_BBS[3];
				}
			case SOUTH_EAST:
				if(state.getActualState(source, pos).getValue(TOP))
				{
					return AXIS_ALIGNED_BBS[6];
				}
				else
				{
					return AXIS_ALIGNED_BBS[2];
				}
		}
		
		return AXIS_ALIGNED_BBS[0];
	}
	
	@SuppressWarnings("deprecation")
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
	{
		return face != EnumFacing.DOWN ? BlockFaceShape.UNDEFINED : BlockFaceShape.SOLID;
	}
	
	/**
	 * Used to determine ambient occlusion and culling when rebuilding chunks for render
	 */
	@SuppressWarnings("deprecation")
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		LinkedList<BlockPos> stonePositions = new LinkedList<>();
		
		int radius = 10;
		for(int y = -radius; y < radius; y++)
		{
			for(int x = -radius; x < radius; x++)
			{
				for(int z = -radius; z < radius; z++)
				{
					BlockPos curPos = pos.add(x, y, z);
					if(worldIn.getBlockState(curPos) == Blocks.STONE.getDefaultState())
					{
						stonePositions.add(curPos);
					}
				}
			}
		}
		
		if(stonePositions.size() > 0)
		{
			BlockPos essPos = stonePositions.get(rand.nextInt(stonePositions.size()));
			
			worldIn.setBlockState(essPos, ModBlocks.FINITE_RUNE_ESSENCE.getDefaultState());
		}
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(!worldIn.isRemote)
		{
			Triggers.TOUCH_ELDER_STONE.trigger((EntityPlayerMP) playerIn);
			
			playerIn.sendMessage(new TextComponentTranslation(NamesReference.BlockInteraction.ELDER_STONE_INTERACT, playerIn.getDisplayName()));
			
			playerIn.addPotionEffect(new PotionEffect(ModPotions.STONETOUCHER, 12000, 0, false, false));
		}
		
		return true;
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, CORNER, TOP);
	}
}
