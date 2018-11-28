package com.robertx22.database.stat_mods.flat.weapon_damages;

import com.robertx22.database.stat_types.offense.weapon_types.SwordDamage;
import com.robertx22.stats.Stat;

public class SwordDamageFlat extends BaseWeaponDamageFlat {

    @Override
    public Stat GetBaseStat() {
	return new SwordDamage();

    }

    @Override
    public String GUID() {
	return "SwordDamageFlat";
    }

}
