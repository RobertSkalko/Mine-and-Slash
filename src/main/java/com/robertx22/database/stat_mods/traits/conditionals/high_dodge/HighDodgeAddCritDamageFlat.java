package com.robertx22.database.stat_mods.traits.conditionals.high_dodge;

import com.robertx22.database.stat_mods.BaseTraitMod;
import com.robertx22.database.stat_types.traits.high_dodge.HighDodgeAddCritDamage;
import com.robertx22.stats.Stat;

public class HighDodgeAddCritDamageFlat extends BaseTraitMod {

    @Override
    public String GUID() {
	return "HighDodgeAddCritDamage";
    }

    @Override
    public Stat GetBaseStat() {
	return new HighDodgeAddCritDamage();

    }

}
