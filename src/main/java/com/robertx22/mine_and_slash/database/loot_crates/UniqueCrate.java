package com.robertx22.mine_and_slash.database.loot_crates;

import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.gens.UniqueGearLootGen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class UniqueCrate extends LootCrate {

    @Override
    public Item lootCrateItem() {
        return null;
    }

    @Override
    public ItemStack generateStack(LootInfo info) {
        return new UniqueGearLootGen(info).generateOne();
    }

    @Override
    public int averageItemCount() {
        return 3;
    }

    @Override
    public String GUID() {
        return "unique_crate";
    }

}
