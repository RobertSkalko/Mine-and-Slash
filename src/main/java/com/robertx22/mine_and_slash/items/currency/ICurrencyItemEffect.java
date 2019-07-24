package com.robertx22.mine_and_slash.items.currency;

import net.minecraft.item.ItemStack;

public interface ICurrencyItemEffect {

    public abstract ItemStack ModifyItem(ItemStack stack, ItemStack currency);

    boolean canItemBeModifiedPROTECTED(ItemStack item, ItemStack currency);

    default boolean canItemBeModified(ItemStack item, ItemStack currency) {
        return this.canItemBeModifiedPROTECTED(item, currency);
    }

}
