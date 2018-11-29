package com.robertx22.database.stat_mods;

import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.utilityclasses.IWeighted;

public abstract class BaseTraitMod extends StatMod {

    public BaseTraitMod() {
    }

    @Override
    public int Min() {
	return 1;
    }

    @Override
    public int Max() {
	return 1;
    }

    @Override
    public StatTypes Type() {
	return StatTypes.Flat;
    }

    @Override
    public int Weight() {
	return IWeighted.CommonWeight;
    }

}