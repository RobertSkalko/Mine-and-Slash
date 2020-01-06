package com.robertx22.mine_and_slash.database.loot_crates;

import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.gens.CurrencyLootGen;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.item.ItemStack;

public class CurrencyCrate extends LootCrate {

    @Override
    public Words name() {
        return Words.CurrencyCrate;
    }

    @Override
    public ItemStack generateStack(LootInfo info) {
        return new CurrencyLootGen(info).generateOne();
    }

    @Override
    public int getRarityRank() {
        return IRarity.Rare;
    }

    @Override
    public int averageItemCount() {
        return 15;
    }

    @Override
    public String GUID() {
        return "currency_crate";
    }

}