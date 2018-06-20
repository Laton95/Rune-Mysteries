package com.laton95.runemysteries.world.mapGenerators;

import com.laton95.runemysteries.world.structureComponents.ComponentCosmicTemple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.StructureStart;

import java.util.Random;

public class MapGenCosmicTemple extends MapGenRuneTemple
{
	
	public MapGenCosmicTemple(World world)
	{
		super(world);
	}
	
	@Override
	protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ)
	{
		return chunkX == 0 && chunkZ == 0 || chunkX == -1 && chunkZ == 0 || chunkX == 0 && chunkZ == -1 || chunkX == -1 && chunkZ == -1 || chunkX == -2 && chunkZ == 0 || chunkX == -2 && chunkZ == -1 || chunkX == -1 && chunkZ == -2 || chunkX == 0 && chunkZ == -2 || chunkX == 1 && chunkZ == -1 || chunkX == 1 && chunkZ == 0 || chunkX == 0 && chunkZ == 1 || chunkX == -1 && chunkZ == 1;
	}
	
	@Override
	protected StructureStart getStructureStart(int chunkX, int chunkZ)
	{
		return new MapGenCosmicTemple.Start(world, rand, chunkX, chunkZ);
	}
	
	public static class Start extends StructureStart
	{
		
		public Start()
		{
		}
		
		public Start(World worldIn, Random random, int chunkX, int chunkZ)
		{
			this(worldIn, random, chunkX, chunkZ, worldIn.getBiome(new BlockPos(chunkX * 16 + 8, 0, chunkZ * 16 + 8)));
			
		}
		
		public Start(World worldIn, Random random, int chunkX, int chunkZ, Biome biomeIn)
		{
			super(chunkX, chunkZ);
			ComponentCosmicTemple componentCosmicTemple = new ComponentCosmicTemple(chunkX, chunkZ);
			components.add(componentCosmicTemple);
			updateBoundingBox();
		}
	}
}
