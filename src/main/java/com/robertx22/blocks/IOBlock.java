package com.robertx22.blocks;

import net.minecraft.item.ItemStack;

public interface IOBlock {

    boolean isItemValidInput(ItemStack stack);

    boolean isItemValidOutput(ItemStack stack);

    boolean isAutomatable();

    int[] inputSlots();

}
