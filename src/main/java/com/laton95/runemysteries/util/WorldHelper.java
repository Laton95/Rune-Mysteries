package com.laton95.runemysteries.util;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.BiomeDictionary;

import java.util.List;

public class WorldHelper
{
	public static boolean biomeIsOfType(List<BiomeDictionary.Type> biomeTypes, Biome biome)
	{
		for(BiomeDictionary.Type type : biomeTypes)
		{
			if(BiomeDictionary.hasType(biome, type))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public static BlockPos getTopSolidBlock(World world, BlockPos pos)
	{
		Chunk chunk = world.getChunkFromBlockCoords(pos);
		BlockPos blockpos;
		BlockPos blockpos1;
		
		for(blockpos = new BlockPos(pos.getX(), chunk.getTopFilledSegment() + 16, pos.getZ()); blockpos.getY() >= 0; blockpos = blockpos1)
		{
			blockpos1 = blockpos.down();
			IBlockState state = chunk.getBlockState(blockpos1);
			if(state.getMaterial().blocksMovement() && !state.getBlock().isLeaves(state, world, blockpos1) && !state.getBlock().isFoliage(world, blockpos1))
			{
				break;
			}
		}
		
		return blockpos;
	}
	
	public static boolean isNearby(ChunkPos chunkA, ChunkPos chunkB, int range)
	{
		int x = Math.abs(chunkA.x - chunkB.x);
		int z = Math.abs(chunkA.z - chunkB.z);
		
		return Math.max(z, x) < range;
	}
	
	public static boolean isNearby(BlockPos blockA, BlockPos blockB, int range)
	{
		int x = Math.abs(blockA.getX() - blockB.getX());
		int z = Math.abs(blockA.getZ() - blockB.getZ());
		int y = Math.abs(blockA.getY() - blockB.getY());
		
		return Math.max(Math.max(z, x), y * 2) < range;
	}
	
	public static Tuple<HorizontalDirection, VerticalDirection> getDirection(BlockPos from, BlockPos to)
	{
		HorizontalDirection horizontalDirection;
		VerticalDirection verticalDirection = VerticalDirection.NONE;
		
		double horizontalDistanceX = from.getX() - to.getX();
		double verticalDistance = from.getY() - to.getY();
		double horizontalDistanceZ = from.getZ() - to.getZ();
		
		double horizontalDistance = Math.sqrt(Math.pow(horizontalDistanceX, 2) + Math.pow(horizontalDistanceZ, 2));
		
		double verticalAngle = Math.atan(verticalDistance / horizontalDistance);
		
		double horizontalAngle = Math.atan2(horizontalDistanceX, horizontalDistanceZ);
		
		double offset = Math.PI / 8;
		
		//Calculate horizontal direction
		if(horizontalDistance == 0)
		{
			horizontalDirection = HorizontalDirection.NONE;
		}
		else if(horizontalAngle >= Math.PI - offset)
		{
			horizontalDirection = HorizontalDirection.SOUTH;
		}
		else if(horizontalAngle > 6 * Math.PI / 8 - offset)
		{
			horizontalDirection = HorizontalDirection.SOUTH_WEST;
		}
		else if(horizontalAngle >= 4 * Math.PI / 8 - offset)
		{
			horizontalDirection = HorizontalDirection.WEST;
		}
		else if(horizontalAngle > 2 * Math.PI / 8 - offset)
		{
			horizontalDirection = HorizontalDirection.NORTH_WEST;
		}
		else if(horizontalAngle >= -2 * Math.PI / 8 + offset)
		{
			horizontalDirection = HorizontalDirection.NORTH;
		}
		else if(horizontalAngle > -4 * Math.PI / 8 + offset)
		{
			horizontalDirection = HorizontalDirection.NORTH_EAST;
		}
		else if(horizontalAngle >= -6 * Math.PI / 8 + offset)
		{
			horizontalDirection = HorizontalDirection.EAST;
		}
		else if(horizontalAngle > -8 * Math.PI / 8 + offset)
		{
			horizontalDirection = HorizontalDirection.SOUTH_EAST;
		}
		else
		{
			horizontalDirection = HorizontalDirection.SOUTH;
		}
		
		//Calculate vertical direction
		double cutoffAngle = Math.PI / 4;
		if(verticalAngle > cutoffAngle)
		{
			verticalDirection = VerticalDirection.DOWN;
		}
		else if(verticalAngle > 0)
		{
			verticalDirection = VerticalDirection.SLIGHT_DOWN;
		}
		else if(verticalAngle < -cutoffAngle)
		{
			verticalDirection = VerticalDirection.UP;
		}
		else if(verticalAngle < 0)
		{
			verticalDirection = VerticalDirection.SLIGHT_UP;
		}
		
		return new Tuple<>(horizontalDirection, verticalDirection);
	}
	
	public enum HorizontalDirection
	{
		NONE, NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST
	}
	
	public enum VerticalDirection
	{
		NONE, UP, DOWN, SLIGHT_UP, SLIGHT_DOWN
	}
}
