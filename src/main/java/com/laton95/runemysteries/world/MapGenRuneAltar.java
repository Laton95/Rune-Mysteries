package com.laton95.runemysteries.world;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.css.ElementCSSInlineStyle;

import java.util.Random;

import com.google.common.collect.Lists;
import com.laton95.runemysteries.reference.ConfigReference;
import com.laton95.runemysteries.reference.WorldGenReference;
import com.laton95.runemysteries.utility.WorldHelper;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenRuneAltar extends MapGenStructure {
	private final List<Biome.SpawnListEntry> runeAltarSpawnList;

	public MapGenRuneAltar() {
		this.runeAltarSpawnList = Lists.<Biome.SpawnListEntry>newArrayList();
	}

	public String getStructureName() {
		return "RuneAltar";
	}

	protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
		boolean biomeAppropriate = false;
		
		if (WorldGenReference.allAltarSpawnBiomes.contains(this.world.getBiome(new BlockPos(chunkX*16 + 8, 0, chunkZ*16 + 8)))) {
			biomeAppropriate = true;
		}
		
		if (biomeAppropriate) {
			this.rand.setSeed(this.world.getSeed() * chunkX * chunkZ);
			if (this.rand.nextInt(100) <= ConfigReference.runeAltarRarity) {
				return true;
			} else return false;
		} else return false;
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
	 * returns possible spawns for scattered features
	 */
	public List<Biome.SpawnListEntry> getScatteredFeatureSpawnList() {
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

			ComponentRuneAltar componentRuneAltar = new ComponentRuneAltar(random, chunkX * 16, chunkZ * 16);
			
			componentRuneAltar.offsetToAverageGroundLevel(worldIn, componentRuneAltar.getBoundingBox(), -1);
			BlockPos blockpos = new BlockPos(componentRuneAltar.getBoundingBox().minX, componentRuneAltar.getBoundingBox().minY, componentRuneAltar.getBoundingBox().minZ);
			if (WorldHelper.determineFlatness(worldIn, blockpos, componentRuneAltar.getBoundingBox().getXSize(), componentRuneAltar.getBoundingBox().getYSize(), componentRuneAltar.getBoundingBox().getZSize()) > WorldGenReference.structureFlatnessTolerance) {
				this.components.add(componentRuneAltar);
			} else componentRuneAltar = null;
			
			this.updateBoundingBox();
		}
	}
}