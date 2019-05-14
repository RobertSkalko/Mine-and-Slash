package com.robertx22.database.stat_types.offense.weapon_types;

import com.robertx22.database.stats.WeaponDamageStat;
import com.robertx22.uncommon.effectdatas.interfaces.WeaponTypes;

public class HammerDamage extends WeaponDamageStat {

    @Override
    public String unlocString() {
	return "hammer_damage";
    }

    @Override
    public WeaponTypes weaponType() {
	return WeaponTypes.Hammer;
    }

    @Override
    public String Guid() {
	return "Hammer Damage";
    }

}
