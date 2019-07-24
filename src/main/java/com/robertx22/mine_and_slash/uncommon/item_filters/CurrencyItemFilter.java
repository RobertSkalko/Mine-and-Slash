package com.robertx22.mine_and_slash.uncommon.item_filters;

import com.robertx22.mine_and_slash.items.currency.CurrencyItem;
import com.robertx22.mine_and_slash.uncommon.item_filters.bases.ItemFilter;
import net.minecraft.item.ItemStack;

public class CurrencyItemFilter extends ItemFilter {

    @Override
    public boolean IsValidItem(ItemStack stack) {

        return stack.getItem() instanceof CurrencyItem;
    }
}

