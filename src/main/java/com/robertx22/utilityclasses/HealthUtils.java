package com.robertx22.utilityclasses;

import com.robertx22.datasaving.Saving;
import com.robertx22.saveclasses.Unit;

import net.minecraft.entity.EntityLivingBase;

public class HealthUtils {

	public static float DamageToMinecraftHealth(int dmg, EntityLivingBase entity) {

		try {
			Unit unit = Saving.Load(entity);

			float maxhp = unit.Stats().get("Health").Value;
			float maxMChp = entity.getMaxHealth();

			return (float) (maxMChp / maxhp * dmg);
		} catch (Exception e) {
		}
		return 0;

	}

}
