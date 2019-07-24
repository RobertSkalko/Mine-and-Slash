package com.robertx22.mine_and_slash.uncommon.item_filters;

import com.robertx22.mine_and_slash.items.misc.ItemAwakenRuneWord;
import com.robertx22.mine_and_slash.uncommon.item_filters.bases.ItemFilter;
import net.minecraft.item.ItemStack;

public class AwakenWordFilter extends ItemFilter {

    @Override
    public boolean IsValidItem(ItemStack stack) {
        return stack.getItem() instanceof ItemAwakenRuneWord;
    }
}


