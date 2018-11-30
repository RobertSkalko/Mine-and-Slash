package com.robertx22.database.stat_mods.flat.resources;

import com.robertx22.database.stat_types.resources.ManaRegen;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class MajorManaRegenFlat extends StatMod {

    public MajorManaRegenFlat() {
    }

    @Override
    public String GUID() {
	return "MajorManaRegenFlat";
    }

    @Override
    public int Min() {
	return 3;
    }

    @Override
    public int Max() {
	return 6;
    }

    @Override
    public StatTypes Type() {
	return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
	return new ManaRegen();
    }

}
