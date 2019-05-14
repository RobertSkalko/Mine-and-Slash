package com.robertx22.database.stat_types.offense.weapon_types;

import com.robertx22.database.stats.WeaponDamageStat;
import com.robertx22.uncommon.effectdatas.interfaces.WeaponTypes;

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
