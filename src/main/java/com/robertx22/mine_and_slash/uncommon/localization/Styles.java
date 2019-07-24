package com.robertx22.mine_and_slash.uncommon.localization;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class Styles {

    public static TextFormatting BLUE = TextFormatting.BLUE;
    public static TextFormatting GOLD = TextFormatting.GOLD;
    public static TextFormatting GREEN = TextFormatting.GREEN;
    public static TextFormatting YELLOW = TextFormatting.YELLOW;
    public static TextFormatting RED = TextFormatting.RED;
    public static TextFormatting DARK_GREEN = TextFormatting.DARK_GREEN;
    public static TextFormatting GRAY = TextFormatting.GRAY;
    public static TextFormatting LIGHT_PURPLE = TextFormatting.LIGHT_PURPLE;
    public static TextFormatting AQUA = TextFormatting.AQUA;

    public static ITextComponent BLUECOMP() {
        return new StringTextComponent(BLUE + "");
    }

    public static ITextComponent GOLDCOMP() {
        return new StringTextComponent(GOLD + "");
    }

    public static ITextComponent GREENCOMP() {
        return new StringTextComponent(GREEN + "");
    }

    public static ITextComponent YELLOWCOMP() {
        return new StringTextComponent(YELLOW + "");
    }

    public static ITextComponent REDCOMP() {
        return new StringTextComponent(RED + "");
    }

    public static ITextComponent DARK_GREENCOMP() {
        return new StringTextComponent(DARK_GREEN + "");
    }

    public static ITextComponent GRAYCOMP() {
        return new StringTextComponent(GRAY + "");
    }

    public static ITextComponent LIGHT_PURPLECOMP() {
        return new StringTextComponent(LIGHT_PURPLE + "");
    }

    public static ITextComponent AQUACOMP() {
        return new StringTextComponent(AQUA + "");
    }

}


