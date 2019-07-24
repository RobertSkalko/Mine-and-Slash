package com.robertx22.mine_and_slash.blocks.bases;

import net.minecraft.item.ItemStack;

public interface IOBlock {

    boolean isItemValidInput(ItemStack stack);

    boolean isItemValidOutput(ItemStack stack);

    boolean isAutomatable();

    int[] inputSlots();

}
