package com.robertx22.customitems.gearitems.weapon_mechanics;

import com.robertx22.customitems.gearitems.bases.WeaponMechanic;
import com.robertx22.effectdatas.interfaces.WeaponTypes;
import com.robertx22.uncommon.capability.EntityData.UnitData;

import net.minecraft.entity.EntityLivingBase;

public class StaffWeaponMechanic extends WeaponMechanic {

    @Override
    public float GetEnergyCost() {
	return 9;
    }

    @Override
    public WeaponTypes weaponType() {
	return WeaponTypes.Staff;
    }

    @Override
    public boolean Attack(EntityLivingBase source, EntityLivingBase target, UnitData unitsource, UnitData targetUnit) {

	// double attack :3
	super.Attack(source, target, unitsource, targetUnit);
	super.Attack(source, target, unitsource, targetUnit);

	return true;
    }
}
