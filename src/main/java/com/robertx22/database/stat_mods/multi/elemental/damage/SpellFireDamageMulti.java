package com.robertx22.database.stat_mods.multi.elemental.damage;

import com.robertx22.database.stat_types.elementals.spell_damage.SpellFireDamage;
import com.robertx22.stats.Stat;

public class SpellFireDamageMulti extends BaseEleMulti {

    @Override
    public Stat GetBaseStat() {
	return new SpellFireDamage();

    }

    @Override
    public String GUID() {
	return "SpellFireDamageMulti";
    }

}
