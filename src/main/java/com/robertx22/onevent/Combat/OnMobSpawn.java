package com.robertx22.onevent.combat;

import com.robertx22.onevent.ontick.EntityUpdate;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.datasaving.UnitSaving;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnMobSpawn {

	@SubscribeEvent
	public static void onMobSpawn(EntityJoinWorldEvent event) {

		if (!(event.getEntity() instanceof EntityLivingBase)) {
			return;
		}

		EntityLivingBase entity = (EntityLivingBase) event.getEntity();

		if (entity.world.isRemote) {
			return;
		}
		if (!entity.hasCapability(EntityData.Data, null)) {
			return;
		}

		int level = GetMobLevelByDistanceFromSpawn(entity);

		Unit check = UnitSaving.Load(entity);

		if (!(entity instanceof EntityPlayer)) {
			if (check == null) {
				Unit unit = Unit.Mob(entity, level);
				unit.Save(entity);
			}

			EntityUpdate.syncEntityToClient(entity);

		} else {
			event.setCanceled(true);
		}

	}

	public static int GetMobLevelByDistanceFromSpawn(Entity entity) {

		double distance = entity.world.getSpawnPoint().distanceSq(entity.posX, entity.posY, entity.posZ);
		int lvl = (int) (1 + (distance / 12500));

		return lvl;

	}

}
