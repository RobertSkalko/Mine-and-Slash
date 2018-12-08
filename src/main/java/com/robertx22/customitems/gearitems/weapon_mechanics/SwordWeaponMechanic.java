package com.robertx22.customitems.gearitems.weapon_mechanics;

import com.robertx22.customitems.gearitems.bases.WeaponMechanic;
import com.robertx22.effectdatas.interfaces.WeaponTypes;

public class SwordWeaponMechanic extends WeaponMechanic {

    @Override
    public float GetEnergyCost() {
	return 4;
    }

    @Override
    public WeaponTypes weaponType() {
	return WeaponTypes.Sword;
    }

}
