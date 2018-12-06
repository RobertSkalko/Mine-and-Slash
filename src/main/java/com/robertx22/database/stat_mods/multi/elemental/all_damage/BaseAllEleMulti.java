package com.robertx22.database.stat_mods.multi.elemental.all_damage;

import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public abstract class BaseAllEleMulti extends StatMod {

    @Override
    public float Min() {
	return 5;
    }

    @Override
    public float Max() {
	return 20;
    }

    @Override
    public StatTypes Type() {
	return StatTypes.Multi;
    }

}
