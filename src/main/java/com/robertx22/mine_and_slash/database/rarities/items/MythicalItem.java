package com.robertx22.mine_and_slash.database.rarities.items;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.ItemRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseMythical;

public class MythicalItem extends BaseMythical implements ItemRarity {

    @Override
    public int AffixChance() {
        return 100;
    }

    @Override
    public MinMax StatPercents() {
        return new MinMax(35, 100);
    }

    @Override
    public int SetChance() {
        return 80;
    }

    @Override
    public float specialItemChance() {
        return 15.5F;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.ITEMS.MYTHICAL_WEIGHT.get();
    }

    @Override
    public int runeSlots() {
        return 5;
    }

    @Override
    public MinMax SpawnDurabilityHit() {
        return new MinMax(80, 90);
    }

    @Override
    public float itemTierPower() {
        return 3;
    }

    @Override
    public float powerMultiplier() {
        return 7F;
    }
}
