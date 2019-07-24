package com.robertx22.mine_and_slash.blocks.slots;

import com.robertx22.mine_and_slash.blocks.salvage_station.TileGearSalvage;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class SalvageSlot extends Slot {
    public SalvageSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return TileGearSalvage.isItemValidForInputSlot(stack);
    }
}