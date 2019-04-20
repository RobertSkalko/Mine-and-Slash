package com.robertx22.database.stat_mods.spell_buffs;

import com.robertx22.database.stat_mods.BaseTraitMod;
import com.robertx22.database.stat_types.spell_buff_traits.BuffManaRegenTrait;
import com.robertx22.stats.Stat;

public class ManaRegenBuffFlat extends BaseTraitMod {

    public ManaRegenBuffFlat() {
    }

    @Override
    public String GUID() {
	return "ManaRegenBuffFlat";
    }

    @Override
    public Stat GetBaseStat() {
	return new BuffManaRegenTrait();
    }

}
