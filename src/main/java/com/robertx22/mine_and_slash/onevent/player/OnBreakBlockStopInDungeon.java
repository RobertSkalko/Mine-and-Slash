package com.robertx22.mine_and_slash.onevent.player;

import com.robertx22.mine_and_slash.database.world_providers.IWP;
import com.robertx22.mine_and_slash.new_content.chests.MapChestBlock;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnBreakBlockStopInDungeon {

    // disable all block breaking in maps, except stuff like my chests
    @SubscribeEvent
    public static void onBreak(BlockEvent.BreakEvent event) {

        try {
            if (event.getPlayer() != null &&
                event.getPlayer().world != null && event.getPlayer().world.getDimension() instanceof IWP) {

                if (event.getState()
                    .getBlock() instanceof MapChestBlock) {
                    return;
                }

                event.setCanceled(true);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
