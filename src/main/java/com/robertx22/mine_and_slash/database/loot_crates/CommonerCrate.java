package com.robertx22.mine_and_slash.database.loot_crates;

import com.robertx22.mine_and_slash.database.loot_crates.bases.LootCrate;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.gens.gears.GearLootGen;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.item.ItemStack;

public class CommonerCrate extends LootCrate {

    public static CommonerCrate INSTANCE = new CommonerCrate();

    private CommonerCrate() {

    }

    @Override
    public Words name() {
        return Words.CommonerCrate;
    }

    @Override
    public ItemStack generateStack(LootInfo info) {
        return new GearLootGen(info).generateOne();
    }

    @Override
    public int getRarityRank() {
        return IRarity.Rare;
    }

    @Override
    public int maxItems() {
        return 6;
    }

    @Override
    public String GUID() {
        return "commoner_crate";
    }

}
