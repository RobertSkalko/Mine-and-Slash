package com.robertx22.mine_and_slash.items.bags;

import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public abstract class BaseSlot extends Slot {

    public BaseSlot(Inventory inv, int index, int xPosition, int yPosition) {
        super(inv, index, xPosition, yPosition);

    }

    public abstract boolean isItemValid(ItemStack stack);

}
