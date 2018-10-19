package com.robertx22.uncommon.datasaving;

import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.datasaving.bases.Saving;

import net.minecraft.entity.Entity;

public class UnitSaving {

	private static final Class<? extends Unit> UnitClass = Unit.class;
	private static String DataLocation = "PathOfMinecraftData";

	public static <T> T Load(Entity entity) {
		return Saving.Load(entity, EntityData.Data, DataLocation, UnitClass);
	}

	public static <T> void Save(Entity entity, Unit obj) {
		Saving.Save(entity, obj, EntityData.Data, DataLocation);
	}
}
