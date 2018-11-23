package com.robertx22.onevent.world;

import com.robertx22.uncommon.capability.MapDatas;

import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnLoadWorld {
	static boolean registeredDims = false;

	@SubscribeEvent
	public static void onWorldLoad(WorldEvent.Load event) {

		if (registeredDims == false) {
			MapDatas data = (MapDatas) event.getWorld().getMapStorage().getOrLoadData(MapDatas.class,
					MapDatas.LOCATION);

			if (data == null) {
				event.getWorld().getMapStorage().setData(MapDatas.LOCATION, new MapDatas(MapDatas.LOCATION));
			}
			if (data != null) {
				data.registerDimensions();
				registeredDims = true;
			}
		}

	}
}
