package com.robertx22.database.stat_types.elementals.spell_damage;

import com.robertx22.stats.Stat;

public abstract class BaseSpellDamage extends Stat {

    public BaseSpellDamage() {
    }

    @Override
    public boolean ScalesToLevel() {
	return true;
    }

    @Override
    public boolean IsPercent() {
	return false;
    }

}
