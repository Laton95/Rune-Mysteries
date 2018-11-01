package com.laton95.runemysteries.enums;

import com.laton95.runemysteries.config.ModConfig;
import com.laton95.runemysteries.init.ModItems;
import com.laton95.runemysteries.item.ItemRune;
import com.laton95.runemysteries.item.ItemTalisman;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;

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
	
	public DimensionType gerRuinDimType()
	{
		switch(this)
		{
			case CHAOS:
				return DimensionType.NETHER;
			case COSMIC:
				return DimensionType.THE_END;
			default:
				return DimensionType.OVERWORLD;
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
	
	public BlockPos getTempleEntrancePoint()
	{
		switch(this)
		{
			case AIR:
				return new BlockPos(2, 87, 2);
			case BLOOD:
				return new BlockPos(4, 66, 6);
			case BODY:
				return new BlockPos(2, 87, 2);
			case CHAOS:
				return new BlockPos(2, 87, 2);
			case COSMIC:
				return new BlockPos(10, 64, 10);
			case DEATH:
				return new BlockPos(2, 87, 2);
			case EARTH:
				return new BlockPos(2, 87, 2);
			case FIRE:
				return new BlockPos(10, 107, 10);
			case LAW:
				return new BlockPos(2, 87, 2);
			case MIND:
				return new BlockPos(2, 87, 2);
			case NATURE:
				return new BlockPos(2, 87, 2);
			case SOUL:
				return new BlockPos(2, 87, 2);
			case WATER:
				return new BlockPos(9, 65, 11);
			default:
				return new BlockPos(2, 87, 2);
		}
	}
}
