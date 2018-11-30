package com.robertx22.onevent;

import com.robertx22.mmorpg.Main;
import com.robertx22.network.EntityPackage;
import com.robertx22.uncommon.capability.EntityData;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnTrackEntity {

    @SubscribeEvent
    public static void onEntityTrack(PlayerEvent.StartTracking event) {

	Entity entity = event.getTarget();

	if (entity instanceof EntityLivingBase) {
	    if (entity.isEntityEqual(event.getEntityPlayer()) == false) {
		if (entity.hasCapability(EntityData.Data, null)) {
		    Main.Network.sendTo(new EntityPackage((EntityLivingBase) entity),
			    (EntityPlayerMP) event.getEntityPlayer());
		}

	    }
	}

    }
}
