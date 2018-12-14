package com.robertx22.onevent.combat;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnHealDecrease {

    public static float HEAL_DECREASE = 10F;

    @SubscribeEvent
    public static void OnEntityHealDecrease(LivingHealEvent event) {

	if (event.getEntityLiving() instanceof IMob || event.getEntityLiving() instanceof EntityMob
		|| event.getEntityLiving() instanceof EntityPlayer) {

	    event.setAmount(event.getAmount() / HEAL_DECREASE);
	}
    }
}