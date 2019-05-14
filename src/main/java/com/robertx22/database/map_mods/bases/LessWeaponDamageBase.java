package com.robertx22.database.map_mods.bases;

import com.robertx22.database.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public abstract class LessWeaponDamageBase extends StatMod {

    @Override
    public float Min() {
	return -20;
    }

    @Override
    public float Max() {
	return -50;
    }

    @Override
    public StatTypes Type() {
	return StatTypes.Flat;
    }

}