package com.robertx22.database.stat_mods.flat.weapon_damages;

import com.robertx22.database.stat_types.offense.weapon_types.AxeDamage;
import com.robertx22.stats.Stat;

public class AxeDamageFlat extends BaseWeaponDamageFlat {

    @Override
    public Stat GetBaseStat() {

	return new AxeDamage();

    }

    @Override
    public String GUID() {
	return "AxeDamageFlat";
    }

}
