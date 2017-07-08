package com.laton95.runemysteries.world;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.css.ElementCSSInlineStyle;

import java.util.Random;

import com.google.common.collect.Lists;
import com.laton95.runemysteries.init.WorldGenRegistry;
import com.laton95.runemysteries.reference.ConfigReference;
import com.laton95.runemysteries.reference.WorldGenReference;
import com.laton95.runemysteries.utility.LogHelper;
import com.laton95.runemysteries.utility.WorldHelper;

import it.unimi.dsi.fastutil.Arrays;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenRuneAltar extends MapGenStructure {
	private final List<Biome.SpawnListEntry> runeAltarSpawnList;
    private boolean foundLocations = false;
    private AltarTracker altarTracker;
    
    public MapGenRuneAltar() {
		this.runeAltarSpawnList = Lists.<Biome.SpawnListEntry>newArrayList();
		altarTracker = ChunkGenerator.altarTracker;
	}

	public String getStructureName() {
		return "RuneAltar";
	}

	protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
		if (!this.foundLocations)
        {
            this.findLocations();
            this.foundLocations = true;
        }
		
		//return altarTracker.isInChunks(new ChunkPos(chunkX, chunkZ));
        return altarTracker.isCloseToChunks(new ChunkPos(chunkX, chunkZ));
	}

	public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored) {
		int i = 1000;
        int j = pos.getX() >> 4;
        int k = pos.getZ() >> 4;

        for (int l = 0; l <= 1000; ++l)
        {
            for (int i1 = -l; i1 <= l; ++i1)
            {
                boolean flag = i1 == -l || i1 == l;

                for (int j1 = -l; j1 <= l; ++j1)
                {
                    boolean flag1 = j1 == -l || j1 == l;

                    if (flag || flag1)
                    {
                        int k1 = j + i1;
                        int l1 = k + j1;

                        if (this.canSpawnStructureAtCoords(k1, l1) && (!findUnexplored || !worldIn.isChunkGeneratedAt(k1, l1)))
                        {
                            return new BlockPos((k1 << 4) + 8, 64, (l1 << 4) + 8);
                        }
                    }
                }
            }
        }

        return null;
	}

	protected StructureStart getStructureStart(int chunkX, int chunkZ) {
		return new MapGenRuneAltar.Start(this.world, this.rand, chunkX, chunkZ);
	}

	/**
	 * returns possible spawns for rune altars
	 */
	public List<Biome.SpawnListEntry> getSpawnList() {
		return this.runeAltarSpawnList;
	}

	public static class Start extends StructureStart {
		public Start() {
		}

		public Start(World worldIn, Random random, int chunkX, int chunkZ) {
			this(worldIn, random, chunkX, chunkZ, worldIn.getBiome(new BlockPos(chunkX * 16 + 8, 0, chunkZ * 16 + 8)));
		}

		public Start(World worldIn, Random random, int chunkX, int chunkZ, Biome biomeIn) {
			super(chunkX, chunkZ);
			
			String altar = ChunkGenerator.altarTracker.getAltar(new ChunkPos(chunkX, chunkZ));
			
			if (ChunkGenerator.altarTracker.altarPlacedMap.get(altar) == false) {
				ComponentRuneAltar componentRuneAltar = new ComponentRuneAltar(random, chunkX * 16, chunkZ * 16, altar);
				
				componentRuneAltar.offsetToAverageGroundLevel(worldIn, componentRuneAltar.getBoundingBox(), -1);
				BlockPos blockpos = new BlockPos(componentRuneAltar.getBoundingBox().minX, componentRuneAltar.getBoundingBox().minY, componentRuneAltar.getBoundingBox().minZ);
				if (WorldHelper.determineFlatness(worldIn, blockpos, componentRuneAltar.getBoundingBox().getXSize(), componentRuneAltar.getBoundingBox().getYSize(), componentRuneAltar.getBoundingBox().getZSize()) > WorldGenReference.structureFlatnessTolerance && WorldGenReference.getBiomeList(altar).contains(biomeIn)) {
					ChunkGenerator.altarTracker.altarPlacedMap.put(altar, true);
					ChunkGenerator.altarTracker.updateAltar(altar, blockpos);
					ChunkGenerator.altarTracker.printAltar(altar);
					this.components.add(componentRuneAltar);
				} else componentRuneAltar = null;
			}
			
			
			this.updateBoundingBox();
		}
	}

	public void findLocations() {
		int altarRange = ConfigReference.runeAltarRange;
		
		LinkedList<Biome> outStandingAltarSpawnBiomes = new LinkedList<Biome>();
		outStandingAltarSpawnBiomes.addAll(WorldGenReference.allAltarSpawnBiomes);
		outStandingAltarSpawnBiomes.removeAll(WorldGenReference.chaosAltarSpawnBiomes);
		outStandingAltarSpawnBiomes.removeAll(WorldGenReference.cosmicAltarSpawnBiomes);
		
		List<String> genericAltars = new ArrayList<>();
		genericAltars.add("airaltar");
		genericAltars.add("mindaltar");
		genericAltars.add("earthaltar");
		genericAltars.add("bodyaltar");
		genericAltars.add("lawaltar");
		
		List<String> swampAltars = new ArrayList<>();
		swampAltars.add("wateraltar");
		swampAltars.add("bloodaltar");
		
		List<String> desertAltars = new ArrayList<>();
		desertAltars.add("firealtar");
		desertAltars.add("soulaltar");
		
		List<String> outStandingAltars = new ArrayList<>();
		outStandingAltars.addAll(genericAltars);
		outStandingAltars.addAll(swampAltars);
		outStandingAltars.addAll(desertAltars);
		outStandingAltars.add("naturealtar");
		outStandingAltars.add("astralaltar");
		outStandingAltars.add("deathaltar");
		
		while (!outStandingAltarSpawnBiomes.isEmpty()) {
			BlockPos blockpos = this.world.getBiomeProvider().findBiomePosition(0,0,altarRange, outStandingAltarSpawnBiomes, world.rand);
			
			if (blockpos != null) {
				Biome biome = world.getBiome(blockpos);
				if (WorldGenReference.genericAltarSpawnBiomes.contains(biome)) {
					altarTracker.addAltar(genericAltars.get(0), new ChunkPos(blockpos));
					LogHelper.info("Found " + genericAltars.get(0));
					outStandingAltars.remove(genericAltars.get(0));
					genericAltars.remove(0);
					if (genericAltars.isEmpty()) {
						outStandingAltarSpawnBiomes.removeAll(WorldGenReference.genericAltarSpawnBiomes);
					}
				} else if (WorldGenReference.swampAltarSpawnBiomes.contains(biome)) {
					altarTracker.addAltar(swampAltars.get(0), new ChunkPos(blockpos));
					LogHelper.info("Found " + swampAltars.get(0));
					outStandingAltars.remove(swampAltars.get(0));
					swampAltars.remove(0);
					if (swampAltars.isEmpty()) {
						outStandingAltarSpawnBiomes.removeAll(WorldGenReference.swampAltarSpawnBiomes);
					}
				} else if (WorldGenReference.desertAltarSpawnBiomes.contains(biome)) {
					altarTracker.addAltar(desertAltars.get(0), new ChunkPos(blockpos));
					LogHelper.info("Found " + desertAltars.get(0));
					outStandingAltars.remove(desertAltars.get(0));
					desertAltars.remove(0);
					if (desertAltars.isEmpty()) {
						outStandingAltarSpawnBiomes.removeAll(WorldGenReference.desertAltarSpawnBiomes);
					}
				} else if (WorldGenReference.natureAltarSpawnBiomes.contains(biome)) {
					String name = "naturealtar";
					LogHelper.info("Found " + name);
					outStandingAltars.remove(name);
					altarTracker.addAltar(name, new ChunkPos(blockpos));
					outStandingAltarSpawnBiomes.removeAll(WorldGenReference.natureAltarSpawnBiomes);
				} else if (WorldGenReference.astralAltarSpawnBiomes.contains(biome)) {
					String name = "astralaltar";
					LogHelper.info("Found " + name);
					outStandingAltars.remove(name);
					altarTracker.addAltar(name, new ChunkPos(blockpos));
					outStandingAltarSpawnBiomes.removeAll(WorldGenReference.astralAltarSpawnBiomes);
				} else if (WorldGenReference.deathAltarSpawnBiomes.contains(biome)) {
					String name = "deathaltar";
					LogHelper.info("Found " + name);
					outStandingAltars.remove(name);
					altarTracker.addAltar(name, new ChunkPos(blockpos));
					outStandingAltarSpawnBiomes.removeAll(WorldGenReference.deathAltarSpawnBiomes);
				}
			} else {
				LogHelper.info("Could not find biome, placing altar randomly");
				blockpos = new BlockPos(world.rand.nextInt(altarRange*2) - altarRange, 0, world.rand.nextInt(altarRange*2) - altarRange);
				String name = outStandingAltars.get(0);
				LogHelper.info("Found " + name);
				outStandingAltars.remove(name);
				altarTracker.addAltar(name, new ChunkPos(blockpos));
				
				if(outStandingAltars.isEmpty()) {
					outStandingAltarSpawnBiomes.clear();
				}
			}
		}
		
        altarTracker.printAltarChunks();
	}
}