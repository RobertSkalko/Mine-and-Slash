package com.robertx22.mine_and_slash.database.loot_crates;

import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class LootCrate implements ISlashRegistryEntry {

    public abstract Item lootCrateItem();

    public abstract Words name();

    public abstract ItemStack generateStack(LootInfo info);

    public float lotteryWinChance() {
        return 1;
    }

    public boolean wonLottery() {
        return RandomUtils.roll(lotteryWinChance());
    }

    public abstract int averageItemCount();

    public List<ItemStack> generateItems(LootInfo info, int scoreMulti,
                                         boolean wonLottery) {

        List<ItemStack> items = new ArrayList<>();

        float amount = (this.averageItemCount() * scoreMulti);
        if (wonLottery) {
            amount *= 3;
        }

        int amountInt = (int) amount;

        while (items.size() < amountInt) {
            ItemStack stack = generateStack(info);
            if (stack != null && !stack.isEmpty()) {
                items.add(stack);
            }
        }

        return items;

    }

    @Override
    public int Weight() {
        return getRarity().Weight();
    }

    @Override
    public int getRarityRank() {
        return IRarity.Uncommon;
    }

    @Override
    public Rarity getRarity() {
        return Rarities.Items.get(getRarityRank());
    }

    @Override
    public int Tier() {
        return 0;
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.LOOT_CRATE;
    }

}
