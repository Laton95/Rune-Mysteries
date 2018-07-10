package com.laton95.runemysteries.world.mapGenerators;

import com.laton95.runemysteries.config.ModConfig;
import com.laton95.runemysteries.reference.WorldGenReference;
import com.laton95.runemysteries.util.LogHelper;
import com.laton95.runemysteries.util.WorldHelper;
import com.laton95.runemysteries.world.structureComponents.ComponentElementalObelisks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

import javax.annotation.Nullable;
import java.util.Random;

public class MapGenElementalObelisk extends MapGenStructure
{
	private final int maxDistanceBetweenElementalObelisks;
	
	private final boolean spawnObelisks;
	
	private final int seed = 81435019;
	
	public MapGenElementalObelisk()
	{
		maxDistanceBetweenElementalObelisks = ModConfig.WORLD_GENERATION.elemental_obelisks.elementalObeliskMaxDistance;
		spawnObelisks = ModConfig.WORLD_GENERATION.elemental_obelisks.generateElementalObelisks;
	}
	
	@Override
	public String getStructureName()
	{
		return "elemental_obelisk";
	}
	
	@Nullable
	@Override
	public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored)
	{
		this.world = worldIn;
		return findNearestStructurePosBySpacing(worldIn, this, pos, maxDistanceBetweenElementalObelisks, 8, seed, false, 100, findUnexplored);
		
	}
	
	@Override
	protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ)
	{
		if(!spawnObelisks)
		{
			return false;
		}
		
		int i = chunkX;
		int j = chunkZ;
		
		if(chunkX < 0)
		{
			chunkX -= maxDistanceBetweenElementalObelisks - 1;
		}
		
		if(chunkZ < 0)
		{
			chunkZ -= maxDistanceBetweenElementalObelisks - 1;
		}
		
		int k = chunkX / maxDistanceBetweenElementalObelisks;
		int l = chunkZ / maxDistanceBetweenElementalObelisks;
		Random random = this.world.setRandomSeed(k, l, seed);
		k = k * maxDistanceBetweenElementalObelisks;
		l = l * maxDistanceBetweenElementalObelisks;
		k = k + random.nextInt(maxDistanceBetweenElementalObelisks - 8);
		l = l + random.nextInt(maxDistanceBetweenElementalObelisks - 8);
		
		if(i == k && j == l)
		{
			Biome biome = this.world.getBiomeProvider().getBiome(new BlockPos(i * 16 + 8, 0, j * 16 + 8));
			
			if(biome == null)
			{
				return false;
			}
			
			return !WorldHelper.biomeIsOfType(WorldGenReference.ELEMENTAL_OBELISK_BIOMES_AVOID, biome);
		}
		
		return false;
	}
	
	@Override
	protected StructureStart getStructureStart(int chunkX, int chunkZ)
	{
		return new Start(this.world, this.rand, chunkX, chunkZ);
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
			boolean generateBiomeSpecific = random.nextInt(4) != 0;
			
			if(WorldHelper.biomeIsOfType(WorldGenReference.AIR_OBELISK_BIOMES, biomeIn) && generateBiomeSpecific)
			{
				//Generate air obelisk
				LogHelper.info("air obelisk around: " + chunkX * 16 + "," + chunkZ * 16);
				ComponentElementalObelisks.ComponentAirObelisk obelisk = new ComponentElementalObelisks.ComponentAirObelisk(random, chunkX * 16, chunkZ * 16);
				this.components.add(obelisk);
			}
			else if(WorldHelper.biomeIsOfType(WorldGenReference.WATER_OBELISK_BIOMES, biomeIn) && generateBiomeSpecific)
			{
				LogHelper.info("water obelisk around: " + chunkX * 16 + "," + chunkZ * 16);
				//Generate water obelisk
				ComponentElementalObelisks.ComponentWaterObelisk obelisk = new ComponentElementalObelisks.ComponentWaterObelisk(random, chunkX * 16, chunkZ * 16);
				this.components.add(obelisk);
			}
			else
			{
				boolean generateFire = random.nextBoolean();
				
				if(generateFire)
				{
					//Generate fire obelisk
					ComponentElementalObelisks.ComponentFireObelisk obelisk = new ComponentElementalObelisks.ComponentFireObelisk(random, chunkX * 16, chunkZ * 16);
					this.components.add(obelisk);
				}
				else
				{
					//Generate earth obelisk
					ComponentElementalObelisks.ComponentEarthObelisk obelisk = new ComponentElementalObelisks.ComponentEarthObelisk(random, chunkX * 16, chunkZ * 16);
					this.components.add(obelisk);
				}
			}
			
			this.updateBoundingBox();
		}
	}
}
