package com.laton95.runemysteries.world.mapGenerators;

import com.google.common.collect.Lists;
import com.laton95.runemysteries.world.structureComponents.ComponentMine;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

import java.util.List;
import java.util.Random;

public class MapGenMine extends MapGenStructure
{
	
	private final List<Biome.SpawnListEntry> runeTempleSpawnList;
	
	public MapGenMine(World world)
	{
		this.world = world;
		runeTempleSpawnList = Lists.newArrayList();
	}
	
	@Override
	public String getStructureName()
	{
		return "EssenceMine";
	}
	
	@Override
	public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored)
	{
		return null;
	}
	
	@Override
	protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ)
	{
		return chunkX == 0 && chunkZ == 0 || chunkX == -1 && chunkZ == 0 || chunkX == 0 && chunkZ == -1 || chunkX == -1 && chunkZ == -1;
	}
	
	@Override
	protected StructureStart getStructureStart(int chunkX, int chunkZ)
	{
		return new MapGenMine.Start(world, rand, chunkX, chunkZ);
	}
	
	/**
	 * returns possible spawns for rune temples
	 */
	public List<Biome.SpawnListEntry> getSpawnList()
	{
		return runeTempleSpawnList;
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
			ComponentMine componentMine = new ComponentMine(chunkX, chunkZ, new BlockPos(-5, 87, -6), 63);
			components.add(componentMine);
			
			updateBoundingBox();
		}
	}
}
