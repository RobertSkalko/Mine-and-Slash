package com.robertx22.mine_and_slash.onevent.player;

import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnLogout {

    // maps either are random or may get deleted either in registry or the folder to reset the zone, players can't stay in maps
    @SubscribeEvent
    public static void onLogout(PlayerEvent.PlayerLoggedOutEvent event) {

        if (event.getPlayer().world.isRemote) {
            return;
        }

        if (WorldUtils.isMapWorldClass(event.getPlayer().world)) {
            Load.playerMapData(event.getPlayer()).teleportPlayerBack(event.getPlayer());
        }

    }
}