package com.robertx22.database.stat_mods.spell_buffs;

import com.robertx22.database.stat_mods.BaseTraitMod;
import com.robertx22.database.stat_types.spell_buff_traits.LightTrait;
import com.robertx22.stats.Stat;

public class LightFlat extends BaseTraitMod {

    public LightFlat() {
    }

    @Override
    public String GUID() {
	return "LightFlat";
    }

    @Override
    public Stat GetBaseStat() {
	return new LightTrait();
    }

}
