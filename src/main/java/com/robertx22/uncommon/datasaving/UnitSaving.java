package com.robertx22.uncommon.datasaving;

import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.capability.EntityData;

import net.minecraft.entity.Entity;

/**
 * Save the gson to a string of capability nbt.
 * 
 * @author User
 *
 */
public class UnitSaving {

	public static Unit Load(Entity entity) {

		if (entity.hasCapability(EntityData.Data, null)) {

			return entity.getCapability(EntityData.Data, null).getUnit();

		}

		return null;
	}

	public static void Save(Entity entity, Unit obj) {
		if (obj != null && entity.hasCapability(EntityData.Data, null)) {
			entity.getCapability(EntityData.Data, null).setUnit(obj);
		}

	}
}
