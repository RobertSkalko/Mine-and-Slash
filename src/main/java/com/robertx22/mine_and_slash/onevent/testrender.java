package com.robertx22.mine_and_slash.onevent;

import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class testrender {

    @SubscribeEvent
    public static void tick(RenderLivingEvent evt) {

        //evt.getRenderer().addLayer((TestLayer::new);

    }

}
