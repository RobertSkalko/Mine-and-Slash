package com.robertx22.onevent.player;

import com.robertx22.uncommon.capability.EntityData;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnPlayerClone {

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {

	if (!event.getEntityPlayer().world.isRemote) {

	    EntityPlayer player = event.getEntityPlayer();
	    player.getCapability(EntityData.Data, null)
		    .HandleCloneEvent(event.getOriginal().getCapability(EntityData.Data, null));

	}

    }

}
