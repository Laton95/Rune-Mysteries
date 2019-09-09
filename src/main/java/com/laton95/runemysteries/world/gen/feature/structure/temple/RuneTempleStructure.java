package com.laton95.runemysteries.world.gen.feature.structure.temple;

import com.laton95.runemysteries.enums.EnumRuneType;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;
import java.util.function.Function;

public abstract class RuneTempleStructure extends Structure<NoFeatureConfig> {
	
	private final EnumRuneType rune;
	
	private final int height;
	
	public RuneTempleStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> config, EnumRuneType rune, int height) {
		super(config);
		this.rune = rune;
		this.height = height;
	}
	
	@Override
	public boolean hasStartAt(ChunkGenerator<?> chunkGen, Random rand, int chunkPosX, int chunkPosZ) {
		return chunkPosX == 0 && chunkPosZ == 0;
	}
	
	@Override
	public IStartFactory getStartFactory() {
		return Start::new;
	}
	
	@Override
	public String getStructureName() {
		return "runemysteries:" + rune.name().toLowerCase() + "_temple";
	}
	
	@Override
	public int getSize() {
		return 4;
	}
	
	public class Start extends StructureStart {
		
		public Start(Structure<?> structure, int chunkX, int chunkZ, Biome biome, MutableBoundingBox boundingBox, int reference, long seed) {
			super(structure, chunkX, chunkZ, biome, boundingBox, reference, seed);
		}
		
		@Override
		public void init(ChunkGenerator<?> generator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome) {
			int i = chunkX * 16;
			int j = chunkZ * 16;
			BlockPos pos = new BlockPos(i, height, j);
			RuneTemplePieces.addPieces(templateManager, pos, this.components, rune);
			this.recalculateStructureSize();
		}
	}
}
