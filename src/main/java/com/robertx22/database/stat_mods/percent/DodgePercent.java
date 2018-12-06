package com.robertx22.database.stat_mods.percent;

import com.robertx22.database.stat_types.defense.Dodge;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class DodgePercent extends StatMod {

    public DodgePercent() {
    }

    @Override
    public String GUID() {
	return "DodgePercent";

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
	return StatTypes.Percent;
    }

    @Override
    public Stat GetBaseStat() {
	return new Dodge();
    }

}
