package com.robertx22.database.stat_mods.traits.conditionals.high_dodge;

import com.robertx22.database.stat_mods.BaseTraitMod;
import com.robertx22.database.stat_types.traits.high_dodge.HighDodgeAddPhysDamage;
import com.robertx22.stats.Stat;

public class HighDodgeAddPhysDamageFlat extends BaseTraitMod {

    @Override
    public String GUID() {
	return "HighDodgeAddPhysDamageFlat";
    }

    @Override
    public Stat GetBaseStat() {
	return new HighDodgeAddPhysDamage();

    }

}
