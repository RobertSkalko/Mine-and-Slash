package com.robertx22.utilityclasses;

import com.robertx22.constants.Stats;
import com.robertx22.constants.Tag;
import net.minecraft.nbt.NBTTagCompound;

public class GeneralUtils {

    public static String removeFormatting(String s) {
        return s.replaceAll("\u00a7.", "");
    }

    public static float getHpMultiplier(float vanillaHP, int actualHP) {
        return vanillaHP / (float) (actualHP);
    }

    public static NBTTagCompound getdefaultEntityNBT() {

        NBTTagCompound nbt = new NBTTagCompound();

        nbt.setInteger(Tag.LEVEL, 1);
        nbt.setInteger(Tag.CURRENT_XP, 0);
        nbt.setInteger(Tag.MAX_XP, 25);
        nbt.setInteger(Tag.RARITY_NUMBER, 0);
        nbt.setString(Tag.RARITY, "");
        nbt.setInteger(Stats.HEALTH.name, 20);
        nbt.setInteger(Tag.DAMAGE, 1);
        nbt.setInteger(Tag.CURRENT_MANA, 0);

        return nbt;

    }

    public static void log(Object s) {
        System.out.println((s));
    }
}
