package com.robertx22.customitems.gearitems.bases;

import com.robertx22.database.stats.types.offense.PhysicalDamage;
import com.robertx22.effectdatas.DamageEffect;
import com.robertx22.effectdatas.EffectData.EffectTypes;
import com.robertx22.uncommon.capability.EntityData.UnitData;

import net.minecraft.entity.EntityLivingBase;

public interface IWeapon extends IGearItem {
	public int GetEnergyCost();

	public boolean Attack(EntityLivingBase source, EntityLivingBase target, UnitData unitsource, UnitData targetUnit);

	public default boolean defaultAttack(EntityLivingBase source, EntityLivingBase target, UnitData unitsource,
			UnitData targetUnit) {

		int num = (int) unitsource.getUnit().MyStats.get(PhysicalDamage.GUID).Value;
		DamageEffect dmg = new DamageEffect(source, target, num, unitsource, targetUnit);
		dmg.Type = EffectTypes.BASIC_ATTACK;
		dmg.Activate();

		return true;
	}
}
