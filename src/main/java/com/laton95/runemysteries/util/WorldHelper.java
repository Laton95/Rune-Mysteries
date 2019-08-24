package com.laton95.runemysteries.util;

import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.BiomeDictionary;

import java.util.List;

public class WorldHelper {
	
	public static BlockPos getTopSolidBlock(IWorld world, BlockPos pos) {
		return getTopSolidBlock(world, pos.getX(), pos.getZ());
	}
	
	public static BlockPos getTopSolidBlock(IWorld world, int x, int z) {
		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(x, world.getHeight(Heightmap.Type.WORLD_SURFACE, x, z), z);
		
		while(!world.getBlockState(pos).isSolid()) {
			pos.move(net.minecraft.util.Direction.DOWN);
		}
		
		return pos;
	}
	
	public static boolean isNearby(BlockPos blockA, BlockPos blockB, int range) {
		int x = Math.abs(blockA.getX() - blockB.getX());
		int z = Math.abs(blockA.getZ() - blockB.getZ());
		int y = Math.abs(blockA.getY() - blockB.getY());
		
		return Math.max(Math.max(z, x), y * 2) < range;
	}
	
	public static Direction getDirection(BlockPos from, BlockPos to) {
		Direction direction;
		
		double horizontalDistanceX = from.getX() - to.getX();
		double verticalDistance = from.getY() - to.getY();
		double horizontalDistanceZ = from.getZ() - to.getZ();
		
		double horizontalDistance = Math.sqrt(Math.pow(horizontalDistanceX, 2) + Math.pow(horizontalDistanceZ, 2));
		
		double verticalAngle = Math.atan(verticalDistance / horizontalDistance);
		
		double horizontalAngle = Math.atan2(horizontalDistanceX, horizontalDistanceZ);
		
		double offset = Math.PI / 8;
		if(horizontalAngle >= Math.PI - offset) {
			direction = Direction.SOUTH;
		}
		else if(horizontalAngle > 6 * Math.PI / 8 - offset) {
			direction = Direction.SOUTH_WEST;
		}
		else if(horizontalAngle >= 4 * Math.PI / 8 - offset) {
			direction = Direction.WEST;
		}
		else if(horizontalAngle > 2 * Math.PI / 8 - offset) {
			direction = Direction.NORTH_WEST;
		}
		else if(horizontalAngle >= -2 * Math.PI / 8 + offset) {
			direction = Direction.NORTH;
		}
		else if(horizontalAngle > -4 * Math.PI / 8 + offset) {
			direction = Direction.NORTH_EAST;
		}
		else if(horizontalAngle >= -6 * Math.PI / 8 + offset) {
			direction = Direction.EAST;
		}
		else if(horizontalAngle > -8 * Math.PI / 8 + offset) {
			direction = Direction.SOUTH_EAST;
		}
		else {
			direction = Direction.SOUTH;
		}
		
		double cutoffAngle = Math.PI / 4;
		if(verticalAngle > cutoffAngle) {
			direction = Direction.DOWN;
		}
		else if(verticalAngle < -cutoffAngle) {
			direction = Direction.UP;
		}
		
		return direction;
	}
	
	public enum Direction {
		UP, DOWN, NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST
	}
}
