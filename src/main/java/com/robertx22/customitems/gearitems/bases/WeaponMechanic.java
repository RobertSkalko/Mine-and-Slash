package com.robertx22.customitems.gearitems.bases;

import com.robertx22.database.stat_types.offense.PhysicalDamage;
import com.robertx22.effectdatas.DamageEffect;
import com.robertx22.effectdatas.EffectData.EffectTypes;
import com.robertx22.effectdatas.interfaces.WeaponTypes;
import com.robertx22.uncommon.capability.EntityData.UnitData;

import net.minecraft.entity.EntityLivingBase;

public abstract class WeaponMechanic {
    public abstract float GetEnergyCost();

    public abstract WeaponTypes weaponType();

    public boolean Attack(EntityLivingBase source, EntityLivingBase target, UnitData unitsource, UnitData targetUnit) {

	int num = (int) unitsource.getUnit().MyStats.get(PhysicalDamage.GUID).Value;
	DamageEffect dmg = new DamageEffect(source, target, num, unitsource, targetUnit, EffectTypes.BASIC_ATTACK,
		weaponType());

	dmg.Activate();

	return true;
    }
}
