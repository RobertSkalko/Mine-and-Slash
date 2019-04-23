package com.robertx22.onevent;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;

//@Mod.EventBusSubscriber
public class OnLivingLadderClimb {

    // this is a secret tool that will help us later

    // @SubscribeEvent
    public static void ssdsd(LivingEvent.LivingUpdateEvent event) {

	if (event.getEntityLiving() instanceof EntityPlayer && event.getEntityLiving().isOnLadder()) {
	    EntityPlayer p = (EntityPlayer) event.getEntityLiving();

	    if (p.isSneaking() == false) {
		float speed = 0.5F;
		if (p.motionY < -0.15) {
		    p.motionY = -speed;

		} else {
		    p.motionY = +speed;
		}
	    }
	}

    }

}
