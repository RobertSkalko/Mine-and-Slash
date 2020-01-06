package com.robertx22.onevent.combatevent;

import com.robertx22.config.ModConfig;

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
				if (ModConfig.Server.SHOULD_MOBS_BURN) {
					event.getEntityLiving().extinguish();
				} else {
					
				}
			}
		}
	}

}
