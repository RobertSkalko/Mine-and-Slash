package com.robertx22.database.stat_types.offense.weapon_types;

import com.robertx22.database.stats.WeaponDamageStat;
import com.robertx22.uncommon.effectdatas.interfaces.WeaponTypes;

public class AxeDamage extends WeaponDamageStat {

    @Override
    public String unlocString() {
	return "axe_damage";
    }

    @Override
    public WeaponTypes weaponType() {
	return WeaponTypes.Axe;
    }

    @Override
    public String Guid() {
	return "Axe Damage";
    }

}
