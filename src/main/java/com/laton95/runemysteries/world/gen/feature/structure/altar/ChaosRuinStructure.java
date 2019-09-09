package com.laton95.runemysteries.world.gen.feature.structure.altar;

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

public class ChaosRuinStructure extends RuinStructure {
	
	public static ResourceLocation CHAOS_RUIN = new ResourceLocation(RuneMysteries.MOD_ID, "ruin/chaos_ruin");
	
	public ChaosRuinStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> function) {
		super(function, EnumRuneType.CHAOS);
	}
	
	@Override
	protected void addPieces(List<StructurePiece> components, TemplateManager templateManager, int chunkX, int chunkZ, Random rand) {
		BlockPos pos = new BlockPos(chunkX * 16, rand.nextInt(82) + 27, chunkZ * 16);
		IslandRuinPieces.addPieces(templateManager, pos, components, CHAOS_RUIN, IslandRuinPieces.NETHER_ISLAND, rune);
	}
}
