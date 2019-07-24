package com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.resources.ManaRegen;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class MajorManaRegenFlat extends StatMod {

    public MajorManaRegenFlat() {
    }

    @Override
    public String GUID() {
	return "MajorManaRegenFlat";
    }

    @Override
    public float Min() {
	return 3;
    }

    @Override
    public float Max() {
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
