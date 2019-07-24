package com.robertx22.mine_and_slash.database.stats.stat_mods.percent.much_less;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.offense.CriticalDamage;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class CrippleCriticalDamagePercent extends StatMod {

    public CrippleCriticalDamagePercent() {
    }

    @Override
    public String GUID() {
	return "CrippleCriticalDamagePercent";

    }

    @Override
    public float Min() {
	return -30;
    }

    @Override
    public float Max() {
	return -60;
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