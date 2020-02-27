package com.robertx22.mine_and_slash.database.loot_crates;

import com.robertx22.mine_and_slash.database.loot_crates.bases.LootCrate;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.MasterLootGen;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public class NormalCrate extends LootCrate {

    private NormalCrate() {
    }

    public static NormalCrate getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public ITextComponent name() {
        return Words.CommonerCrate.locName();
    }

    @Override
    public ItemStack generateStack(LootInfo info) {

        try {
            List<ItemStack> items = MasterLootGen.generateLoot(info);
            info.minItems = 1;
            ItemStack stack = RandomUtils.randomFromList(items);

            return stack;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ItemStack.EMPTY;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Common;
    }

    @Override
    public int maxItems() {
        return 4;
    }

    @Override
    public String GUID() {
        return "normal_crate";
    }

    private static class SingletonHolder {
        private static final NormalCrate INSTANCE = new NormalCrate();
    }
}
