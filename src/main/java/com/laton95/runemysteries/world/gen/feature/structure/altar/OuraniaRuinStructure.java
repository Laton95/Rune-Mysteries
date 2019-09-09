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

public class OuraniaRuinStructure extends RuinStructure {
	
	public static final ResourceLocation OURANIA_RUIN = new ResourceLocation(RuneMysteries.MOD_ID, "ruin/ourania_ruin");
	
	public static final ResourceLocation OURANIA_ROOM = new ResourceLocation(RuneMysteries.MOD_ID, "ourania_room");
	
	public OuraniaRuinStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> function) {
		super(function, EnumRuneType.OURANIA);
	}
	
	@Override
	protected void addPieces(List<StructurePiece> components, TemplateManager templateManager, int chunkX, int chunkZ, Random rand) {
		BlockPos pos = new BlockPos(chunkX * 16, 10, chunkZ * 16);
		UndergroundRuinPieces.addPieces(templateManager, pos, components, OURANIA_RUIN, OURANIA_ROOM, rune);
	}
}
