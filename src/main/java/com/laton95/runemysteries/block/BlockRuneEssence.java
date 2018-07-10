package com.laton95.runemysteries.block;

import com.laton95.runemysteries.init.ModBlocks;
import com.laton95.runemysteries.init.ModItems;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.EnumFaceDirection;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockRuneEssence extends RMModBlock
{
	private final boolean isFinite;
	
	public BlockRuneEssence(boolean isFinite)
	{
		super(isFinite ? "rune_essence_block_finite" : "rune_essence_block", Material.ROCK, 1.5f, 2000f, "pickaxe", 0, isFinite, isFinite);
		this.isFinite = isFinite;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
	{
		if(!isFinite)
		{
			double xPos = 0;
			double yPos = 0;
			double zPos = 0;
			
			double offset = 0.1;
			
			switch(EnumFaceDirection.values()[rand.nextInt(EnumFaceDirection.values().length - 1) + 1])
			{
				case UP:
					xPos += rand.nextFloat() + offset;
					yPos += 1 + offset;
					zPos += rand.nextFloat() + offset;
					break;
				case NORTH:
					xPos += rand.nextFloat() + offset;
					yPos += rand.nextFloat() + offset;
					zPos -= offset;
					break;
				case SOUTH:
					xPos += rand.nextFloat() + offset;
					yPos += rand.nextFloat() + offset;
					zPos += 1 + offset;
					break;
				case WEST:
					xPos -= offset;
					yPos += rand.nextFloat() + offset;
					zPos += rand.nextFloat() + offset;
					break;
				case EAST:
					xPos += 1 + offset;
					yPos += rand.nextFloat() + offset;
					zPos += rand.nextFloat() + offset;
					break;
			}
			
			xPos += pos.getX();
			yPos += pos.getY();
			zPos += pos.getZ();
			
			worldIn.spawnParticle(EnumParticleTypes.SPELL_INSTANT, xPos, yPos, zPos, 0, -0.09, 0);
		}
	}
	
	@Override
	public int quantityDropped(Random random)
	{
		return 1 + random.nextInt(4);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return ModItems.RUNE_ESSENCE;
	}
	
	@Override
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
	{
		super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
		if(!isFinite)
		{
			worldIn.setBlockState(pos, state);
		}
	}
	
	@Override
	public int quantityDroppedWithBonus(int fortune, Random random)
	{
		return this.quantityDropped(random) + random.nextInt(fortune + 1);
	}
	
	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		return false;
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(ModBlocks.FINITE_RUNE_ESSENCE), 1, 0);
	}
}
