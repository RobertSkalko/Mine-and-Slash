package com.robertx22.database.stat_mods.flat.elemental.resist;

import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public abstract class BaseEleResistFlat extends StatMod {

    public BaseEleResistFlat() {
    }

    @Override
    public float Min() {
	return 4;
    }

    @Override
    public float Max() {
	return 12;
    }

    @Override
    public StatTypes Type() {
	return StatTypes.Flat;
    }

}
