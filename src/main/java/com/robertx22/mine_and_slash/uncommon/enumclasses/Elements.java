package com.robertx22.mine_and_slash.uncommon.enumclasses;

import com.robertx22.mine_and_slash.uncommon.interfaces.IColor;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Elements implements IColor {
    Physical(0, new RGB(0, 0, 0), false, "Physical", TextFormatting.GRAY, Items.COAL, "Annihilation"),
    Fire(1, new RGB(255, 0, 0), true, "Flame", TextFormatting.RED, Items.MAGMA_CREAM, "Firestorms"),
    Water(2, new RGB(0, 128, 255), true, "Frost", TextFormatting.BLUE, Items.SNOWBALL, "Blizzards"),
    Thunder(3, new RGB(255, 255, 0), true, "Lightning", TextFormatting.YELLOW, Items.GLOWSTONE_DUST, "Thunderstorms"),
    Nature(4, new RGB(0, 204, 0), true, "Venom", TextFormatting.GREEN, Items.SLIME_BALL, "Earthquakes"),
    Elemental(5, new RGB(0, 0, 0), false, "Elemental", TextFormatting.GOLD, Items.EMERALD, "Element Eruption");

    public boolean isSingleElement = true;
    public Item projectileItem;
    private RGB color;

    Elements(int i, RGB color, boolean isSingleElement, String dmgname,
             TextFormatting format, Item item, String disasterName) {
        this.disasterName = disasterName;
        this.i = i;
        this.color = color;
        this.isSingleElement = isSingleElement;
        this.dmgName = dmgname;
        this.format = format;
        this.projectileItem = item;

    }

    public String disasterName;
    public String dmgName;
    public int i = 0;

    public TextFormatting format;

    public static List<Elements> getAllSingleElements() {

        return Arrays.stream(Elements.values())
                .filter(x -> x.isSingleElement)
                .collect(Collectors.toList());

    }

    public static List<Elements> getAllExceptNone() {

        return Arrays.stream(Elements.values())
                .filter(x -> x != Elements.Elemental)
                .collect(Collectors.toList());

    }

    @Override
    public RGB getRGBColor() {
        return color;
    }

    public static class RGB {
        public RGB(int R, int G, int B) {
            this.R = R;
            this.G = G;
            this.B = B;
        }

        private int R;
        private int G;
        private int B;

        public float getR() {
            return R / 255.0F; // you need to divide by 255 for the color to work as intended..
        }

        public float getG() {
            return G / 255.0F;
        }

        public float getB() {
            return B / 255.0F;
        }

        public int getIntR() {
            return R;
        }

        public int getIntG() {
            return G;
        }

        public int getIntB() {
            return B;
        }
    }

}
