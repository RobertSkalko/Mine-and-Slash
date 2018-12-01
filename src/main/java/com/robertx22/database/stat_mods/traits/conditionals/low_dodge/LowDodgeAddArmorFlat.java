package com.robertx22.database.stat_mods.traits.conditionals.low_dodge;

import com.robertx22.database.stat_mods.BaseTraitMod;
import com.robertx22.database.stat_types.traits.low_dodge.LowDodgeAddArmor;
import com.robertx22.stats.Stat;

public class LowDodgeAddArmorFlat extends BaseTraitMod {

    @Override
    public String GUID() {
	return "LowDodgeAddArmorFlat";
    }

    @Override
    public Stat GetBaseStat() {
	return new LowDodgeAddArmor();
    }

}
