package com.robertx22.mine_and_slash.database.rarities.items;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.ItemRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseUncommon;

public class UncommonItem extends BaseUncommon implements ItemRarity {

    @Override
    public int AffixChance() {
        return 15;
    }

    @Override
    public MinMax StatPercents() {
        return new MinMax(5, 65);
    }

    @Override
    public int SetChance() {
        return 25;
    }

    @Override
    public float specialItemChance() {
        return 1.5F;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.ITEMS.UNCOMMON_WEIGHT.get();
    }

    @Override
    public int runeSlots() {
        return 2;
    }

    @Override
    public MinMax SpawnDurabilityHit() {
        return new MinMax(55, 65);
    }

    @Override
    public float itemTierPower() {
        return 1.2F;
    }

    @Override
    public float powerMultiplier() {
        return 1.2F;
    }
}
