package com.robertx22.database.stat_mods.flat.elemental.transfers;

import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public abstract class BaseTransferFlat extends StatMod {

    @Override
    public float Min() {
	return 10;
    }

    @Override
    public float Max() {
	return 35;
    }

    @Override
    public StatTypes Type() {
	return StatTypes.Flat;
    }

}
