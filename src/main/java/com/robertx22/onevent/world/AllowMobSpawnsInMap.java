package com.robertx22.onevent.world;

import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityBat;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class AllowMobSpawnsInMap {

	@SubscribeEvent
	public static void onMobForceSpawn(LivingSpawnEvent.CheckSpawn event) {

		IWorldData data = Load.World(event.getWorld());

		if (data.isMapWorld()) {
			EntityLivingBase en = event.getEntityLiving();

			if (en instanceof EntitySlime || en instanceof EntityBat) {

			} else {
				event.setResult(Result.ALLOW);
			}
		}

	}
}
