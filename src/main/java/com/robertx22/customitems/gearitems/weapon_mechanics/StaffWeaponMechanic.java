package com.robertx22.customitems.gearitems.weapon_mechanics;

import com.robertx22.customitems.gearitems.bases.WeaponMechanic;
import com.robertx22.effectdatas.interfaces.WeaponTypes;

public class StaffWeaponMechanic extends WeaponMechanic {

    @Override
    public int GetEnergyCost() {
	return 6;
    }

    @Override
    public WeaponTypes weaponType() {
	return WeaponTypes.Staff;
    }
}
