package com.robertx22.uncommon.datasaving;

import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.datasaving.bases.Saving;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Save the gson to a string of capability nbt. I also added a backup if it's a
 * player in case the main save goes wrong, though i'm not sure how effective it
 * is
 * 
 * @author User
 *
 */
public class UnitSaving {

	private static final Class<? extends Unit> UnitClass = Unit.class;

	public static String DataLocation = "PathOfMinecraftData";
	private static String BackupDataLocation = "PathOfMinecraftBackupData";

	public static <T> T Load(Entity entity) {

		if (entity instanceof EntityPlayer) {

			Unit unit = Saving.Load(entity, EntityData.Data, DataLocation, UnitClass);

			if (unit != null) {
				return (T) unit;
			} else {
				Unit backup = Saving.Load(entity, EntityData.Data, BackupDataLocation, UnitClass);

				return (T) backup;

			}

		} else {

			return Saving.Load(entity, EntityData.Data, DataLocation, UnitClass);
		}
	}

	public static <T> void Save(Entity entity, Unit obj) {
		Saving.Save(entity, obj, EntityData.Data, DataLocation);

		if (entity instanceof EntityPlayer && obj != null) {
			Saving.Save(entity, obj, EntityData.Data, BackupDataLocation);
		}

	}
}
