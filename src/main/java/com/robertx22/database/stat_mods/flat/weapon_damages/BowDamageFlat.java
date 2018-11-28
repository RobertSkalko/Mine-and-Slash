package com.robertx22.database.stat_mods.flat.weapon_damages;

import com.robertx22.database.stat_types.offense.weapon_types.BowDamage;
import com.robertx22.stats.Stat;

public class BowDamageFlat extends BaseWeaponDamageFlat {

    @Override
    public Stat GetBaseStat() {
	return new BowDamage();
    }

    @Override
    public String GUID() {
	return "BowDamageFlat";
    }

}
