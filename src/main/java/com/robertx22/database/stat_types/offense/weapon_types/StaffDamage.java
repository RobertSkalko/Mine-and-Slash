package com.robertx22.database.stat_types.offense.weapon_types;

import com.robertx22.effectdatas.interfaces.WeaponTypes;
import com.robertx22.stats.WeaponDamageStat;

public class StaffDamage extends WeaponDamageStat {

    @Override
    public String unlocString() {
	return "staff_damage";
    }

    @Override
    public WeaponTypes weaponType() {
	return WeaponTypes.Staff;
    }

    @Override
    public String Guid() {
	return "Staff Damage";
    }

}
