package com.robertx22.database.stat_mods.multi.elemental.all_damage;

import com.robertx22.database.stat_mods.multi.elemental.damage.BaseEleMulti;
import com.robertx22.database.stat_types.elementals.all_damage.AllThunderDamage;
import com.robertx22.stats.Stat;

public class AllThunderDamageMulti extends BaseEleMulti {

    @Override
    public Stat GetBaseStat() {
	return new AllThunderDamage();

    }

    @Override
    public String GUID() {
	return "AllThunderDamageMulti";
    }

}
