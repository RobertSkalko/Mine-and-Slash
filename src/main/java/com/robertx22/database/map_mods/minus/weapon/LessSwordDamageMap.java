package com.robertx22.database.map_mods.minus.weapon;

import com.robertx22.database.map_mods.bases.LessWeaponDamageBase;
import com.robertx22.database.stat_types.offense.weapon_types.SwordDamage;
import com.robertx22.stats.Stat;

public class LessSwordDamageMap extends LessWeaponDamageBase {

    @Override
    public String GUID() {
	return "LessSwordDamageMap";
    }

    @Override
    public Stat GetBaseStat() {
	return new SwordDamage();
    }

}