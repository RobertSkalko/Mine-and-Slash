package com.robertx22.mine_and_slash.gui.trader.offers;

import com.robertx22.mine_and_slash.gui.trader.ISellPrice;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ICommonDataItem;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class TraderOffer implements IWeighted {

    public int amount = 15;

    @Override
    public int Weight() {
        return 1000;
    }

    public abstract ItemStack generateStackInternal(LootInfo info);

    public List<ItemStack> generateStacks(LootInfo info) {
        List<ItemStack> list = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            list.add(generateStack(info));
        }

        return list;
    }

    private ItemStack generateStack(LootInfo info) {

        ItemStack stack = generateStackInternal(info);

        int price = Integer.MAX_VALUE;

        ICommonDataItem data = ICommonDataItem.load(stack);

        if (data instanceof ISellPrice) {
            ISellPrice sell = (ISellPrice) data;
            price = sell.getSavedPriceInCommonOres();
        } else {
            if (stack.getItem() instanceof ISellPrice) {
                ISellPrice sell = (ISellPrice) stack.getItem();
                price = sell.getSavedPriceInCommonOres();
            }
        }

        ISellPrice.savePriceInCommonOres(stack, price);

        return stack;

    }

}
