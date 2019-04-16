package com.robertx22.database.stat_mods.spell_buffs;

import com.robertx22.database.stat_mods.BaseTraitMod;
import com.robertx22.database.stat_types.spell_buff_traits.ZephyrTrait;
import com.robertx22.stats.Stat;

public class ZephyrFlat extends BaseTraitMod {

    public ZephyrFlat() {
    }

    @Override
    public String GUID() {
	return "ZephyrFlat";
    }

    @Override
    public Stat GetBaseStat() {
	return new ZephyrTrait();
    }

}
