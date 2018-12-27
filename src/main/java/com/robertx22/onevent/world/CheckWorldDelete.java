package com.robertx22.onevent.world;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

import com.robertx22.dimensions.WorldFileUtils;
import com.robertx22.uncommon.capability.MapDatas;
import com.robertx22.uncommon.capability.WorldData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;

import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CheckWorldDelete {

    public static HashMap<Integer, Integer> idAndDeleteAttempts = new HashMap<Integer, Integer>();

    @SubscribeEvent
    public static void onWorldUnload(WorldEvent.Unload event) {

	if (!event.getWorld().isRemote) {

	    try {
		resetCount(event.getWorld().provider.getDimension());
	    } catch (Exception e1) {
		e1.printStackTrace();
	    }

	    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

	    Runnable noteThread = new Runnable() {
		@Override
		public void run() {

		    int id = 0;

		    try {

			World world = event.getWorld();

			id = world.provider.getDimension();

			if (world.hasCapability(WorldData.Data, null)) {
			    IWorldData data = world.getCapability(WorldData.Data, null);

			    if (data != null && data.isSetForDelete()) {

				MapDatas mapdata = (MapDatas) world.getMapStorage().getOrLoadData(MapDatas.class,
					MapDatas.getLoc());

				if (mapdata != null) {

				    mapdata.delete(world.provider.getDimension());

				    try {
					increaseCount(id);

					FileUtils.deleteDirectory(WorldFileUtils.getWorldDirectory(world));
					System.out.println("Deleting a temporary map world to free up disk space!");

				    } catch (IOException e) {
					// e.printStackTrace();
				    }
				}

			    }

			}
			scheduler.shutdown();

		    } catch (Exception e) {
			// keep scheduling until it's correctly deleted

			if (hasMoreTries(id)) {
			    scheduler.schedule(this, 3, TimeUnit.SECONDS);
			}
		    }
		}

	    };
	    scheduler.schedule(noteThread, 3, TimeUnit.SECONDS);
	}
    }

    private static boolean hasMoreTries(int id) {
	return idAndDeleteAttempts.containsKey(id) == false || idAndDeleteAttempts.get(id) < 5;
    }

    private static void resetCount(int id) {
	if (idAndDeleteAttempts.containsKey(id)) {
	    idAndDeleteAttempts.put(id, 0);
	}
    }

    private static void increaseCount(int id) {

	if (idAndDeleteAttempts.containsKey(id)) {
	    idAndDeleteAttempts.put(id, idAndDeleteAttempts.get(id) + 1);
	} else {
	    idAndDeleteAttempts.put(id, 1);
	}
    }

}
