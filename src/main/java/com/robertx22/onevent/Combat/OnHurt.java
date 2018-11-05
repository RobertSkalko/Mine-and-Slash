package com.robertx22.onevent.combat;

import com.robertx22.spells.bases.MyDamageSource;

import net.minecraft.entity.player.EntityPlayer;
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

		if (event.getSource() instanceof MyDamageSource) {
			return;
		}

		if (event.getEntityLiving() instanceof EntityPlayer) {
			if (event.getSource().isExplosion()) {
				event.setAmount(event.getAmount() / 3);
				return;
			} else {
				event.setAmount(event.getAmount() / 2);
				return;
			}

		} else {
			if (event.getSource().isExplosion()) {
				event.setAmount(event.getAmount() / 5);
				return;
			} else {
				event.setAmount(event.getAmount() / 20);
			}
		}

	}
}