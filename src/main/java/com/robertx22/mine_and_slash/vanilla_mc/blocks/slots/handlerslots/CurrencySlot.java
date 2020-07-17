package com.robertx22.mine_and_slash.vanilla_mc.blocks.slots.handlerslots;

import com.robertx22.mine_and_slash.vanilla_mc.items.bags.BaseSlot;
import com.robertx22.mine_and_slash.uncommon.item_filters.bases.ItemFilterGroup;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

public class CurrencySlot extends BaseSlot {

    public CurrencySlot(Inventory itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);

    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return ItemFilterGroup.CURRENCY_BAG.anyMatchesFilter(stack);
    }

}