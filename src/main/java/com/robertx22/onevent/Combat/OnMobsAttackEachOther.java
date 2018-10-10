package com.robertx22.onevent.Combat;

import net.minecraft.entity.EntityCreature;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OnMobsAttackEachOther {

	@SubscribeEvent
	public void onMobAttackEachOther(LivingDamageEvent event) {
		if (event.getSource() == null) {
			return;
		}
		if (event.getEntityLiving().world.isRemote) {
			return;
		}
		if (!(event.getEntityLiving() instanceof EntityCreature)) {
			return;
		}
		if (!(event.getSource().getTrueSource() instanceof EntityCreature)) {
			return;
		}
		event.setAmount(0);
		event.setCanceled(true);

	}

}
