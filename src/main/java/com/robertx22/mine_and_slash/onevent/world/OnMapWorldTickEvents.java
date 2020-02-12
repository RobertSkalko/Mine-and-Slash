package com.robertx22.mine_and_slash.onevent.world;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.uncommon.capability.WorldMapCap;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;

public class OnMapWorldTickEvents {

    static int ticks = 0;

    @SubscribeEvent
    public static void onTickLogicVoid(TickEvent.WorldTickEvent event) {

        if (event.side.equals(LogicalSide.SERVER) && event.phase == TickEvent.Phase.END) {

            ticks++;

            if (ticks >= 60 * 20) {
                ticks = 0;
                if (WorldUtils.isMapWorldClass(event.world)) {
                    if (RandomUtils.roll(ModConfig.INSTANCE.Server.MAP_EVENT_CHANCE_PER_MINUTE.get())) {
                        event.world.getCapability(WorldMapCap.Data).ifPresent(x -> x.startRandomMapEvent(event.world));
                    }

                }
            }

        }
    }
}