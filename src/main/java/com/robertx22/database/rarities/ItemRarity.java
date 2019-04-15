package com.robertx22.database.rarities;

import com.robertx22.database.MinMax;
import com.robertx22.saveclasses.gearitem.gear_bases.Rarity;

public interface ItemRarity extends Rarity, SalvagableItem {
    int AffixChance();

    MinMax SecondaryStatsAmount();

    MinMax StatPercents();

    int SetChance();

    int runeSlots();

    MinMax SpawnDurabilityHit();

}
