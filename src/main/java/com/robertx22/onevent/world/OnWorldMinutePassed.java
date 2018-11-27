package com.robertx22.onevent.world;

import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;

import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

@Mod.EventBusSubscriber
public class OnWorldMinutePassed {

	static final int oneMinute = 1200;

	static int ticks = 0;

	@SubscribeEvent
	public static void onMinutePassedUpdateWorldTime(TickEvent.ServerTickEvent event) {

		if (event.phase == Phase.END && event.side.isServer()) {

			ticks++;

			if (ticks > oneMinute) {

				ticks = 0;

				for (WorldServer world : FMLCommonHandler.instance().getMinecraftServerInstance().worlds) {

					IWorldData data = Load.World(world);

					if (data != null) {
						data.onMinutePassed(world);
					}
				}
			}
		}

	}

}
