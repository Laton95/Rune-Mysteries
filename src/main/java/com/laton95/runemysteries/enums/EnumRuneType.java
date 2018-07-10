package com.laton95.runemysteries.enums;

import com.laton95.runemysteries.config.ModConfig;
import com.laton95.runemysteries.init.ModItems;
import com.laton95.runemysteries.item.ItemRune;
import com.laton95.runemysteries.item.ItemTalisman;
import net.minecraft.item.Item;

import java.util.LinkedList;
import java.util.List;

public enum EnumRuneType
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
	WATER;
	
	public static EnumRuneType getTypeFromRune(ItemRune rune)
	{
		for(EnumRuneType type : values())
		{
			if(rune == type.getRuneOfType())
			{
				return type;
			}
		}
		
		return AIR;
	}
	
	public ItemRune getRuneOfType()
	{
		switch(this)
		{
			case AIR:
				return ModItems.AIR_RUNE;
			case ASTRAL:
				return ModItems.ASTRAL_RUNE;
			case BLOOD:
				return ModItems.BLOOD_RUNE;
			case BODY:
				return ModItems.BODY_RUNE;
			case CHAOS:
				return ModItems.CHAOS_RUNE;
			case COSMIC:
				return ModItems.COSMIC_RUNE;
			case DEATH:
				return ModItems.DEATH_RUNE;
			case EARTH:
				return ModItems.EARTH_RUNE;
			case FIRE:
				return ModItems.FIRE_RUNE;
			case LAW:
				return ModItems.LAW_RUNE;
			case MIND:
				return ModItems.MIND_RUNE;
			case NATURE:
				return ModItems.NATURE_RUNE;
			case SOUL:
				return ModItems.SOUL_RUNE;
			case WATER:
				return ModItems.WATER_RUNE;
			default:
				return ModItems.AIR_RUNE;
		}
	}
	
	public static List<Item> getRunes()
	{
		List<Item> runes = new LinkedList<>();
		for(EnumRuneType rune : EnumRuneType.values())
		{
			runes.add(rune.getRuneOfType());
		}
		return runes;
	}
	
	public static List<Item> getTalismans()
	{
		List<Item> talismans = new LinkedList<>();
		for(EnumRuneType rune : EnumRuneType.values())
		{
			talismans.add(rune.getTalismanOfType());
		}
		return talismans;
	}
	
	public ItemTalisman getTalismanOfType()
	{
		switch(this)
		{
			case AIR:
				return ModItems.AIR_TALISMAN;
			case ASTRAL:
				return ModItems.ASTRAL_TALISMAN;
			case BLOOD:
				return ModItems.BLOOD_TALISMAN;
			case BODY:
				return ModItems.BODY_TALISMAN;
			case CHAOS:
				return ModItems.CHAOS_TALISMAN;
			case COSMIC:
				return ModItems.COSMIC_TALISMAN;
			case DEATH:
				return ModItems.DEATH_TALISMAN;
			case EARTH:
				return ModItems.EARTH_TALISMAN;
			case FIRE:
				return ModItems.FIRE_TALISMAN;
			case LAW:
				return ModItems.LAW_TALISMAN;
			case MIND:
				return ModItems.MIND_TALISMAN;
			case NATURE:
				return ModItems.NATURE_TALISMAN;
			case SOUL:
				return ModItems.SOUL_TALISMAN;
			case WATER:
				return ModItems.WATER_TALISMAN;
			default:
				return ModItems.AIR_TALISMAN;
		}
	}
	
	public int getRuinDimId()
	{
		switch(this)
		{
			case CHAOS:
				return -1;
			case COSMIC:
				return 1;
			default:
				return 0;
		}
	}
	
	public int getTempleDimId()
	{
		switch(this)
		{
			case AIR:
				return ModConfig.DIMENSIONS.airTempleDimID;
			case BLOOD:
				return ModConfig.DIMENSIONS.bloodTempleDimID;
			case BODY:
				return ModConfig.DIMENSIONS.bodyTempleDimID;
			case CHAOS:
				return ModConfig.DIMENSIONS.chaosTempleDimID;
			case COSMIC:
				return ModConfig.DIMENSIONS.cosmicTempleDimID;
			case DEATH:
				return ModConfig.DIMENSIONS.deathTempleDimID;
			case EARTH:
				return ModConfig.DIMENSIONS.earthTempleDimID;
			case FIRE:
				return ModConfig.DIMENSIONS.fireTempleDimID;
			case LAW:
				return ModConfig.DIMENSIONS.lawTempleDimID;
			case MIND:
				return ModConfig.DIMENSIONS.mindTempleDimID;
			case NATURE:
				return ModConfig.DIMENSIONS.natureTempleDimID;
			case SOUL:
				return ModConfig.DIMENSIONS.soulTempleDimID;
			case WATER:
				return ModConfig.DIMENSIONS.waterTempleDimID;
			default:
				return 0;
		}
	}
}
