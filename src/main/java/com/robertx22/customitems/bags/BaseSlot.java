package com.robertx22.customitems.bags;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public abstract class BaseSlot extends SlotItemHandler {

    public BaseSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
	super(itemHandler, index, xPosition, yPosition);

    }

    public abstract BaseSlot newObject(IItemHandler inv, int index, int x, int y);

    public abstract boolean isItemValid(ItemStack stack);

}
