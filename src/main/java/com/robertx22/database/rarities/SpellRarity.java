package com.robertx22.database.rarities;

import com.robertx22.database.MinMax;
import com.robertx22.saveclasses.gearitem.gear_bases.Rarity;

public interface SpellRarity extends Rarity, SalvagableItem {

    MinMax SpellBasePercents();

    MinMax SpellScalingPercents();

}
