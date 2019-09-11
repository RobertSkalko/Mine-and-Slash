package com.robertx22.mine_and_slash.new_content_test.professions.recipe;

import com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases.ProfessionTile;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SimpleOutputItem extends BaseOutputItem {

    ItemStack output;

    public SimpleOutputItem(Item item) {
        output = new ItemStack(item);
    }

    @Override
    public ItemStack getOutput(ProfessionTile tile) {
        return output;
    }

    @Override
    public ItemStack getPreview() {
        return output.copy();
    }

    @Override
    public ItemStack getItemStack() {
        return output.copy();
    }
}
