package com.robertx22.database.stat_types.offense.weapon_types;

import com.robertx22.effectdatas.interfaces.WeaponTypes;
import com.robertx22.stats.WeaponDamageStat;

public class SwordDamage extends WeaponDamageStat {

    @Override
    public String unlocString() {
	return "sword_damage";
    }

    @Override
    public WeaponTypes weaponType() {
	return WeaponTypes.Sword;
    }

    @Override
    public String Guid() {
	return "Sword Damage";
    }

}
