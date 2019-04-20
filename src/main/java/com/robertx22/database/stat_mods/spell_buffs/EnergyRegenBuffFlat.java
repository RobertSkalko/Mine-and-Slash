package com.robertx22.database.stat_mods.spell_buffs;

import com.robertx22.database.stat_mods.BaseTraitMod;
import com.robertx22.database.stat_types.spell_buff_traits.BuffEnergyRegenTrait;
import com.robertx22.stats.Stat;

public class EnergyRegenBuffFlat extends BaseTraitMod {

    public EnergyRegenBuffFlat() {
    }

    @Override
    public String GUID() {
	return "EnergyRegenBuffFlat";
    }

    @Override
    public Stat GetBaseStat() {
	return new BuffEnergyRegenTrait();
    }

}
