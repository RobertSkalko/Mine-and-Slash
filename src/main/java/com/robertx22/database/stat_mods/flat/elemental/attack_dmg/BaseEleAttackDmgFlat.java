package com.robertx22.database.stat_mods.flat.elemental.attack_dmg;

import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public abstract class BaseEleAttackDmgFlat extends StatMod {

    public BaseEleAttackDmgFlat() {
    }

    @Override
    public float Min() {
	return 3;
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
