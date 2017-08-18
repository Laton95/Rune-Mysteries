package com.laton95.runemysteries.item;

import net.minecraft.util.IStringSerializable;

public class ItemRune extends RMModItem
{

	public ItemRune(String name)
	{
		super(name, true, EnumRuneType.class);
	}

	public static enum EnumRuneType implements IStringSerializable
	{
		AIR("air", 0), 
		ASTRAL("astral", 1), 
		BLOOD("blood", 2), 
		BODY("body", 3), 
		CHAOS("chaos", 4), 
		COSMIC("cosmic", 5), 
		DEATH("death", 6), 
		EARTH("earth", 7), 
		FIRE("fire", 8), 
		LAW("law", 9), 
		MIND("mind", 10), 
		NATURE("nature", 11), 
		SOUL("soul", 12), 
		WATER("water", 13),
		ESSENCE("essence", 14);

		private final String name;
		private final int ID;

		private EnumRuneType(String name, int ID)
		{
			this.name = name;
			this.ID = ID;
		}

		@Override
		public String getName()
		{
			return name;
		}

		public int getID()
		{
			return ID;
		}

		@Override
		public String toString()
		{
			return name;
		}
	}
}
