package com.robertx22.mine_and_slash.uncommon.enumclasses;

import com.robertx22.mine_and_slash.uncommon.interfaces.IColor;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Elements implements IColor {
    Physical(0, new RGB(240, 157, 55), false, "Physical", TextFormatting.GOLD, Items.COAL, "Annihilation", "physical",
             "\u2726"
    ),
    Fire(1, new RGB(255, 0, 0), true, "Flame", TextFormatting.RED, Items.FIRE_CHARGE, "Firestorms", "fire", "\u2600"),
    Water(2, new RGB(0, 128, 255), true, "Frost", TextFormatting.BLUE, Items.SNOWBALL, "Blizzards", "water", "\u2749"),
    Thunder(3, new RGB(204, 0, 255), true, "Lightning", TextFormatting.YELLOW, Items.GLOWSTONE_DUST, "Thunderstorms",
            "thunder", "\u272A"
    ),
    Nature(4, new RGB(0, 204, 0), true, "Venom", TextFormatting.DARK_GREEN, Items.SLIME_BALL, "Earthquakes", "nature",
           "\u273F"
    ),
    Elemental(5, new RGB(0, 0, 0), false, "Elemental", TextFormatting.GOLD, Items.EMERALD, "getElement Eruption",
              "elemental", "\u269C"
    );

    public boolean isSingleElement = true;
    public Item projectileItem;
    private RGB color;

    Elements(int i, RGB color, boolean isSingleElement, String dmgname, TextFormatting format, Item item,
             String disasterName, String guidname, String icon) {
        this.disasterName = disasterName;
        this.i = i;
        this.color = color;
        this.isSingleElement = isSingleElement;
        this.dmgName = dmgname;
        this.format = format;
        this.projectileItem = item;
        this.guidName = guidname;
        this.icon = icon;
    }

    public String disasterName;
    public String dmgName;
    public String guidName;
    public int i = 0;
    public String icon;

    public TextFormatting format;

    public static List<Elements> getAllSingleElements() {

        return Arrays.stream(Elements.values()).filter(x -> x.isSingleElement).collect(Collectors.toList());

    }

    private static List<Elements> allExceptNone = null;
    private static List<Elements> allSingles = null;

    public static List<Elements> getAllExceptNone() {
        return Arrays.stream(Elements.values()).filter(x -> x != Elements.Elemental).collect(Collectors.toList());

    }

    @Override
    public RGB getRGBColor() {
        return color;
    }

}
