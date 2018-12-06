package com.robertx22.database.stat_mods.traits.conditionals.high_crit;

import com.robertx22.database.stat_mods.BaseTraitMod;
import com.robertx22.database.stat_types.traits.high_crit.HighCritAddLifesteal;
import com.robertx22.stats.Stat;

public class HighCritAddLifestealFlat extends BaseTraitMod {

    @Override
    public String GUID() {
	return "HighCritAddLifestealFlat";
    }

    @Override
    public Stat GetBaseStat() {
	return new HighCritAddLifesteal();

    }

}
