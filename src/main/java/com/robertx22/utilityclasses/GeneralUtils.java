package com.robertx22.utilityclasses;

import com.robertx22.constants.Tags;

import net.minecraft.nbt.NBTTagCompound;

public class GeneralUtils {

	public static String removeFormatting(String s) {
		return s.replaceAll("\u00a7.", "");
	}

	public static float getHpMultiplier(float vanillaHP, int actualHP) {
		return vanillaHP / (float) (actualHP);
	}

	public static void log(Object s) {
		System.out.println((s));
	}
}
