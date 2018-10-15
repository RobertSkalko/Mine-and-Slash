package com.robertx22.utilityclasses;

import com.robertx22.database.stats.types.Health;
import com.robertx22.datasaving.Saving;
import com.robertx22.saveclasses.Unit;

import net.minecraft.entity.EntityLivingBase;

public class HealthUtils {

	public static float DamageToMinecraftHealth(int dmg, EntityLivingBase entity) throws Exception {

		Unit unit = Saving.Load(entity);

		float maxhp = unit.Stats.get(Health.class).GetValue(unit);
		float maxMChp = entity.getMaxHealth();

		return (float) maxMChp / maxhp * maxMChp;

	}

}
