package com.robertx22.mine_and_slash.database.rarities.serialization;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.GearRarity;

public class SerializedGearRarity extends SerializedBaseRarity implements GearRarity {

    public int affixChance;
    public int setChance;
    public int runeSlots;
    public MinMax secondaryStatsAmount;
    public MinMax statPercents;
    public float itemTierPower;
    public float requirementMulti;
    public float salvageLotteryChance;
    public int unidentifiedChance;

    public SerializedGearRarity(SerializedBaseRarity baseRarity) {
        super(baseRarity);
    }

    @Override
    public int AffixChance() {
        return affixChance;
    }

    @Override
    public int SetChance() {
        return setChance;
    }

    @Override
    public int runeSlots() {
        return runeSlots;
    }

    @Override
    public MinMax secondaryStatAmount() {
        return secondaryStatsAmount;
    }

    @Override
    public float itemTierPower() {
        return itemTierPower;
    }

    @Override
    public float requirementMulti() {
        return requirementMulti;
    }

    @Override
    public float unidentifiedChance() {
        return unidentifiedChance;
    }

    @Override
    public MinMax StatPercents() {
        return statPercents;
    }

    @Override
    public float salvageLotteryWinChance() {
        return salvageLotteryChance;
    }

}
