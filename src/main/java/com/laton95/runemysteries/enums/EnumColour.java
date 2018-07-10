package com.laton95.runemysteries.enums;

import com.laton95.runemysteries.block.BlockParticleLight;
import com.laton95.runemysteries.init.ModBlocks;
import net.minecraft.item.EnumDyeColor;

public enum EnumColour
{
	WHITE(228, 228, 228),
	ORANGE(234, 126, 53),
	MAGENTA(190, 73, 201),
	LIGHT_BLUE(99, 135, 210),
	YELLOW(194, 181, 28),
	LIME(57, 186, 46),
	PINK(217, 129, 153),
	GRAY(65, 65, 65),
	SILVER(160, 167, 167),
	CYAN(38, 113, 145),
	PURPLE(126, 52, 191),
	BLUE(37, 49, 147),
	BROWN(86, 51, 28),
	GREEN(54, 75, 24),
	RED(158, 43, 39),
	BLACK(24, 20, 20);
	
	private final Colour colour;
	
	EnumColour(float red, float green, float blue)
	{
		this.colour = new Colour(red, green, blue);
	}
	
	public Colour getRGB()
	{
		return colour;
	}
	
	public int getDyeMeta()
	{
		return getDyeColour().getDyeDamage();
	}
	
	public EnumDyeColor getDyeColour()
	{
		switch(this)
		{
			case WHITE:
				return EnumDyeColor.WHITE;
			case ORANGE:
				return EnumDyeColor.ORANGE;
			case MAGENTA:
				return EnumDyeColor.MAGENTA;
			case LIGHT_BLUE:
				return EnumDyeColor.LIGHT_BLUE;
			case YELLOW:
				return EnumDyeColor.YELLOW;
			case LIME:
				return EnumDyeColor.LIME;
			case PINK:
				return EnumDyeColor.PINK;
			case GRAY:
				return EnumDyeColor.GRAY;
			case SILVER:
				return EnumDyeColor.SILVER;
			case CYAN:
				return EnumDyeColor.CYAN;
			case PURPLE:
				return EnumDyeColor.PURPLE;
			case BLUE:
				return EnumDyeColor.BLUE;
			case BROWN:
				return EnumDyeColor.BROWN;
			case GREEN:
				return EnumDyeColor.GREEN;
			case RED:
				return EnumDyeColor.RED;
			case BLACK:
				return EnumDyeColor.BLACK;
			default:
				return EnumDyeColor.WHITE;
		}
	}
	
	public EnumColour getColourFromDye(EnumDyeColor dye)
	{
		switch(dye)
		{
			case WHITE:
				return WHITE;
			case ORANGE:
				return ORANGE;
			case MAGENTA:
				return MAGENTA;
			case LIGHT_BLUE:
				return LIGHT_BLUE;
			case YELLOW:
				return YELLOW;
			case LIME:
				return LIME;
			case PINK:
				return PINK;
			case GRAY:
				return GRAY;
			case SILVER:
				return SILVER;
			case CYAN:
				return CYAN;
			case PURPLE:
				return PURPLE;
			case BLUE:
				return BLUE;
			case BROWN:
				return BROWN;
			case GREEN:
				return GREEN;
			case RED:
				return RED;
			case BLACK:
				return BLACK;
			default:
				return WHITE;
		}
	}
	
	public BlockParticleLight getLight()
	{
		switch(this)
		{
			case WHITE:
				return ModBlocks.WHITE_LIGHT;
			case ORANGE:
				return ModBlocks.ORANGE_LIGHT;
			case MAGENTA:
				return ModBlocks.MAGENTA_LIGHT;
			case LIGHT_BLUE:
				return ModBlocks.LIGHT_BLUE_LIGHT;
			case YELLOW:
				return ModBlocks.YELLOW_LIGHT;
			case LIME:
				return ModBlocks.LIME_LIGHT;
			case PINK:
				return ModBlocks.PINK_LIGHT;
			case GRAY:
				return ModBlocks.GRAY_LIGHT;
			case SILVER:
				return ModBlocks.SILVER_LIGHT;
			case CYAN:
				return ModBlocks.CYAN_LIGHT;
			case PURPLE:
				return ModBlocks.PURPLE_LIGHT;
			case BLUE:
				return ModBlocks.BLUE_LIGHT;
			case BROWN:
				return ModBlocks.BROWN_LIGHT;
			case GREEN:
				return ModBlocks.GREEN_LIGHT;
			case RED:
				return ModBlocks.RED_LIGHT;
			case BLACK:
				return ModBlocks.BLACK_LIGHT;
			default:
				return ModBlocks.WHITE_LIGHT;
		}
	}
	
	public class Colour
	{
		private float red;
		
		private float green;
		
		private float blue;
		
		Colour(float red, float green, float blue)
		{
			this.red = red;
			this.green = green;
			this.blue = blue;
		}
		
		public void set(float red, float green, float blue)
		{
			this.red = red;
			this.green = green;
			this.blue = blue;
		}
		
		public float getRed()
		{
			return red;
		}
		
		public float getGreen()
		{
			return green;
		}
		
		public float getBlue()
		{
			return blue;
		}
	}
}
