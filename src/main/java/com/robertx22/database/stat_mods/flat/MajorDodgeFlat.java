package com.robertx22.database.stat_mods.flat;

import com.robertx22.database.stat_types.defense.Dodge;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class MajorDodgeFlat extends StatMod {

    public MajorDodgeFlat() {
    }

    @Override
    public String GUID() {
	return "MajorDodgeFlat";
    }

    @Override
    public float Min() {
	return 3;
    }

    @Override
    public float Max() {
	return 15;
    }

    @Override
    public StatTypes Type() {
	return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
	return new Dodge();
    }

}
