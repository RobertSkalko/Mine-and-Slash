package com.robertx22.database.stat_mods.flat.weapon_damages;

import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public abstract class BaseWeaponDamageFlat extends StatMod {

    @Override
    public float Min() {
	return 10;
    }

    @Override
    public float Max() {
	return 25;
    }

    @Override
    public StatTypes Type() {
	return StatTypes.Flat;
    }

}
