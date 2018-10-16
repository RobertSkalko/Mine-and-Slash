package com.robertx22.onevent.combat;

import net.minecraft.entity.monster.EntityMob;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnBurnStop {

	@SubscribeEvent
	public static void onBurnStop(LivingEvent.LivingUpdateEvent event) {

		if (event.getEntityLiving() instanceof EntityMob) {

			if (event.getEntityLiving().isBurning()) {
				event.getEntityLiving().extinguish();
			}
		}
	}

}
