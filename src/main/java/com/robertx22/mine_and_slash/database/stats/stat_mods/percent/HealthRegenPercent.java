package com.robertx22.mine_and_slash.database.stats.stat_mods.percent;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.resources.HealthRegen;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class HealthRegenPercent extends StatMod {

    public HealthRegenPercent() {
    }

    @Override
    public String GUID() {
	return "HealthRegenPercent";
    }

    @Override
    public float Min() {
	return 5;
    }

    @Override
    public float Max() {
	return 15;
    }

    @Override
    public StatTypes Type() {
	return StatTypes.Percent;
    }

    @Override
    public Stat GetBaseStat() {
	return new HealthRegen();
    }

}
