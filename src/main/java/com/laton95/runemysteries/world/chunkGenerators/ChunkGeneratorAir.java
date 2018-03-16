package com.laton95.runemysteries.world.chunkGenerators;

import com.google.common.collect.ImmutableList;
import com.laton95.runemysteries.reference.NamesReference;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;

import java.util.List;

public class ChunkGeneratorAir extends ChunkGeneratorSurfaceWorld
{
	
	private final static List<Biome.SpawnListEntry> MOB_SPAWNS = ImmutableList.of(new SpawnListEntry(EntityRabbit.class, 20, 2, 6));
	
	public ChunkGeneratorAir(World worldIn, long seed)
	{
		super(worldIn, seed, Blocks.STONE.getDefaultState(), NamesReference.worldGenStrings.AIR, 5, 3, MOB_SPAWNS);
	}
}
