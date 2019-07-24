package com.robertx22.mine_and_slash.uncommon.item_filters;

import com.robertx22.mine_and_slash.uncommon.item_filters.bases.ItemFilter;
import com.robertx22.mine_and_slash.uncommon.datasaving.Rune;
import net.minecraft.item.ItemStack;

public class RuneItemFilter extends ItemFilter {

    @Override
    public boolean IsValidItem(ItemStack stack) {
        return Rune.has(stack);
    }
}
