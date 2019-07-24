package com.robertx22.mine_and_slash.blocks.slots.handlerslots;

import com.robertx22.mine_and_slash.items.bags.BaseSlot;
import com.robertx22.mine_and_slash.uncommon.item_filters.bases.ItemFilterGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class SlotHandler extends BaseSlot {

    ItemFilterGroup filter;

    public SlotHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition,
                       ItemFilterGroup filter) {
        super(itemHandler, index, xPosition, yPosition);
        this.filter = filter;

    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return filter.anyMatchesFilter(stack);
    }

}
