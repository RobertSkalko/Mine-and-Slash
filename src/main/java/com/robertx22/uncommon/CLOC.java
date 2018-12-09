package com.robertx22.uncommon;

import com.robertx22.mmorpg.Ref;

import net.minecraft.client.resources.I18n;

public class CLOC {

    public static String tooltip(String str) {

	return I18n.format(Ref.MODID + ".tooltip." + str);

    }
}
