package com.laton95.runemysteries.world.dimension;

import com.laton95.runemysteries.enums.EnumRuneType;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;

public abstract class RuneTempleDimension extends Dimension {
	
	public final EnumRuneType runeType;
	
	public RuneTempleDimension(World world, DimensionType type, EnumRuneType runeType) {
		super(world, type);
		this.runeType = runeType;
	}
}
