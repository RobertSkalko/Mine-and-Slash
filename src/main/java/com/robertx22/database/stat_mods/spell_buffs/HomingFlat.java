package com.robertx22.database.stat_mods.spell_buffs;

import com.robertx22.database.stat_mods.BaseTraitMod;
import com.robertx22.database.stat_types.spell_buff_traits.HomingSpell;
import com.robertx22.stats.Stat;

public class HomingFlat extends BaseTraitMod {

    public HomingFlat() {
    }

    @Override
    public String GUID() {
	return "HomingFlat";
    }

    @Override
    public Stat GetBaseStat() {
	return new HomingSpell();
    }

}
