package com.laton95.runemysteries.util;

import java.util.Random;

public class MathsHelper {
	
	public static int randomInRange(Random rand, int min, int max) {
		return rand.nextInt(max - min + 1) + min;
	}
	
	public static int randomSign(Random rand) {
		return rand.nextBoolean() ? 1 : -1;
	}
	
	public static int randomInRangeRandomSign(Random rand, int min, int max) {
		return randomInRange(rand, min, max) * randomSign(rand);
	}
}
