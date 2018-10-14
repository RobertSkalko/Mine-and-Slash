package com.robertx22.utilityclasses;

import com.robertx22.database.stats.types.Health;
import com.robertx22.saveclasses.Unit;
import com.robertx22.saving.Saving;

import net.minecraft.entity.EntityLivingBase;

public class HealthUtils {

	public static float DamageToMinecraftHealth(int dmg, EntityLivingBase entity) {

		Unit unit = Saving.Load(entity, Unit.class);

		float maxhp = unit.Stats.get(Health.class).GetValue();
		float maxMChp = entity.getMaxHealth();

		return (float) maxMChp / maxhp * maxMChp;

	}

}
