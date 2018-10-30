package com.robertx22.onevent.combat;

import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnHealDecrease {

	public static float HEAL_DECREASE = 10F;

	@SubscribeEvent
	public static void OnEntityHealDecrease(LivingHealEvent event) {
		event.setAmount(event.getAmount() / HEAL_DECREASE);
	}
}