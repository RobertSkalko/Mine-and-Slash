package com.robertx22.mine_and_slash.uncommon.item_filters;

import com.robertx22.mine_and_slash.uncommon.item_filters.bases.ItemFilter;
import com.robertx22.mine_and_slash.uncommon.datasaving.Spell;
import net.minecraft.item.ItemStack;

public class SpellItemFilter extends ItemFilter {

    @Override
    public boolean IsValidItem(ItemStack stack) {
        return Spell.has(stack);
    }
}
