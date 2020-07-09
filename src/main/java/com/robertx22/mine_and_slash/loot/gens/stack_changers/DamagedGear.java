package com.robertx22.mine_and_slash.loot.gens.stack_changers;

import com.robertx22.mine_and_slash.loot.LootUtils;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ICommonDataItem;
import net.minecraft.item.ItemStack;

public class DamagedGear implements IStackAction {

    private DamagedGear() {

    }

    public static DamagedGear INSTANCE = new DamagedGear();

    @Override
    public void changeStack(ItemStack stack) {

        ICommonDataItem data = ICommonDataItem.load(stack);

        if (data != null) {
            LootUtils.RandomDamagedGear(stack, data.getRarity());
        }

    }
}
