package com.laton95.runemysteries.state.properties;

import com.laton95.runemysteries.enums.EnumCorner;
import net.minecraft.state.EnumProperty;

public class ModBlockStateProperties {
	
	public static final EnumProperty CORNER = EnumProperty.create("corner", EnumCorner.class);
}
