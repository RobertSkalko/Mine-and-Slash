package com.robertx22.database.stat_mods.multi.elemental.damage;

import com.robertx22.database.stat_types.elementals.spell_damage.SpellThunderDamage;
import com.robertx22.stats.Stat;

public class SpellThunderDamageMulti extends BaseEleMulti {

    @Override
    public Stat GetBaseStat() {
	return new SpellThunderDamage();

    }

    @Override
    public String GUID() {

	return "SpellThunderDamageMulti";
    }

}
