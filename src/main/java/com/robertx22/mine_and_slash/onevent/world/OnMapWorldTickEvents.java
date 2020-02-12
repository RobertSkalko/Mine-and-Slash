package com.robertx22.mine_and_slash.onevent.world;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.uncommon.capability.WorldMapCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;

import java.util.HashMap;

public class OnMapWorldTickEvents {

    static HashMap<DimensionType, Integer> ticks = new HashMap<>();

    @SubscribeEvent
    public static void onTickLogicVoid(TickEvent.WorldTickEvent event) {

        try {
            if (event.side.equals(LogicalSide.SERVER) && event.phase == TickEvent.Phase.END) {

                DimensionType type = event.world.dimension.getType();

                ticks.put(type, ticks.getOrDefault(type, 1) + 1);

                if (ticks.getOrDefault(type, 0) >= 60 * 20) {
                    ticks.put(type, 0);

                    if (WorldUtils.isMapWorldClass(event.world)) {

                        WorldMapCap.IWorldMapData map = Load.world(event.world);
                        if (map != null) {

                            map.getEvents().onMinute(event.world);

                            if (RandomUtils.roll(ModConfig.INSTANCE.Server.MAP_EVENT_CHANCE_PER_MINUTE.get())) {
                                map.startRandomMapEvent(event.world);
                            }

                        }

                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}