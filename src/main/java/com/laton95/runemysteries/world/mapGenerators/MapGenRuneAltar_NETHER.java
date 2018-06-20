package com.laton95.runemysteries.world.mapGenerators;

import com.google.common.collect.Lists;
import com.laton95.runemysteries.util.LogHelper;
import com.laton95.runemysteries.world.AltarTracker;
import com.laton95.runemysteries.world.WorldGenerator;
import com.laton95.runemysteries.world.structureComponents.ComponentNetherAltar;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

import java.util.List;
import java.util.Random;

public class MapGenRuneAltar_NETHER extends MapGenStructure
{
	
	private final List<Biome.SpawnListEntry> runeAltarSpawnList;
	
	public MapGenRuneAltar_NETHER()
	{
		runeAltarSpawnList = Lists.newArrayList();
	}
	
	@Override
	public String getStructureName()
	{
		return "RuneAltarNether";
	}
	
	@Override
	public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored)
	{
		return null;
	}
	
	@Override
	protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ)
	{
		if(WorldGenerator.altarTracker != null)
		{
			if(!WorldGenerator.altarTracker.netherAltarsFound)
			{
				WorldGenerator.altarTracker.findNetherLocations(world);
			}
		}
		else
		{
			WorldGenerator.altarTracker = new AltarTracker();
			WorldGenerator.altarTracker.findNetherLocations(world);
		}
		
		return WorldGenerator.altarTracker.inGenerationRange(new ChunkPos(chunkX, chunkZ), -1, AltarTracker.Type.NETHER);
	}
	
	@Override
	protected StructureStart getStructureStart(int chunkX, int chunkZ)
	{
		return new MapGenRuneAltar_NETHER.Start(world, rand, chunkX, chunkZ);
	}
	
	/**
	 * returns possible spawns for rune altars
	 */
	public List<Biome.SpawnListEntry> getSpawnList()
	{
		return runeAltarSpawnList;
	}
	
	public static class Start extends StructureStart
	{
		
		public Start(World worldIn, Random random, int chunkX, int chunkZ)
		{
			this(worldIn, random, chunkX, chunkZ, worldIn.getBiome(new BlockPos(chunkX * 16 + 8, 0, chunkZ * 16 + 8)));
			
		}
		
		public Start(World worldIn, Random random, int chunkX, int chunkZ, Biome biomeIn)
		{
			super(chunkX, chunkZ);
			
			AltarTracker.RuneAltar altar = WorldGenerator.altarTracker.getAltar("chaos_altar");
			
			if(altar != null && !altar.isPlaced())
			{
				LogHelper.info("tst");
				int randX = random.nextInt(4) + 1;
				int randZ = random.nextInt(4) + 1;
				
				ComponentNetherAltar componentRuneAltar = new ComponentNetherAltar(random, chunkX * 16 + randX, chunkZ * 16 + randZ, altar.getName());
				
				// Altar generated
				altar.setPlaced(true);
				LogHelper.info(altar.toString());
				components.add(componentRuneAltar);
			}
			updateBoundingBox();
		}
	}
}
