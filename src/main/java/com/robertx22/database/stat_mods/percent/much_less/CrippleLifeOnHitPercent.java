package com.robertx22.database.stat_mods.percent.much_less;

import com.robertx22.database.stat_types.resources.LifeOnHit;
import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class CrippleLifeOnHitPercent extends StatMod {

    public CrippleLifeOnHitPercent() {
    }

    @Override
    public String GUID() {
	return "CrippleLifeOnHitPercent";

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
	return new LifeOnHit();
    }

}