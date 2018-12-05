package com.robertx22.database.stat_mods.multi.elemental.damage;

import com.robertx22.database.stat_types.elementals.spell_damage.SpellNatureDamage;
import com.robertx22.stats.Stat;

public class SpellNatureDamageMulti extends BaseEleMulti {

    @Override
    public Stat GetBaseStat() {
	return new SpellNatureDamage();

    }

    @Override
    public String GUID() {
	return "SpellNatureDamageMulti";
    }

}
