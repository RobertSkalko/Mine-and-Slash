package com.robertx22.utilityclasses;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import java.util.Arrays;
import java.util.Objects;
import static com.robertx22.mmorpg.Ref.MODID;
import static java.lang.String.format;

public final class Utils {

	public static String setName(String name) {
		return format("%s.%s", MODID, name);
	}

	public static NonNullList<ItemStack> getItemStacks(ItemStack... items) {
		NonNullList<ItemStack> list = NonNullList.create();
		list.addAll(Arrays.asList(items));
		return list;
	}

	public static ResourceLocation setRL(String path) {
		return new ResourceLocation(MODID, path);
	}

	public static String setLocation(String path) {
		return format("%s:%s", MODID, path);
	}

}