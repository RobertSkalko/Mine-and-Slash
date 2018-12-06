package com.robertx22.database.stat_mods.percent;

import com.robertx22.database.stat_types.offense.CriticalHit;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.utilityclasses.IWeighted;

public class MajorCriticalHitPercent extends StatMod {

    public MajorCriticalHitPercent() {
    }

    @Override
    public String GUID() {
	return "MajorCriticalHitPercent";

    }

    @Override
    public float Min() {
	return 10;

    }

    @Override
    public float Max() {
	return 25;
    }

    @Override
    public StatTypes Type() {
	return StatTypes.Percent;
    }

    @Override
    public Stat GetBaseStat() {
	return new CriticalHit();
    }

    @Override
    public int Weight() {
	return IWeighted.UncommonWeight;
    }

}
