package com.robertx22.mine_and_slash.database.loot_crates;

import com.robertx22.mine_and_slash.database.loot_crates.bases.LootCrate;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.gens.MapLootGen;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

public class CartographerCrate extends LootCrate {

    private CartographerCrate() {
    }

    public static CartographerCrate getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public ITextComponent name() {
        return Words.CartographerCrate.locName();
    }

    @Override
    public ItemStack generateStack(LootInfo info) {
        return new MapLootGen(info).generateOne();
    }

    @Override
    public int getRarityRank() {
        return IRarity.Mythic;
    }

    @Override
    public int maxItems() {
        return 2;
    }

    @Override
    public String GUID() {
        return "map_crate";
    }

    private static class SingletonHolder {
        private static final CartographerCrate INSTANCE = new CartographerCrate();
    }
}
