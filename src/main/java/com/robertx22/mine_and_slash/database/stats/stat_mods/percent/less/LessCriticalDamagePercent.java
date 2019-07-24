package com.robertx22.mine_and_slash.database.stats.stat_mods.percent.less;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.offense.CriticalDamage;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class LessCriticalDamagePercent extends StatMod {

    public LessCriticalDamagePercent() {
    }

    @Override
    public String GUID() {
	return "LessCriticalDamagePercent";

    }

    @Override
    public float Min() {
	return -10;
    }

    @Override
    public float Max() {
	return -20;
    }

    @Override
    public StatTypes Type() {
	return StatTypes.Percent;
    }

    @Override
    public Stat GetBaseStat() {
	return new CriticalDamage();
    }

}