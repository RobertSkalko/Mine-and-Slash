package com.robertx22.mine_and_slash.database.rarities;

import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;

public interface GearRarity extends Rarity, SalvagableItem, IStatPercents {
    int AffixChance();

    int SetChance();

    int runeSlots();

    float itemTierPower();

    float powerMultiplier();

    float requirementMulti();
}
