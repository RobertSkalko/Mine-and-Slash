package com.robertx22.uncommon;

import com.robertx22.mmorpg.Ref;

import net.minecraft.client.resources.I18n;

public class CLOC {

    public static String tooltip(String str) {

	return I18n.format(Ref.MODID + ".tooltip." + str);

    }

    public static String stat(String str) {

	return I18n.format(Ref.MODID + ".stat." + str);

    }

    public static String word(String str) {

	return I18n.format(Ref.MODID + ".word." + str);

    }

    public static String uniqueName(String str) {

	return I18n.format(Ref.MODID + ".unique.name." + str);

    }

    public static String uniqueDesc(String str) {

	return I18n.format(Ref.MODID + ".unique.tooltip." + str);

    }

}
