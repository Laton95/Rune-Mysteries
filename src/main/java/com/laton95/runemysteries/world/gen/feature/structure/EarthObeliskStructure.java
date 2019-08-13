package com.laton95.runemysteries.world.gen.feature.structure;

import com.laton95.runemysteries.RuneMysteries;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.function.Function;

public class EarthObeliskStructure extends ModifiableRarityStructure {
	
	public static final ResourceLocation EARTH_OBELISK = new ResourceLocation(RuneMysteries.MOD_ID, "obelisk/earth_obelisk");
	
	public EarthObeliskStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> function) {
		super(function);
	}
	
	@Override
	protected int getDistance() {
		return 32;
	}
	
	@Override
	protected int getSeparation() {
		return 1;
	}
	
	@Override
	public IStartFactory getStartFactory() {
		return Start::new;
	}
	
	@Override
	public String getStructureName() {
		return "earth_obelisk";
	}
	
	@Override
	public int getSize() {
		return 1;
	}
	
	@Override
	protected int getSeedModifier() {
		return 90161713;
	}
	
	public class Start extends StructureStart {
		
		public Start(Structure<?> structure, int p_i50678_2_, int p_i50678_3_, Biome p_i50678_4_, MutableBoundingBox p_i50678_5_, int p_i50678_6_, long p_i50678_7_) {
			super(structure, p_i50678_2_, p_i50678_3_, p_i50678_4_, p_i50678_5_, p_i50678_6_, p_i50678_7_);
		}
		
		public void init(ChunkGenerator<?> generator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome) {
			int i = chunkX * 16;
			int j = chunkZ * 16;
			BlockPos pos = new BlockPos(i, 90, j);
			BaseObeliskPieces.addPieces(templateManager, pos, this.components, EARTH_OBELISK, 40);
			this.recalculateStructureSize();
		}
	}
}
