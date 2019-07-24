package com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.conversions;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public abstract class BaseResourceConversion extends StatMod {

    @Override
    public float Min() {
	return 10;
    }

    @Override
    public float Max() {
	return 30;
    }

    @Override
    public StatTypes Type() {
	return StatTypes.Flat;
    }

}
