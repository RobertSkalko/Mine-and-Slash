package com.robertx22.onevent.combat;

import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnMobsAttackEachOther {

	@SubscribeEvent
	public void onMobAttackEachOther(LivingDamageEvent event) {
		/*
		 * if (event.getSource() == null) { return; } if
		 * (event.getEntityLiving().world.isRemote) { return; } if
		 * (!(event.getEntityLiving() instanceof EntityCreature)) { return; } if
		 * (!(event.getSource().getTrueSource() instanceof EntityCreature)) { return; }
		 * event.setAmount(0); event.setCanceled(true);
		 */

	}

}
