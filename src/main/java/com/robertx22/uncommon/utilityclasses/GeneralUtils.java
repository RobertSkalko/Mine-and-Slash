package com.robertx22.uncommon.utilityclasses;

public class GeneralUtils {

	public static String removeFormatting(String s) {
		return s.replaceAll("\u00a7.", "");
	}

	public static float getHpMultiplier(float vanillaHP, int actualHP) {
		return vanillaHP / (float) (actualHP);
	}

}
