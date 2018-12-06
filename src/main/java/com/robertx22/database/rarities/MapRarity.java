package com.robertx22.database.rarities;

import com.robertx22.database.MinMax;
import com.robertx22.saveclasses.gearitem.gear_bases.Rarity;

public interface MapRarity extends Rarity, SalvagableItem {

    MinMax AffixAmount();

    MinMax StatPercents();

}
