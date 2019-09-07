package com.robertx22.mine_and_slash.new_content_test.professions.recipe;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SameStackAsPreview extends BasePreviewItem {

    ItemStack stack;

    public SameStackAsPreview(ItemStack stack) {
        this.stack = stack;
    }

    public SameStackAsPreview(Item item) {
        this.stack = new ItemStack(item);

    }

    @Override
    public ItemStack getPreview() {
        return stack;
    }

    @Override
    public ItemStack getItemStack() {
        return stack;
    }
}
