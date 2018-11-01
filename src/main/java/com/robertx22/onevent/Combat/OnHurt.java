package com.robertx22.onevent.combat;

import com.robertx22.spells.bases.MyDamageSource;

import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnHurt {

	/**
	 * If damage is from my source, leave the value, otherwise decrease it (this
	 * makes my damage source the best one)
	 * 
	 * @param event
	 */
	@SubscribeEvent
	public static void OnLivingHurt(LivingHurtEvent event) {
		if (event.getEntityLiving().world.isRemote) {
			return;
		}
		if (event.getSource() instanceof MyDamageSource) {
			// System.out.println("hurt works correctly!");
			return;
		} else if (event.getSource().isExplosion()) {
			event.setAmount(event.getAmount() / 5);
			return;
		} else {
			event.setAmount(event.getAmount() / 20);
		}
	}
}