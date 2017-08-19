package com.laton95.runemysteries.item;

import net.minecraft.util.IStringSerializable;

public class ItemRune extends RMModItem
{

	public ItemRune()
	{
		super("rune", true, EnumRuneType.class);
	}

	public static enum EnumRuneType implements IStringSerializable
	{
		AIR("air"), 
		ASTRAL("astral"), 
		BLOOD("blood"), 
		BODY("body"), 
		CHAOS("chaos"), 
		COSMIC("cosmic"), 
		DEATH("death"), 
		EARTH("earth"), 
		FIRE("fire"), 
		LAW("law"), 
		MIND("mind"), 
		NATURE("nature"), 
		SOUL("soul"), 
		WATER("water"),
		ESSENCE("essence");

		private final String name;

		private EnumRuneType(String name)
		{
			this.name = name;
		}

		@Override
		public String getName()
		{
			return name;
		}

		@Override
		public String toString()
		{
			return name;
		}
	}
}
