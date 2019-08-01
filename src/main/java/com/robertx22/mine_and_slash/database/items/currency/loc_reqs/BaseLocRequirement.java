package com.robertx22.mine_and_slash.database.items.currency.loc_reqs;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

public abstract class BaseLocRequirement {

    public abstract ITextComponent getText();

    public abstract boolean isAllowed(Object object, ItemStack currency);

    public boolean isNotAllowed(Object object, ItemStack currency) {
        return this.isAllowed(object, currency) == false;
    }

}
