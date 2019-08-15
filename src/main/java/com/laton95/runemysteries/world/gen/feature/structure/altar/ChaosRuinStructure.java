package com.laton95.runemysteries.world.gen.feature.structure.altar;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Function;

public class ChaosRuinStructure extends Structure<NoFeatureConfig> {
	
	public static ResourceLocation CHAOS_RUIN = new ResourceLocation(RuneMysteries.MOD_ID, "ruin/chaos_ruin");
	
	public ChaosRuinStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> function) {
		super(function);
	}
	
	@Override
	public boolean hasStartAt(ChunkGenerator<?> chunkGen, Random rand, int chunkPosX, int chunkPosZ) {
		return RuneMysteries.ruinManager.isRuinInChunk(EnumRuneType.CHAOS, chunkGen, new ChunkPos(chunkPosX, chunkPosZ));
	}
	
	@Nullable
	@Override
	public BlockPos findNearest(World world, ChunkGenerator<? extends GenerationSettings> chunkGenerator, BlockPos pos, int radius, boolean p_211405_5_) {
		return RuneMysteries.ruinManager.getRuinPosition(EnumRuneType.CHAOS, chunkGenerator);
	}
	
	@Override
	public IStartFactory getStartFactory() {
		return Start::new;
	}
	
	@Override
	public String getStructureName() {
		return "Chaos_ruin";
	}
	
	@Override
	public int getSize() {
		return 1;
	}
	
	public class Start extends StructureStart {
		
		public Start(Structure<?> structure, int chunkX, int chunkZ, Biome biome, MutableBoundingBox boundingBox, int reference, long seed) {
			super(structure, chunkX, chunkZ, biome, boundingBox, reference, seed);
		}
		
		public void init(ChunkGenerator<?> generator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome) {
			int i = chunkX * 16;
			int j = chunkZ * 16;
			BlockPos pos = new BlockPos(i, rand.nextInt(82) + 27, j);
			IslandRuinPieces.addPieces(templateManager, pos, this.components, CHAOS_RUIN, IslandRuinPieces.NETHER_ISLAND, EnumRuneType.CHAOS);
			this.recalculateStructureSize();
		}
	}
}
