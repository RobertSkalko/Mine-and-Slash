package com.robertx22.customitems.gearitems.weapon_mechanics;

import com.robertx22.customitems.gearitems.bases.WeaponMechanic;
import com.robertx22.effectdatas.interfaces.WeaponTypes;

public class BowWeaponMechanic extends WeaponMechanic {
    @Override
    public float GetEnergyCost() {
	return 9;
    }

    @Override
    public WeaponTypes weaponType() {
	return WeaponTypes.Bow;
    }
}
