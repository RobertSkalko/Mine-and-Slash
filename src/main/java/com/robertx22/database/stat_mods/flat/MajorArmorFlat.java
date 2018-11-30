package com.robertx22.database.stat_mods.flat;

import com.robertx22.database.stat_types.defense.Armor;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class MajorArmorFlat extends StatMod {

    public MajorArmorFlat() {
    }

    @Override
    public String GUID() {
	return "MajorArmorFlat";
    }

    @Override
    public int Min() {
	return 6;
    }

    @Override
    public int Max() {
	return 25;
    }

    @Override
    public StatTypes Type() {
	return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
	return new Armor();
    }

}
