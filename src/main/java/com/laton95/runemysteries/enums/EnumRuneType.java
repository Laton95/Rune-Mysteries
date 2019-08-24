package com.laton95.runemysteries.enums;

import com.laton95.runemysteries.init.ModDimensions;
import com.laton95.runemysteries.init.ModItems;
import com.laton95.runemysteries.item.RuneItem;
import com.laton95.runemysteries.item.TalismanItem;
import com.laton95.runemysteries.util.ModLog;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

public enum EnumRuneType {
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
	OURANIA;
	
	public RuneItem getRune() {
		switch(this) {
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
	
	public TalismanItem getTalisman() {
		switch(this) {
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
	
	public static EnumRuneType getRuneType(RuneItem rune) {
		return rune.getRuneType();
	}
	
	public static EnumRuneType getRuneType(TalismanItem talisman) {
		return talisman.getRuneType();
	}
	
	public static EnumRuneType getRandomType(Random random) {
		return EnumRuneType.values()[random.nextInt(EnumRuneType.values().length)];
	}
	
	public boolean isOurania() {
		return this == OURANIA;
	}
	
	public DimensionType getRuinDimension() {
		switch(this) {
			case CHAOS:
				return DimensionType.THE_NETHER;
			case COSMIC:
				return DimensionType.THE_END;
			default:
				return DimensionType.OVERWORLD;
		}
	}
	
	public RedstoneParticleData getRuneColor() {
		float size = 0.5f;
		switch(this) {
			case AIR:
				return new RedstoneParticleData(valueOf(255), valueOf(255), valueOf(255), size);
			case ASTRAL:
				return new RedstoneParticleData(valueOf(193), valueOf(174), valueOf(193), size);
			case BLOOD:
				return new RedstoneParticleData(valueOf(193), valueOf(11), valueOf(11), size);
			case BODY:
				return new RedstoneParticleData(valueOf(25), valueOf(47), valueOf(212), size);
			case CHAOS:
				return new RedstoneParticleData(valueOf(248), valueOf(89), valueOf(3), size);
			case COSMIC:
				return new RedstoneParticleData(valueOf(155), valueOf(246), valueOf(0), size);
			case DEATH:
				return new RedstoneParticleData(valueOf(255), valueOf(255), valueOf(255), size);
			case EARTH:
				return new RedstoneParticleData(valueOf(108), valueOf(38), valueOf(0), size);
			case FIRE:
				return new RedstoneParticleData(valueOf(248), valueOf(89), valueOf(3), size);
			case LAW:
				return new RedstoneParticleData(valueOf(25), valueOf(37), valueOf(212), size);
			case MIND:
				return new RedstoneParticleData(valueOf(248), valueOf(89), valueOf(3), size);
			case NATURE:
				return new RedstoneParticleData(valueOf(4), valueOf(111), valueOf(27), size);
			case SOUL:
				return new RedstoneParticleData(valueOf(169), valueOf(184), valueOf(193), size);
			case WATER:
				return new RedstoneParticleData(valueOf(25), valueOf(47), valueOf(212), size);
			default:
				return new RedstoneParticleData(valueOf(0), valueOf(0), valueOf(0), size);
		}
	}
	
	public boolean hasObelisk() {
		return this == AIR || this == EARTH || this == FIRE || this == WATER;
	}
	
	private static float valueOf(int colorValue) {
		return (float) colorValue / 255;
	}
	
	public DimensionType getTempleDimension() {
		switch(this) {
			case AIR:
				return DimensionType.byName(ModDimensions.AIR_TEMPLE.getRegistryName());
			case BLOOD:
				return DimensionType.byName(ModDimensions.BLOOD_TEMPLE.getRegistryName());
			case BODY:
				return DimensionType.byName(ModDimensions.BODY_TEMPLE.getRegistryName());
			case CHAOS:
				return DimensionType.byName(ModDimensions.CHAOS_TEMPLE.getRegistryName());
			case COSMIC:
				return DimensionType.byName(ModDimensions.COSMIC_TEMPLE.getRegistryName());
			case DEATH:
				return DimensionType.byName(ModDimensions.DEATH_TEMPLE.getRegistryName());
			case EARTH:
				return DimensionType.byName(ModDimensions.EARTH_TEMPLE.getRegistryName());
			case FIRE:
				return DimensionType.byName(ModDimensions.FIRE_TEMPLE.getRegistryName());
			case LAW:
				return DimensionType.byName(ModDimensions.LAW_TEMPLE.getRegistryName());
			case MIND:
				return DimensionType.byName(ModDimensions.MIND_TEMPLE.getRegistryName());
			case NATURE:
				return DimensionType.byName(ModDimensions.NATURE_TEMPLE.getRegistryName());
			case SOUL:
				return DimensionType.byName(ModDimensions.SOUL_TEMPLE.getRegistryName());
			case WATER:
				return DimensionType.byName(ModDimensions.WATER_TEMPLE.getRegistryName());
			default:
				return null;
		}
	}
	
	public BlockPos getTempleArrivalPoint() {
		return new BlockPos(0, 100, 0);
	}
}
