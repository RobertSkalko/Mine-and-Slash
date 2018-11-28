package com.robertx22.database.stat_mods.flat.weapon_damages;

import com.robertx22.database.stat_types.offense.weapon_types.HammerDamage;
import com.robertx22.stats.Stat;

public class HammerDamageFlat extends BaseWeaponDamageFlat {

    @Override
    public Stat GetBaseStat() {
	return new HammerDamage();
    }

    @Override
    public String GUID() {
	return "HammerDamageFlat";
    }

}
