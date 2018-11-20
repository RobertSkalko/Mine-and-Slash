package com.robertx22.customitems.gearitems.bases;

import com.robertx22.uncommon.capability.EntityData.UnitData;

import net.minecraft.entity.EntityLivingBase;

public interface IWeapon extends IGearItem {
	public int GetEnergyCost();

	public boolean Attack(EntityLivingBase source, EntityLivingBase target, UnitData unitsource, UnitData targetUnit);
}
