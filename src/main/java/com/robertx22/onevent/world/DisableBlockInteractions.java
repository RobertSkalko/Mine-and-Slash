package com.robertx22.onevent.world;

import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class DisableBlockInteractions {

    @SubscribeEvent
    public static void disableBlockInteractions(BlockEvent event) {

	/*
	 * IWorldData data = Load.World(event.getWorld());
	 * 
	 * if (data != null && data.isMapWorld()) { if (event.isCancelable()) {
	 * event.setCanceled(true); } }
	 * 
	 */
    }
}
