package com.robertx22.mine_and_slash.gui.screens.bestiary;

import net.minecraft.item.ItemStack;

public abstract class BestiaryEntry {

    public String splitReason;

    public ItemStack stack;

    public boolean isSplitter() {
        return splitReason != null && !splitReason.isEmpty();
    }

    public boolean isItem() {
        return stack != null && !stack.isEmpty();
    }

    public static class Splitter extends BestiaryEntry {

        public Splitter(String split) {
            this.splitReason = split;
        }

    }

    public static class Item extends BestiaryEntry {
        public Item(ItemStack stack) {
            this.stack = stack;
        }
    }

}
