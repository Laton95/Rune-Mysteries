package com.laton95.runemysteries.world.gen.feature.structure.obelisk;

import com.laton95.runemysteries.config.Config;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.world.gen.feature.structure.ModifiableRarityStructure;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
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
import java.util.function.Function;

public abstract class ObeliskStructure extends ModifiableRarityStructure {
	
	private final EnumRuneType rune;
	
	private final int seedModifier;
	
	private final ResourceLocation obelisk;
	
	public ObeliskStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> function, int seedModifier, EnumRuneType rune, ResourceLocation obelisk) {
		super(function);
		this.rune = rune;
		this.seedModifier = seedModifier;
		this.obelisk = obelisk;
	}
	
	@Override
	public String getStructureName() {
		return "runemysteries:" + rune.name().toLowerCase() + "_obelisk";
	}
	
	@Override
	public int getSize() {
		return 1;
	}
	
	@Override
	protected int getSeedModifier() {
		return seedModifier;
	}
	
	protected abstract int getHeight(Biome biome);
	
	@Nullable
	@Override
	public BlockPos findNearest(World worldIn, ChunkGenerator<? extends GenerationSettings> chunkGenerator, BlockPos pos, int radius, boolean p_211405_5_) {
		return Config.generateObelisks ? super.findNearest(worldIn, chunkGenerator, pos, radius, p_211405_5_) : null;
	}
	
	@Override
	public IStartFactory getStartFactory() {
		return Start::new;
	}
	
	public class Start extends StructureStart {
		
		public Start(Structure<?> structure, int chunkX, int chunkZ, MutableBoundingBox boundingBox, int reference, long seed) {
			super(structure, chunkX, chunkZ, boundingBox, reference, seed);
		}
		
		public void init(ChunkGenerator<?> generator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome) {
			int x = chunkX * 16;
			int z = chunkZ * 16;
			BlockPos pos = new BlockPos(x, 90, z);
			ObeliskPieces.addPieces(templateManager, pos, this.components, obelisk, getHeight(biome));
			this.recalculateStructureSize();
		}
	}
}
