package com.robertx22.mine_and_slash.database.stats.stat_mods.percent.much_less;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.resources.ManaRegen;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class CrippleManaRegenPercent extends StatMod {

    public CrippleManaRegenPercent() {
    }

    @Override
    public String GUID() {
	return "CrippleManaRegenPercent";

    }

    @Override
    public float Min() {
	return -25;
    }

    @Override
    public float Max() {
	return -50;
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