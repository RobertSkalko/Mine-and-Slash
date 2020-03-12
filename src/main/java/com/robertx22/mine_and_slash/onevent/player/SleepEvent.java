package com.robertx22.mine_and_slash.onevent.player;

import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerMapCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SleepEvent {

    @SubscribeEvent
    public static void onSleep(PlayerWakeUpEvent event) {

        try {
            if (!event.getPlayer().world.isRemote) {
                PlayerMapCap.IPlayerMapData data = Load.playerMapData(event.getPlayer());

                if (data.isRefreshedForMap()) {
                    event.getPlayer()
                        .sendMessage(new SText(TextFormatting.GREEN + "You feel refreshed and ready for an adventure!"));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
