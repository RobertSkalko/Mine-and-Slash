package com.robertx22.mine_and_slash.new_content.trader;

import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.new_content.trader.offers.TraderOffer;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

@Storable
public class TraderData {

    @Store
    public List<ItemStack> stacks = new ArrayList<>();

    @Store
    public boolean generated = false;

    public void generateMerchandise(LootInfo info) {

        TraderOffer type = RandomUtils.weightedRandom(TraderOffers.ALL);

        stacks.clear();

        stacks = type.generateStacks(info);

    }

}
