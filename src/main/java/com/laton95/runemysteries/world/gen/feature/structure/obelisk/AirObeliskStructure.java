package com.laton95.runemysteries.world.gen.feature.structure.obelisk;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.world.gen.feature.structure.ModifiableRarityStructure;
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

import java.util.Random;
import java.util.function.Function;

public class AirObeliskStructure extends ModifiableRarityStructure {
	
	public static final ResourceLocation AIR_OBELISK = new ResourceLocation(RuneMysteries.MOD_ID, "obelisk/air_obelisk");
	
	public AirObeliskStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> function) {
		super(function);
	}
	
	@Override
	protected int getDistance() {
		return 16;
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
		return "Air_obelisk";
	}
	
	@Override
	public int getSize() {
		return 1;
	}
	
	@Override
	protected int getSeedModifier() {
		return 13794389;
	}
	
	public class Start extends StructureStart {
		
		public Start(Structure<?> structure, int chunkX, int chunkZ, Biome biome, MutableBoundingBox boundingBox, int reference, long seed) {
			super(structure, chunkX, chunkZ, biome, boundingBox, reference, seed);
		}
		
		public void init(ChunkGenerator<?> generator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome) {
			int i = chunkX * 16;
			int j = chunkZ * 16;
			BlockPos pos = new BlockPos(i + rand.nextInt(32), 90, j + rand.nextInt(32));
			ObeliskPieces.addPieces(templateManager, pos, this.components, AIR_OBELISK, -1);
			this.recalculateStructureSize();
		}
	}
}
