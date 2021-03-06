package com.laton95.runemysteries.enums;

import com.laton95.runemysteries.block.ParticleLightBlock;
import com.laton95.runemysteries.init.ModBlocks;
import net.minecraft.item.DyeColor;

public enum EnumColour {
	WHITE(228, 228, 228, DyeColor.WHITE),
	ORANGE(234, 126, 53, DyeColor.ORANGE),
	MAGENTA(190, 73, 201, DyeColor.MAGENTA),
	LIGHT_BLUE(99, 135, 210, DyeColor.LIGHT_BLUE),
	YELLOW(194, 181, 28, DyeColor.YELLOW),
	LIME(57, 186, 46, DyeColor.LIME),
	PINK(217, 129, 153, DyeColor.PINK),
	GRAY(65, 65, 65, DyeColor.GRAY),
	LIGHT_GRAY(160, 167, 167, DyeColor.LIGHT_GRAY),
	CYAN(38, 113, 145, DyeColor.CYAN),
	PURPLE(126, 52, 191, DyeColor.PURPLE),
	BLUE(37, 49, 147, DyeColor.BLUE),
	BROWN(86, 51, 28, DyeColor.BROWN),
	GREEN(54, 75, 24, DyeColor.GREEN),
	RED(158, 43, 39, DyeColor.RED),
	BLACK(24, 20, 20, DyeColor.BLACK);
	
	private final Colour colour;
	
	private final DyeColor dye;
	
	EnumColour(float red, float green, float blue, DyeColor dye) {
		this.colour = new Colour(red, green, blue);
		this.dye = dye;
	}
	
	public static EnumColour getColourFromDye(DyeColor dye) {
		switch(dye) {
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
			case LIGHT_GRAY:
				return LIGHT_GRAY;
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
	
	public Colour getRGB() {
		return colour;
	}
	
	public DyeColor getDyeColour() {
		return dye;
	}
	
	public ParticleLightBlock getLight() {
		switch(this) {
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
			case LIGHT_GRAY:
				return ModBlocks.LIGHT_GRAY_LIGHT;
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
	
	public class Colour {
		
		private final float red;
		
		private final float green;
		
		private final float blue;
		
		Colour(float red, float green, float blue) {
			this.red = red;
			this.green = green;
			this.blue = blue;
		}
		
		public float getRed() {
			return red;
		}
		
		public float getGreen() {
			return green;
		}
		
		public float getBlue() {
			return blue;
		}
	}
}
