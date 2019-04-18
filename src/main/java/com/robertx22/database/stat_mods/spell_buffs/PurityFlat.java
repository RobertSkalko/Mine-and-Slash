package com.robertx22.database.stat_mods.spell_buffs;

import com.robertx22.database.stat_mods.BaseTraitMod;
import com.robertx22.database.stat_types.spell_buff_traits.PurityTrait;
import com.robertx22.stats.Stat;

public class PurityFlat extends BaseTraitMod {

    public PurityFlat() {
    }

    @Override
    public String GUID() {
	return "PurityFlat";
    }

    @Override
    public Stat GetBaseStat() {
	return new PurityTrait();
    }

}
