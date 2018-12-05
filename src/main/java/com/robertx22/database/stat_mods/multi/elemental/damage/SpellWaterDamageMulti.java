package com.robertx22.database.stat_mods.multi.elemental.damage;

import com.robertx22.database.stat_types.elementals.spell_damage.SpellWaterDamage;
import com.robertx22.stats.Stat;

public class SpellWaterDamageMulti extends BaseEleMulti {

    @Override
    public Stat GetBaseStat() {
	return new SpellWaterDamage();

    }

    @Override
    public String GUID() {
	return "SpellWaterDamageMulti";
    }

}
