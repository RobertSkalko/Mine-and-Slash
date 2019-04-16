package com.robertx22.customitems.gearitems.weapon_mechanics;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.customitems.gearitems.bases.WeaponMechanic;
import com.robertx22.database.stat_types.offense.PhysicalDamage;
import com.robertx22.effectdatas.DamageEffect;
import com.robertx22.effectdatas.EffectData.EffectTypes;
import com.robertx22.effectdatas.interfaces.WeaponTypes;
import com.robertx22.uncommon.capability.EntityData.UnitData;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;

public class HammerWeaponMechanic extends WeaponMechanic {

    @Override
    public float GetEnergyCost() {
	return 10;
    }

    @Override
    public WeaponTypes weaponType() {
	return WeaponTypes.Hammer;
    }

    float radius = 1.5F;

    @Override
    public boolean Attack(EntityLivingBase source, EntityLivingBase target, UnitData unitsource, UnitData targetUnit) {

	List<EntityLivingBase> entities = new ArrayList<EntityLivingBase>();

	for (Entity en : target.world.getEntitiesWithinAABBExcludingEntity(source,
		new AxisAlignedBB(target.posX - radius, target.posY - radius, target.posZ - radius,
			target.posX + radius, target.posY + radius, target.posZ + radius))) {
	    if (en instanceof EntityLivingBase) {
		entities.add((EntityLivingBase) en);
	    }
	}

	for (EntityLivingBase entity : entities) {
	    int num = (int) unitsource.getUnit().MyStats.get(PhysicalDamage.GUID).Value;
	    DamageEffect dmg = new DamageEffect(source, entity, num, unitsource, targetUnit, EffectTypes.BASIC_ATTACK,
		    WeaponTypes.Hammer);
	    dmg.Activate();
	}

	return true;
    }

}
