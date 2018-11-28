package com.robertx22.database.stat_mods.flat.weapon_damages;

import com.robertx22.database.stat_types.offense.weapon_types.StaffDamage;
import com.robertx22.stats.Stat;

public class StaffDamageFlat extends BaseWeaponDamageFlat {

    @Override
    public Stat GetBaseStat() {
	return new StaffDamage();

    }

    @Override
    public String GUID() {
	return "StaffDamageFlat";
    }

}
