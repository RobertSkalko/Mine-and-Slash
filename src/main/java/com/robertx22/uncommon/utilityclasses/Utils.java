package com.robertx22.uncommon.utilityclasses;

import static com.robertx22.mmorpg.Ref.MODID;
import static java.lang.String.format;

import net.minecraft.util.ResourceLocation;

public final class Utils {

	public static ResourceLocation setRL(String path) {
		return new ResourceLocation(MODID, path);
	}

	public static String setLocation(String path) {
		return format("%s:%s", MODID, path);
	}

}