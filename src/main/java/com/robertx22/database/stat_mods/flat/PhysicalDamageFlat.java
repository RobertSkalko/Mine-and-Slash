package com.robertx22.database.stat_mods.flat;

import com.robertx22.database.stat_types.offense.PhysicalDamage;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.utilityclasses.IWeighted;

public class PhysicalDamageFlat extends StatMod {

    public PhysicalDamageFlat() {
    }

    @Override
    public String GUID() {
	return "DamageFlat";
    }

    @Override
    public float Min() {
	return 4;

    }

    @Override
    public float Max() {
	return 12;
    }

    @Override
    public StatTypes Type() {
	return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
	return new PhysicalDamage();
    }

    @Override
    public int Weight() {
	return IWeighted.UncommonWeight;
    }

}
