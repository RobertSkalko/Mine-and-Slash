package com.robertx22.database.stat_mods.percent;

import com.robertx22.database.stat_types.offense.PhysicalDamage;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class PhysicalDamagePercent extends StatMod {

    public PhysicalDamagePercent() {
    }

    @Override
    public String GUID() {
	return "DamagePercent";
    }

    @Override
    public float Min() {
	return 3;
    }

    @Override
    public float Max() {
	return 8;
    }

    @Override
    public StatTypes Type() {
	return StatTypes.Percent;
    }

    @Override
    public Stat GetBaseStat() {
	return new PhysicalDamage();
    }

}
