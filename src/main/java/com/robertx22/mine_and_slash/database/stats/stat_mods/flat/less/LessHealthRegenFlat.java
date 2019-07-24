package com.robertx22.mine_and_slash.database.stats.stat_mods.flat.less;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.resources.HealthRegen;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class LessHealthRegenFlat extends StatMod {

    public LessHealthRegenFlat() {
    }

    @Override
    public String GUID() {
	return "LessHealthRegenFlat";
    }

    @Override
    public float Min() {
	return -2;
    }

    @Override
    public float Max() {
	return -5;
    }

    @Override
    public StatTypes Type() {
	return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
	return new HealthRegen();
    }

}
