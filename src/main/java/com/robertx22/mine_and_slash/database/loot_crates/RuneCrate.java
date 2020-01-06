package com.robertx22.mine_and_slash.database.loot_crates;

import com.robertx22.mine_and_slash.database.loot_crates.bases.LootCrate;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.gens.RuneLootGen;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.item.ItemStack;

public class RuneCrate extends LootCrate {

    @Override
    public Words name() {
        return Words.RuneCrate;
    }

    @Override
    public ItemStack generateStack(LootInfo info) {
        return new RuneLootGen(info).generateOne();
    }

    @Override
    public int getRarityRank() {
        return IRarity.Rare;
    }

    @Override
    public int maxItems() {
        return 7;
    }

    @Override
    public String GUID() {
        return "rune_crate";
    }

}