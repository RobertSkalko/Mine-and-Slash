package com.robertx22.database.stat_types.offense.weapon_types;

import com.robertx22.effectdatas.interfaces.WeaponTypes;
import com.robertx22.stats.WeaponDamageStat;

public class BowDamage extends WeaponDamageStat {

    @Override
    public String unlocString() {
	return "bow_damage";
    }

    @Override
    public WeaponTypes weaponType() {
	return WeaponTypes.Bow;
    }

    @Override
    public String Guid() {
	return "Bow Damage";
    }

}
