package com.robertx22.mine_and_slash.new_content_test.professions.recipe;

import net.minecraft.item.ItemStack;

public abstract class BaseMaterial extends BasePreviewItem {

    ItemStack stack;

    public BaseMaterial(ItemStack stack) {
        this.stack = stack;
    }

    public BaseMaterial amount(int amount) {
        this.stack.setCount(amount);
        return this;
    }

    public void consume(ItemStack stack) {

        stack.shrink(this.stack.getCount());

    }

    public abstract boolean isStackValidMaterial(ItemStack stack);

    @Override
    public ItemStack getPreview() {
        return stack;
    }

}
