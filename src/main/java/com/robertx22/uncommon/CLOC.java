package com.robertx22.uncommon;

import com.robertx22.mmorpg.Main;
import com.robertx22.mmorpg.Ref;

import net.minecraft.util.text.TextFormatting;

public class CLOC {

    private static String base(String s) {
	return Main.proxy.translate(s);
    }

    public static String tooltip(String str) {

	return base(Ref.MODID + ".tooltip." + str);

    }

    public static String geartype(String str) {

	return base(Ref.MODID + ".gear_type." + str);
    }

    public static String stat(String str) {

	return base(Ref.MODID + ".stat." + str);

    }

    public static String word(String str) {

	return base(Ref.MODID + ".word." + str);

    }

    public static String uniqueName(String str) {

	return base("item." + Ref.MODID + ".unique." + str + ".name");

    }

    public static String uniqueDesc(String str) {

	return base(Ref.MODID + ".unique.tooltip." + str);

    }

    public static String suffix(String str) {

	return base(Ref.MODID + ".suffix." + str);

    }

    public static String prefix(String str) {

	return base(Ref.MODID + ".prefix." + str);

    }

    public static String rarity(String str) {

	return base(Ref.MODID + ".rarity." + str);

    }

    public static String lore(String str) {

	return TextFormatting.GREEN + "'" + base(Ref.MODID + ".lore." + str) + "'";

    }

    public static String blank(String string) {

	return base(string);
    }

}
