package com.robertx22.utilityclasses;

import com.robertx22.database.stats.types.Health;
import com.robertx22.datasaving.UnitSaving;
import com.robertx22.saveclasses.Unit;

import net.minecraft.entity.EntityLivingBase;

public class HealthUtils {

	public static float DamageToMinecraftHealth(int dmg, EntityLivingBase entity) {

		try {
			Unit unit = UnitSaving.Load(entity);

			float maxhp = unit.Stats.get(new Health().Name()).Value;
			float maxMChp = entity.getMaxHealth();

			return (float) (maxMChp / maxhp * dmg);
		} catch (Exception e) {
		}
		return 0;

	}

}