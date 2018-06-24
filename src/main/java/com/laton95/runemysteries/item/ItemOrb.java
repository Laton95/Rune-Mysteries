package com.laton95.runemysteries.item;

public class ItemOrb extends RMModItem
{
	public ItemOrb()
	{
		super("glass_orb", true, EnumOrbType.class);
		setMaxStackSize(1);
	}
	
	public enum EnumOrbType implements IMetaEnum
	{
		UNCHARGED,
		AIR,
		EARTH,
		FIRE,
		WATER;
		
		private final String name;
		
		EnumOrbType()
		{
			name = super.toString().toLowerCase();
		}
		
		@Override
		public String getID()
		{
			return "orb_type";
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
