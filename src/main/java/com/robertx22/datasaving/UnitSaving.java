package com.robertx22.datasaving;

import com.robertx22.capability.EntityData;
import com.robertx22.datasaving.bases.Saving;
import com.robertx22.saveclasses.Unit;

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
