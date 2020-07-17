package com.robertx22.mine_and_slash.vanilla_mc.blocks.slots.handlerslots;

import com.robertx22.mine_and_slash.uncommon.item_filters.bases.ItemFilterGroup;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class SlotHandler extends Slot {

    ItemFilterGroup filter;

    public SlotHandler(Inventory itemHandler, int index, int xPosition, int yPosition,
                       ItemFilterGroup filter) {
        super(itemHandler, index, xPosition, yPosition);
        this.filter = filter;

    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return filter.anyMatchesFilter(stack);
    }

}
