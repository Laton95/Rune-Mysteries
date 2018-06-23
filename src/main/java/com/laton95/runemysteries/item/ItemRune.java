package com.laton95.runemysteries.item;

public class ItemRune extends RMModItem
{
	
	public ItemRune()
	{
		super("rune", true, EnumRuneType.class);
	}
	
	public enum EnumRuneType implements IMetaEnum
	{
		AIR,
		ASTRAL,
		BLOOD,
		BODY,
		CHAOS,
		COSMIC,
		DEATH,
		EARTH,
		FIRE,
		LAW,
		MIND,
		NATURE,
		SOUL,
		WATER,
		ESSENCE;
		
		private final String name;
		
		EnumRuneType()
		{
			this.name = super.toString().toLowerCase();
		}
		
		@Override
		public String getName()
		{
			return name;
		}
		
		@Override
		public String getID()
		{
			return "rune_type";
		}
		
		@Override
		public String toString()
		{
			return name;
		}
	}
}
