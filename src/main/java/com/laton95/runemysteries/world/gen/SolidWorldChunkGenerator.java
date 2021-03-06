package com.laton95.runemysteries.world.gen;

import com.laton95.runemysteries.world.gen.feature.structure.temple.RuneTempleStructure;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;

public class SolidWorldChunkGenerator extends ChunkGenerator<SolidGenerationSettings> {
	
	public SolidWorldChunkGenerator(IWorld p_i49954_1_, BiomeProvider p_i49954_2_, SolidGenerationSettings p_i49954_3_) {
		super(p_i49954_1_, p_i49954_2_, p_i49954_3_);
	}
	
	@Override
	public void func_225551_a_(WorldGenRegion p_225551_1_, IChunk p_225551_2_) {
	
	}
	
	@Override
	public boolean hasStructure(Biome biomeIn, Structure<? extends IFeatureConfig> structureIn) {
		return structureIn instanceof RuneTempleStructure;
	}
	
	@Override
	public int getGroundHeight() {
		return 255;
	}
	
	@Override
	public void makeBase(IWorld world, IChunk chunk) {
		BlockPos.Mutable pos = new BlockPos.Mutable();
		BlockState state = this.settings.getDefaultBlock();
		Heightmap oceanHeightmap = chunk.getHeightmap(Heightmap.Type.OCEAN_FLOOR_WG);
		Heightmap surfaceHeightmap = chunk.getHeightmap(Heightmap.Type.WORLD_SURFACE_WG);
		
		for(int y = 0; y <= this.settings.getHeight(); ++y) {
			for(int x = 0; x < 16; ++x) {
				for(int z = 0; z < 16; ++z) {
					chunk.setBlockState(pos.setPos(x, y, z), state, false);
					oceanHeightmap.update(x, y, z, state);
					surfaceHeightmap.update(x, y, z, state);
				}
			}
		}
		
	}
	
	@Override
	public int func_222529_a(int i, int i1, Heightmap.Type type) {
		return 0;
	}
}
