package com.robertx22.mine_and_slash.gui.trader;

import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.blueprints.GearBlueprint;
import com.robertx22.mine_and_slash.loot.blueprints.RunedGearBlueprint;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ICommonDataItem;
import net.minecraft.item.ItemStack;

public enum TraderOffers implements IWeighted {

    HIGH_RARITY {
        @Override
        public ItemStack generateStackInternal(LootInfo info) {
            GearBlueprint gearB = new GearBlueprint(info);
            gearB.rarity.minRarity = 3;
            return gearB.createStack();

        }
    },
    HIGH_RARITY_RUNED {
        @Override
        public ItemStack generateStackInternal(LootInfo info) {
            RunedGearBlueprint gearB = new RunedGearBlueprint(info.level, info.tier);
            gearB.rarity.minRarity = 3;
            return gearB.createStack();

        }
    };

    @Override
    public int Weight() {
        return 1000;
    }

    public abstract ItemStack generateStackInternal(LootInfo info);

    public ItemStack generateStack(LootInfo info) {

        ItemStack stack = generateStackInternal(info);

        int price = Integer.MAX_VALUE;

        ICommonDataItem data = ICommonDataItem.load(stack);

        if (data instanceof ISellPrice) {
            ISellPrice sell = (ISellPrice) data;
            price = sell.getPriceInCommonOres();
        } else {
            if (stack.getItem() instanceof ISellPrice) {
                ISellPrice sell = (ISellPrice) stack.getItem();
                price = sell.getPriceInCommonOres();
            }
        }

        ISellPrice.savePriceInCommonOres(stack, price);

        return stack;

    }

}