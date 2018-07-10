package com.laton95.runemysteries.block;

import com.laton95.runemysteries.enums.EnumColour;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockParticleLight extends RMModBlock
{
	
	private static final AxisAlignedBB BoundingBox = new AxisAlignedBB(0.3D, 0.3D, 0.3D, 0.7D, 0.7D, 0.7D);
	
	private final EnumColour colour;
	
	public BlockParticleLight(EnumColour colour)
	{
		super(colour.toString().toLowerCase() + "_light", Material.BARRIER, 0f, 0f, "pickaxe", 0, false, false);
		lightValue = 15;
		this.colour = colour;
	}
	
	@SuppressWarnings("deprecation")
	@Nullable
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	{
		return NULL_AABB;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
	{
		EnumParticleTypes particle = EnumParticleTypes.REDSTONE;
		
		for(int i = 0; i < 5; i++)
		{
			worldIn.spawnParticle(particle, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, colour.getRGB().getRed() / 255, colour.getRGB().getGreen() / 255, colour.getRGB().getBlue() / 255);
		}
		
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return null;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		
		if(!worldIn.isRemote)
		{
			BlockParticleLight newLight;
			
			if(playerIn.getHeldItem(hand).getItem() instanceof ItemDye)
			{
				newLight = colour.getColourFromDye(EnumDyeColor.byDyeDamage(playerIn.getHeldItem(hand).getItemDamage())).getLight();
			}
			else
			{
				int oldColourOrdinal = colour.ordinal();
				
				if(!playerIn.isSneaking())
				{
					if(oldColourOrdinal == 15)
					{ oldColourOrdinal = -1; }
					
					newLight = EnumColour.values()[oldColourOrdinal + 1].getLight();
				}
				else
				{
					if(oldColourOrdinal == 0)
					{ oldColourOrdinal = 16; }
					
					newLight = EnumColour.values()[oldColourOrdinal + -1].getLight();
				}
			}
			
			worldIn.setBlockState(pos, newLight.getDefaultState());
		}
		
		return true;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.INVISIBLE;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return BoundingBox;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	
}
