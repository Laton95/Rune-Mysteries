package com.laton95.runemysteries.world.chunkGenerators;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.google.common.collect.ImmutableList;
import com.laton95.runemysteries.reference.NamesReference;
import com.laton95.runemysteries.world.mapGenerators.MapGenRuneTemple;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.init.Blocks;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import scala.annotation.implicitNotFound;

public class ChunkGeneratorNature extends ChunkGeneratorSurfaceWorld {

	private final static List<Biome.SpawnListEntry> MOB_SPAWNS = ImmutableList.of(
			new SpawnListEntry(EntityParrot.class, 20, 1, 4)
			);
	
	public ChunkGeneratorNature(World worldIn, long seed) {
		super(worldIn, seed, Blocks.STONE.getDefaultState(), NamesReference.worldGenStrings.NATURE, 14, 8, MOB_SPAWNS);
	}
}