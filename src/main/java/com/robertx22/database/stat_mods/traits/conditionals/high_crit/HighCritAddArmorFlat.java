package com.robertx22.database.stat_mods.traits.conditionals.high_crit;

import com.robertx22.database.stat_mods.BaseTraitMod;
import com.robertx22.database.stat_types.traits.high_crit.HighCritAddArmor;
import com.robertx22.stats.Stat;

public class HighCritAddArmorFlat extends BaseTraitMod {

    @Override
    public String GUID() {
	return "HighCritAddArmorFlat";
    }

    @Override
    public Stat GetBaseStat() {
	return new HighCritAddArmor();

    }

}
