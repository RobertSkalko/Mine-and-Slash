package com.robertx22.database.stat_mods.flat.resources;

import com.robertx22.database.stat_types.resources.Energy;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class EnergyFlat extends StatMod {

    public EnergyFlat() {
    }

    @Override
    public String GUID() {
	return "EnergyFlat";
    }

    @Override
    public float Min() {
	return 10;
    }

    @Override
    public float Max() {
	return 25;
    }

    @Override
    public StatTypes Type() {
	return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
	return new Energy();
    }

}
