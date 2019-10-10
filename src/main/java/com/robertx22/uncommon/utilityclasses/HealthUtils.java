package com.robertx22.uncommon.utilityclasses;

import com.robertx22.database.stat_types.resources.Health;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;

public class HealthUtils {

	public static float DamageToMinecraftHealth(float dmg, EntityLivingBase target, UnitData data) {

		try {
			Unit unit = data.getUnit();

			float maxhp = unit.MyStats.get(new Health().Guid()).Value;
			float maxMChp = target.getMaxHealth();

			return (float) (maxMChp / maxhp * dmg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	public static float vanillaHealthToActualHealth(float dmg, EntityLivingBase entity, UnitData data) {

		try {
			Unit unit = data.getUnit();

			float maxHp = unit.healthData().Value;
			float maxVanillaHp = entity.getMaxHealth();

			return (float) (dmg / maxVanillaHp * maxHp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

}
