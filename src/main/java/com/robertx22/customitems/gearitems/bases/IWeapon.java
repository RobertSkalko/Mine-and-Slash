package com.robertx22.customitems.gearitems.bases;

import com.robertx22.saveclasses.Unit;

import net.minecraft.entity.EntityLivingBase;

public interface IWeapon extends IGearItem {
	public int GetEnergyCost();

	public boolean Attack(EntityLivingBase source, EntityLivingBase target, Unit unitsource, Unit targetUnit);
}
