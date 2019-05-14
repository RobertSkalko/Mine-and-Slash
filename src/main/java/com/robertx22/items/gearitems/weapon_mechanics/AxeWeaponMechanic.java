package com.robertx22.items.gearitems.weapon_mechanics;

import com.robertx22.items.gearitems.bases.WeaponMechanic;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.effectdatas.interfaces.WeaponTypes;

import net.minecraft.entity.EntityLivingBase;

public class AxeWeaponMechanic extends WeaponMechanic {

    @Override
    public float GetEnergyCost() {
	return 8.5F;
    }

    @Override
    public WeaponTypes weaponType() {
	return WeaponTypes.Axe;
    }

    @Override
    public boolean Attack(EntityLivingBase source, EntityLivingBase target, UnitData unitsource, UnitData targetUnit) {

	// double attack :3
	super.Attack(source, target, unitsource, targetUnit);
	super.Attack(source, target, unitsource, targetUnit);

	return true;
    }

}
