package com.robertx22.database.stat_mods.flat.resources;

import com.robertx22.database.stat_types.resources.Lifesteal;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.utilityclasses.IWeighted;

public class LifestealFlat extends StatMod {

    public LifestealFlat() {
    }

    @Override
    public String GUID() {
	return "LifestealFlat";
    }

    @Override
    public float Min() {
	return 2;
    }

    @Override
    public float Max() {
	return 6;
    }

    @Override
    public StatTypes Type() {
	return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
	return new Lifesteal();
    }

    @Override
    public int Weight() {
	return IWeighted.UncommonWeight;
    }

}
