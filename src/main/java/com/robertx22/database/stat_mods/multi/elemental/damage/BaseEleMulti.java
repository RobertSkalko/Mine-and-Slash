package com.robertx22.database.stat_mods.multi.elemental.damage;

import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public abstract class BaseEleMulti extends StatMod {

    @Override
    public int Min() {
	return 5;
    }

    @Override
    public int Max() {
	return 15;
    }

    @Override
    public StatTypes Type() {
	return StatTypes.Multi;
    }

}
