package com.laton95.runemysteries.world.chunkGenerators;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.laton95.runemysteries.reference.NamesReference;
import com.laton95.runemysteries.world.mapGenerators.MapGenRuneTemple;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenWaterlily;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ChunkGeneratorFire extends ChunkGeneratorOceanWorld {

	public ChunkGeneratorFire(World worldIn, long seed) {
		super(worldIn, seed, Blocks.STONE.getDefaultState(), Blocks.STONE.getDefaultState(), Blocks.LAVA.getDefaultState(), 85, 84, 83, NamesReference.worldGenStrings.WATER);
	}
}