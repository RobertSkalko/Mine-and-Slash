package com.robertx22.uncommon;

import com.robertx22.mmorpg.Ref;

import net.minecraft.util.text.TextComponentTranslation;

public class SLOC {

    public static TextComponentTranslation chat(String str) {

	return new TextComponentTranslation(Ref.MODID + ".chat." + str);

    }

}
