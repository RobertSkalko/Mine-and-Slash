package com.robertx22.mine_and_slash.uncommon.item_filters;

import com.robertx22.mine_and_slash.uncommon.item_filters.bases.ItemFilter;
import com.robertx22.mine_and_slash.uncommon.datasaving.Map;
import net.minecraft.item.ItemStack;

public class MapItemFilter extends ItemFilter {

    @Override
    public boolean IsValidItem(ItemStack stack) {
        return Map.has(stack);
    }
}
