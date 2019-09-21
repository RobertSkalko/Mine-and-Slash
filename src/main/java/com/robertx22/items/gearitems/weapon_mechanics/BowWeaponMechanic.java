package com.robertx22.items.gearitems.weapon_mechanics;

import com.robertx22.config.ModConfig;
import com.robertx22.items.gearitems.bases.WeaponMechanic;
import com.robertx22.uncommon.effectdatas.interfaces.WeaponTypes;

public class BowWeaponMechanic extends WeaponMechanic {
	@Override
	public float GetEnergyCost() {
		return ModConfig.BasePlayerStats.bow_energy_cost;
	}

	@Override
	public WeaponTypes weaponType() {
		return WeaponTypes.Bow;
	}
}
