package com.robertx22.mine_and_slash.gui.trader;

import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Storable
public class TraderData {

    @Store
    List<ItemStack> stacks = new ArrayList<>();

    @Store
    public boolean generated = false;

    public void generateMerchandise(LootInfo info) {

        TraderOffers type = RandomUtils.weightedRandom(Arrays.asList(TraderOffers.values()));

        int amount = RandomUtils.RandomRange(5, 15);

        stacks.clear();

        for (int i = 0; i < amount; i++) {
            ItemStack stack = type.generateStack(info);
            stacks.add(stack);
        }

    }

}
