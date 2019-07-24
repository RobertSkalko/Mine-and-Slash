package com.robertx22.mine_and_slash.database.rarities.items;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.ItemRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseLegendary;

public class LegendaryItem extends BaseLegendary implements ItemRarity {

    @Override
    public int AffixChance() {
        return 80;
    }

    @Override
    public MinMax StatPercents() {
        return new MinMax(25, 90);
    }

    @Override
    public int SetChance() {
        return 60;
    }

    @Override
    public float specialItemChance() {
        return 6.5F;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.ITEMS.LEGENDARY_WEIGHT.get();
    }

    @Override
    public int runeSlots() {
        return 4;
    }

    @Override
    public MinMax SpawnDurabilityHit() {
        return new MinMax(75, 85);
    }

    @Override
    public float itemTierPower() {
        return 2.5F;
    }

    @Override
    public float powerMultiplier() {
        return 2.5F;
    }
}
