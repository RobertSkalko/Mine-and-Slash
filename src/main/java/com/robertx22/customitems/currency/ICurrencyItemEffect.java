package com.robertx22.customitems.currency;

import net.minecraft.item.ItemStack;

public interface ICurrencyItemEffect {

	public abstract void ModifyItem(ItemStack stack);

	public abstract boolean CanItemBeModified(ItemStack stack);

}
