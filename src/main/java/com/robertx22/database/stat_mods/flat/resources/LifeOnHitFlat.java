package com.robertx22.database.stat_mods.flat.resources;

import com.robertx22.database.stat_types.resources.LifeOnHit;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.utilityclasses.IWeighted;

public class LifeOnHitFlat extends StatMod {

    public LifeOnHitFlat() {
    }

    @Override
    public String GUID() {
	return "LifeOnHitFlat";
    }

    @Override
    public float Min() {
	return 0.75F;
    }

    @Override
    public float Max() {
	return 2.25F;
    }

    @Override
    public StatTypes Type() {
	return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
	return new LifeOnHit();
    }

    @Override
    public int Weight() {
	return IWeighted.UncommonWeight;
    }
}