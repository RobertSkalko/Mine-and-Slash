package com.robertx22.mine_and_slash.database.stats.stat_mods.percent.less;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.resources.ManaRegen;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class LessManaRegenPercent extends StatMod {

    public LessManaRegenPercent() {
    }

    @Override
    public String GUID() {
	return "LessManaRegenPercent";

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
	return new ManaRegen();
    }

}