package com.robertx22.database.stat_mods.percent.much_less;

import com.robertx22.database.stat_types.defense.Dodge;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class CrippleDodgePercent extends StatMod {

    public CrippleDodgePercent() {
    }

    @Override
    public String GUID() {
	return "CrippleDodgePercent";

    }

    @Override
    public float Min() {
	return -25;
    }

    @Override
    public float Max() {
	return -50;
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