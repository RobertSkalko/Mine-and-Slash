package com.robertx22.mine_and_slash.blocks.slots.handlerslots;

import com.robertx22.mine_and_slash.items.bags.BaseSlot;
import com.robertx22.mine_and_slash.uncommon.item_filters.bases.ItemFilterGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class CurrencySlotHandler extends BaseSlot {

    public CurrencySlotHandler(IItemHandler itemHandler, int index, int xPosition,
                               int yPosition) {
        super(itemHandler, index, xPosition, yPosition);

    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return ItemFilterGroup.ANY_CURRENCY.anyMatchesFilter(stack);
    }

}