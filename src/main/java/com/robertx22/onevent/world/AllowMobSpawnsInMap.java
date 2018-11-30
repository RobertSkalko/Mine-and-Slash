package com.robertx22.onevent.world;

import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.IMob;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class AllowMobSpawnsInMap {

    /**
     * allows mobs to spawn at all times, even daylight. Some mobs are blacklisted
     * like slimes or non mobs
     */
    @SubscribeEvent
    public static void onMobForceSpawn(LivingSpawnEvent.CheckSpawn event) {

	EntityLivingBase en = event.getEntityLiving();

	if (en instanceof EntityMob || en instanceof IMob) {

	    IWorldData data = Load.World(event.getWorld());

	    if (data.isMapWorld()) {

		if (en instanceof EntitySlime) {
		    // no
		} else {
		    event.setResult(Result.ALLOW);
		}
	    }

	}
    }
}
