package com.robertx22.datasaving;

import com.robertx22.capability.DamageSourceData;
import com.robertx22.datasaving.bases.Saving;
import com.robertx22.saveclasses.Unit;

import net.minecraft.entity.Entity;

public class DamageSaving {

	private static final Class<? extends Unit> TheClass = Unit.class;
	private static String DataLocation = "DamageData";

	public static <T> T Load(Entity entity) {
		return Saving.Load(entity, DamageSourceData.Data, DataLocation, TheClass);
	}

	public static <T> void Save(Entity entity, Object obj) {
		Saving.Save(entity, obj, DamageSourceData.Data, DataLocation);
	}
}
