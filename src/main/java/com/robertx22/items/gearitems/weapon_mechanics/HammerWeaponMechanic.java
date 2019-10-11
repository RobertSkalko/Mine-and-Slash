package com.robertx22.items.gearitems.weapon_mechanics;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.config.ModConfig;
import com.robertx22.database.stat_types.offense.PhysicalDamage;
import com.robertx22.items.gearitems.bases.WeaponMechanic;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.effectdatas.DamageEffect;
import com.robertx22.uncommon.effectdatas.EffectData.EffectTypes;
import com.robertx22.uncommon.effectdatas.interfaces.WeaponTypes;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;

public class HammerWeaponMechanic extends WeaponMechanic {

    @Override
    public float GetEnergyCost() {
	return ModConfig.BasePlayerStats.hammer_energy_cost;
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
