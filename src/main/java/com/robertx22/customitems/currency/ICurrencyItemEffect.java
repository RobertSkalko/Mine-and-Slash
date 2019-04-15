package com.robertx22.customitems.currency;

import net.minecraft.item.ItemStack;

public interface ICurrencyItemEffect {

    public abstract ItemStack ModifyItem(ItemStack stack, ItemStack currency);

    boolean canItemBeModified(ItemStack item, ItemStack currency);

}
