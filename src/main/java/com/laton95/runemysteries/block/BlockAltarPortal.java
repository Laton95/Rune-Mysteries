package com.laton95.runemysteries.block;

import com.laton95.runemysteries.tileentity.TileEntityAltarPortal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockAltarPortal extends RMModBlock implements ITileEntityProvider
{
	
	public static final AxisAlignedBB BoundingBox = new AxisAlignedBB(0, 0, 0, 1, 0.1, 1);
	
	public BlockAltarPortal(String name)
	{
		super(name, Material.ROCK, 0, 2000f, null, 0, false);
		setBlockUnbreakable();
		lightValue = 12;
	}
	
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
	{
		TileEntityAltarPortal portal = (TileEntityAltarPortal) worldIn.getTileEntity(pos);
		portal.TeleportEntity(entityIn, worldIn);
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityAltarPortal();
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	{
		return NULL_AABB;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return BoundingBox;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
	{
		double blockCenterX = pos.getX() + 0.5D;
		double blockCenterY = pos.getY() + 0.2D;
		double blockCenterZ = pos.getZ() + 0.5D;
		double sideOffset = 0.4D;
		double cornerOffset = 0.3D;
		EnumParticleTypes particle = EnumParticleTypes.PORTAL;
		// Sides
		worldIn.spawnParticle(particle, blockCenterX + sideOffset, blockCenterY, blockCenterZ, 0.01, 0.01, 0.01);
		worldIn.spawnParticle(particle, blockCenterX - sideOffset, blockCenterY, blockCenterZ, 0.01, 0.01, 0.01);
		worldIn.spawnParticle(particle, blockCenterX, blockCenterY, blockCenterZ + sideOffset, 0.01, 0.01, 0.01);
		worldIn.spawnParticle(particle, blockCenterX, blockCenterY, blockCenterZ - sideOffset, 0.01, 0.01, 0.01);
		
		// Corners
		worldIn.spawnParticle(particle, blockCenterX + cornerOffset, blockCenterY, blockCenterZ + cornerOffset, 0.01, 0.01, 0.01);
		worldIn.spawnParticle(particle, blockCenterX + cornerOffset, blockCenterY, blockCenterZ - cornerOffset, 0.01, 0.01, 0.01);
		worldIn.spawnParticle(particle, blockCenterX - cornerOffset, blockCenterY, blockCenterZ + cornerOffset, 0.01, 0.01, 0.01);
		worldIn.spawnParticle(particle, blockCenterX - cornerOffset, blockCenterY, blockCenterZ - cornerOffset, 0.01, 0.01, 0.01);
	}
}
