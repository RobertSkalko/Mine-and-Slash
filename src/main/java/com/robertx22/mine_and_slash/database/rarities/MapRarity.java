package com.robertx22.mine_and_slash.database.rarities;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;

public interface MapRarity extends Rarity, SalvagableItem {

    MinMax AffixAmount();

    MinMax StatPercents();

    float groupPlayChance();

}
