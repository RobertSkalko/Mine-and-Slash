package com.robertx22.mine_and_slash.new_content_test.professions.recipe;

import com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases.ProfessionTile;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SameStackAsPreview extends BaseOutputItem {

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
    public ItemStack generateStack(ProfessionTile tile) {
        return stack.copy();
    }
}
