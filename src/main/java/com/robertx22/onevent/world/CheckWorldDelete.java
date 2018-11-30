package com.robertx22.onevent.world;

import java.io.IOException;
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

    @SubscribeEvent
    public static void onWorldUnload(WorldEvent.Unload event) {

	Runnable noteThread = new Runnable() {
	    @Override
	    public void run() {
		try {

		    if (!event.getWorld().isRemote) {
			deleteIfSet(event.getWorld());
		    }

		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }

	};
	ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
	scheduler.schedule(noteThread, 3, TimeUnit.SECONDS);

    }

    private static void deleteIfSet(World world) {

	if (world.hasCapability(WorldData.Data, null)) {
	    IWorldData data = world.getCapability(WorldData.Data, null);

	    if (data != null && data.isSetForDelete()) {

		MapDatas mapdata = (MapDatas) world.getMapStorage().getOrLoadData(MapDatas.class, MapDatas.getLoc());

		if (mapdata != null) {

		    mapdata.delete(world.provider.getDimension());

		    try {
			FileUtils.deleteDirectory(WorldFileUtils.getWorldDirectory(world));
			System.out.println("Deleting a temporary map world to free up disk space!");

		    } catch (IOException e) {
			e.printStackTrace();
		    }

		}
	    }

	}
    }

}
