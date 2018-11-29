package com.robertx22.database.map_mods.minus.weapon;

import com.robertx22.database.map_mods.bases.LessWeaponDamageBase;
import com.robertx22.database.stat_types.offense.weapon_types.HammerDamage;
import com.robertx22.stats.Stat;

public class LessHammerDamageMap extends LessWeaponDamageBase {

    @Override
    public String GUID() {
	return "LessHammerDamageMap";
    }

    @Override
    public Stat GetBaseStat() {
	return new HammerDamage();
    }

}