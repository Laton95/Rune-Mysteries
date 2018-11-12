package com.laton95.runemysteries.world;

import com.laton95.runemysteries.world.mapGenerators.*;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

@Mod.EventBusSubscriber
public class WorldGenerator implements IWorldGenerator
{
	
	public static RuinTracker ruinTracker;
	
	private MapGenMonolith monolithGenerator = new MapGenMonolith();
	
	private MapGenElementalObelisk obeliskGenerator = new MapGenElementalObelisk();
	
	private MapGenRuinAir airRuinGenerator = new MapGenRuinAir();
	
	private MapGenRuinAstral astralRuinGenerator = new MapGenRuinAstral();
	
	private MapGenRuinBlood bloodRuinGenerator = new MapGenRuinBlood();
	
	private MapGenRuinBody bodyRuinGenerator = new MapGenRuinBody();
	
	private MapGenRuinChaos chaosRuinGenerator = new MapGenRuinChaos();
	
	private MapGenRuinCosmic cosmicRuinGenerator = new MapGenRuinCosmic();
	
	private MapGenRuinDeath deathRuinGenerator = new MapGenRuinDeath();
	
	private MapGenRuinEarth earthRuinGenerator = new MapGenRuinEarth();
	
	private MapGenRuinFire fireRuinGenerator = new MapGenRuinFire();
	
	private MapGenRuinLaw lawRuinGenerator = new MapGenRuinLaw();
	
	private MapGenRuinMind mindRuinGenerator = new MapGenRuinMind();
	
	private MapGenRuinNature natureRuinGenerator = new MapGenRuinNature();
	
	private MapGenRuinSoul soulRuinGenerator = new MapGenRuinSoul();
	
	private MapGenRuinWater waterRuinGenerator = new MapGenRuinWater();
	
	private MapGenRuinOurania ouraniaRuinGenerator = new MapGenRuinOurania();
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		if(!world.isRemote)
		{
			switch(world.provider.getDimensionType())
			{
				case OVERWORLD:
					// Ores
					OreGenerator.finiteEssenceGen(world, random, chunkX, chunkZ);
					
					// Structures
					monolithGenerator.generate(world, chunkX, chunkZ, new ChunkPrimer());
					obeliskGenerator.generate(world, chunkX, chunkZ, new ChunkPrimer());
					
					airRuinGenerator.generate(world, chunkX, chunkZ, new ChunkPrimer());
					astralRuinGenerator.generate(world, chunkX, chunkZ, new ChunkPrimer());
					bloodRuinGenerator.generate(world, chunkX, chunkZ, new ChunkPrimer());
					bodyRuinGenerator.generate(world, chunkX, chunkZ, new ChunkPrimer());
					deathRuinGenerator.generate(world, chunkX, chunkZ, new ChunkPrimer());
					earthRuinGenerator.generate(world, chunkX, chunkZ, new ChunkPrimer());
					fireRuinGenerator.generate(world, chunkX, chunkZ, new ChunkPrimer());
					lawRuinGenerator.generate(world, chunkX, chunkZ, new ChunkPrimer());
					mindRuinGenerator.generate(world, chunkX, chunkZ, new ChunkPrimer());
					natureRuinGenerator.generate(world, chunkX, chunkZ, new ChunkPrimer());
					soulRuinGenerator.generate(world, chunkX, chunkZ, new ChunkPrimer());
					waterRuinGenerator.generate(world, chunkX, chunkZ, new ChunkPrimer());
					ouraniaRuinGenerator.generate(world, chunkX, chunkZ, new ChunkPrimer());
					break;
				case NETHER:
					// Ores
					
					// Structures
					chaosRuinGenerator.generate(world, chunkX, chunkZ, new ChunkPrimer());
					break;
				case THE_END:
					// Ores
					
					// Structures
					cosmicRuinGenerator.generate(world, chunkX, chunkZ, new ChunkPrimer());
					break;
				default:
					break;
			}
		}
	}
	
	@SubscribeEvent
	public void populate(PopulateChunkEvent.Populate event)
	{
		if((event.getType() == EventType.DUNGEON || event.getType() == EventType.GLOWSTONE) && !event.getWorld().isRemote)
		{
			switch(event.getWorld().provider.getDimensionType())
			{
				case OVERWORLD:
					// Structures
					obeliskGenerator.generateStructure(event.getWorld(), event.getRand(), new ChunkPos(event.getChunkX(), event.getChunkZ()));
					
					airRuinGenerator.generateStructure(event.getWorld(), event.getRand(), new ChunkPos(event.getChunkX(), event.getChunkZ()));
					astralRuinGenerator.generateStructure(event.getWorld(), event.getRand(), new ChunkPos(event.getChunkX(), event.getChunkZ()));
					bloodRuinGenerator.generateStructure(event.getWorld(), event.getRand(), new ChunkPos(event.getChunkX(), event.getChunkZ()));
					bodyRuinGenerator.generateStructure(event.getWorld(), event.getRand(), new ChunkPos(event.getChunkX(), event.getChunkZ()));
					deathRuinGenerator.generateStructure(event.getWorld(), event.getRand(), new ChunkPos(event.getChunkX(), event.getChunkZ()));
					earthRuinGenerator.generateStructure(event.getWorld(), event.getRand(), new ChunkPos(event.getChunkX(), event.getChunkZ()));
					fireRuinGenerator.generateStructure(event.getWorld(), event.getRand(), new ChunkPos(event.getChunkX(), event.getChunkZ()));
					lawRuinGenerator.generateStructure(event.getWorld(), event.getRand(), new ChunkPos(event.getChunkX(), event.getChunkZ()));
					mindRuinGenerator.generateStructure(event.getWorld(), event.getRand(), new ChunkPos(event.getChunkX(), event.getChunkZ()));
					natureRuinGenerator.generateStructure(event.getWorld(), event.getRand(), new ChunkPos(event.getChunkX(), event.getChunkZ()));
					soulRuinGenerator.generateStructure(event.getWorld(), event.getRand(), new ChunkPos(event.getChunkX(), event.getChunkZ()));
					waterRuinGenerator.generateStructure(event.getWorld(), event.getRand(), new ChunkPos(event.getChunkX(), event.getChunkZ()));
					ouraniaRuinGenerator.generateStructure(event.getWorld(), event.getRand(), new ChunkPos(event.getChunkX(), event.getChunkZ()));
					break;
				case NETHER:
					chaosRuinGenerator.generateStructure(event.getWorld(), event.getRand(), new ChunkPos(event.getChunkX(), event.getChunkZ()));
					break;
				default:
					break;
			}
		}
	}
	
	@SubscribeEvent
	public void populateEnd(PopulateChunkEvent.Post event)
	{
		switch(event.getWorld().provider.getDimensionType())
		{
			case THE_END:
				cosmicRuinGenerator.generateStructure(event.getWorld(), event.getRand(), new ChunkPos(event.getChunkX(), event.getChunkZ()));
				break;
			default:
				break;
		}
	}
}
