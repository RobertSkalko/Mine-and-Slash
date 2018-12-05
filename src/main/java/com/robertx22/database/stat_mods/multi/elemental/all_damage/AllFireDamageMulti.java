package com.robertx22.database.stat_mods.multi.elemental.all_damage;

import com.robertx22.database.stat_mods.multi.elemental.damage.BaseEleMulti;
import com.robertx22.database.stat_types.elementals.all_damage.AllFireDamage;
import com.robertx22.stats.Stat;

public class AllFireDamageMulti extends BaseEleMulti {

    @Override
    public Stat GetBaseStat() {
	return new AllFireDamage();

    }

    @Override
    public String GUID() {
	return "AllFireDamageMulti";
    }

}
