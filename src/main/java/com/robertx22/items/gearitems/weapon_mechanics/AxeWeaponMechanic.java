package com.robertx22.items.gearitems.weapon_mechanics;

import com.robertx22.config.ModConfig;
import com.robertx22.items.gearitems.bases.WeaponMechanic;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.effectdatas.interfaces.WeaponTypes;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class AxeWeaponMechanic extends WeaponMechanic {

    @Override
    public float GetEnergyCost() {
	return ModConfig.BasePlayerStats.axe_energy_cost;
    }

    @Override
    public WeaponTypes weaponType() {
	return WeaponTypes.Axe;
    }

    @Override
    public boolean Attack(LivingHurtEvent event,EntityLivingBase source, EntityLivingBase target, UnitData unitsource, UnitData targetUnit) {

	// double attack :3
	super.Attack(event, source, target, unitsource, targetUnit);
	super.Attack(event, source, target, unitsource, targetUnit);

	return true;
    }

}
