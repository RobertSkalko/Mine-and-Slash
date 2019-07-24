package com.robertx22.mine_and_slash.blocks.slots;

import com.robertx22.mine_and_slash.blocks.item_modify_station.TileGearModify;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class ModifySlot extends Slot {
    public ModifySlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return TileGearModify.isItemValidForInputSlot(stack);
    }
}