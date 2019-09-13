package com.robertx22.mine_and_slash.new_content_test.professions.recipe;

import com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases.ProfessionTile;
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

    public BaseMaterial amount(float amount) {
        this.stack.setCount((int) amount);
        return this;
    }

    public void consume(ItemStack stack, ProfessionTile tile) {
        stack.shrink(tile.profession.tryReduceMaterialRequirement(tile, this, tile.playerProfLevel));
    }

    public abstract boolean isStackValidMaterial(ItemStack stack);

    @Override
    public ItemStack getPreview() {

        ItemStack copy = stack.copy();

        return copy;
    }

}
