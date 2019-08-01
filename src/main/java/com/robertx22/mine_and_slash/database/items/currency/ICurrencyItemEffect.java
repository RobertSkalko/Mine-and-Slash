package com.robertx22.mine_and_slash.database.items.currency;

import com.robertx22.mine_and_slash.database.items.currency.loc_reqs.BaseLocRequirement;
import net.minecraft.item.ItemStack;

import java.util.List;

public interface ICurrencyItemEffect {

    public abstract ItemStack ModifyItem(ItemStack stack, ItemStack currency);

    public abstract List<BaseLocRequirement> requirements();

    boolean canItemBeModified(ItemStack item, ItemStack currency);

}
