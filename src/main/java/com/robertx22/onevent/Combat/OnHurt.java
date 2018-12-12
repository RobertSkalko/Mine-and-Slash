package com.robertx22.onevent.combat;

import com.robertx22.spells.bases.MyDamageSource;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnHurt {

    static int decrease = 30;

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

	// mobs take much less damage from any source other than my mods. This is
	// required or else there's no point in getting legendary weapons if a diamond
	// sword more damage
	if (event.getEntityLiving() instanceof IMob || event.getEntityLiving() instanceof EntityMob) {

	    if (event.getSource().isExplosion()) {
		event.setAmount(event.getAmount() / 5);
		return;
	    } else {
		event.setAmount(event.getAmount() / decrease);
	    }

	}

	if (event.getSource() != null && event.getEntityLiving() instanceof EntityPlayer
		&& event.getSource().getTrueSource() instanceof EntityPlayer) {
	    event.setAmount(event.getAmount() / decrease);
	}

    }
}