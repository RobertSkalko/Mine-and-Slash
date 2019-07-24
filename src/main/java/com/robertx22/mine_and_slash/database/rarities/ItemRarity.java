package com.robertx22.mine_and_slash.database.rarities;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;

public interface ItemRarity extends Rarity, SalvagableItem {
    int AffixChance();

    MinMax StatPercents();

    int SetChance();

    int runeSlots();

    MinMax SpawnDurabilityHit();

    float itemTierPower();

    float powerMultiplier();

}
