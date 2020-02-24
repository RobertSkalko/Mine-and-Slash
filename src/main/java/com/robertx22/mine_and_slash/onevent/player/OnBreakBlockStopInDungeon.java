package com.robertx22.mine_and_slash.onevent.player;

import com.robertx22.mine_and_slash.database.world_providers.IWP;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnBreakBlockStopInDungeon {

    // disable all block breaking in maps
    @SubscribeEvent
    public static void onBreak(BlockEvent.BreakEvent event) {

        if (event.getPlayer() != null &&
            event.getPlayer().world != null && event.getPlayer().world.getDimension() instanceof IWP) {

            event.setCanceled(true);

        }

    }
}
