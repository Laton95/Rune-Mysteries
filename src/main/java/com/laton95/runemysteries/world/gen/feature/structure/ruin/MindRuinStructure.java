package com.laton95.runemysteries.world.gen.feature.structure.ruin;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class MindRuinStructure extends RuinStructure {
	
	public static final ResourceLocation MIND_RUIN = new ResourceLocation(RuneMysteries.MOD_ID, "ruin/mind_ruin");
	
	public MindRuinStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> function) {
		super(function, EnumRuneType.MIND);
	}
	
	@Override
	protected void addPieces(List<StructurePiece> components, TemplateManager templateManager, int chunkX, int chunkZ, Random rand) {
		BlockPos pos = new BlockPos(chunkX * 16, 90, chunkZ * 16);
		SurfaceRuinPieces.addPieces(templateManager, pos, components, MIND_RUIN, SurfaceRuinPieces.DIRT_ISLAND, rune);
	}
}
