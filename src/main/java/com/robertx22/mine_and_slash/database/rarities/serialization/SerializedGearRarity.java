package com.robertx22.mine_and_slash.database.rarities.serialization;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.GearRarity;

public class SerializedGearRarity extends SerializedBaseRarity implements GearRarity {

    public int affixChance;

    public MinMax statPercents;
    public MinMax primaryStatPercents;
    public MinMax secondaryStatPercents;
    public MinMax affixStatPercents;
    public MinMax uniqueStatPercents;
    public float itemTierPower;
    public float requirementMulti;
    public float salvageLotteryChance;
    public int unidentifiedChance;
    public int maxAffixes;

    public SerializedGearRarity(SerializedBaseRarity baseRarity) {
        super(baseRarity);
    }

    @Override
    public MinMax affixStatPercents() {
        return affixStatPercents;
    }

    @Override
    public MinMax uniqueStatPercents() {
        return uniqueStatPercents;
    }

    @Override
    public int AffixChance() {
        return affixChance;
    }

    @Override
    public int maxAffixes() {
        return maxAffixes;
    }

    @Override
    public float itemTierPower() {
        return itemTierPower;
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
