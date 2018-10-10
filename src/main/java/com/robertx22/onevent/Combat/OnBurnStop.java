package com.robertx22.onevent.Combat;


import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OnBurnStop {


    @SubscribeEvent
    public void onBurnStop(LivingEvent.LivingUpdateEvent event) {

        if (event.getEntityLiving() instanceof EntityZombie || event.getEntityLiving() instanceof EntitySkeleton) {

            if (event.getEntityLiving().isBurning()) {
                event.getEntityLiving().extinguish();
            }
        }
    }


}
