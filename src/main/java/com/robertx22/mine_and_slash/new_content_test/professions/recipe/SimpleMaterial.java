package com.robertx22.mine_and_slash.new_content_test.professions.recipe;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SimpleMaterial extends BaseMaterial {

    public SimpleMaterial(ItemStack stack) {
        super(stack);
    }

    public SimpleMaterial(Item item) {
        super(new ItemStack(item));
    }

    @Override
    public boolean isStackValidMaterial(ItemStack stack) {
        return stack.getItem()
                .equals(this.stack.getItem()) && stack.getCount() >= this.stack.getCount();
    }

    @Override
    public ItemStack getItemStack() {
        return stack;
    }
}
