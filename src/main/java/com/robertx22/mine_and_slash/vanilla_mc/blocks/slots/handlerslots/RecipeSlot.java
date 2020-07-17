package com.robertx22.mine_and_slash.vanilla_mc.blocks.slots.handlerslots;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class RecipeSlot extends SlotItemHandler {

    public RecipeSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean canTakeStack(PlayerEntity playerIn) {
        return false;
    }

    @Override
    public boolean isItemValid(@Nonnull ItemStack stack) {
        return false;
    }
}
