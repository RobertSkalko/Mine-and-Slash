package com.robertx22.mine_and_slash.database.stats.stat_mods.flat;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.defense.Dodge;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

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
