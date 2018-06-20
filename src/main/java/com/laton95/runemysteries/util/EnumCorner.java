package com.laton95.runemysteries.util;

import net.minecraft.util.IStringSerializable;

public enum EnumCorner implements IStringSerializable
{
	NORTH_WEST("north_west"),
	NORTH_EAST("north_east"),
	SOUTH_WEST("south_west"),
	SOUTH_EAST("south_east");
	
	private final String name;
	
	private EnumCorner(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
}
