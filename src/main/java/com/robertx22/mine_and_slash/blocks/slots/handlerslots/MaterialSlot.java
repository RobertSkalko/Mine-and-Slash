package com.robertx22.mine_and_slash.blocks.slots.handlerslots;

import com.robertx22.mine_and_slash.new_content_test.professions.recipe.BaseMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class MaterialSlot extends SlotItemHandler {

    public BaseMaterial material;

    public MaterialSlot(BaseMaterial mat, IItemHandler itemHandler, int index,
                        int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);

        this.material = mat;
    }

    @Override
    public boolean isItemValid(@Nonnull ItemStack stack) {
        return material.isStackValidMaterial(stack);
    }

}
