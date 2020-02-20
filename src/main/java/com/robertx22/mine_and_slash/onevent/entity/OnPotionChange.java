package com.robertx22.mine_and_slash.onevent.entity;

import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnPotionChange {

    //PotionAddedEvent is called BEFORE adding the potion
    @SubscribeEvent
    public static void onAdded(PotionEvent.PotionAddedEvent event) {

        LivingEntity entity = event.getEntityLiving();

        if (entity != null) {
            EntityCap.UnitData data = Load.Unit(entity);
            data.setEquipsChanged(true);
            //data.tryRecalculateStats(entity); dont calc stats, PotionAddedEvent is called BEFORE adding the potion O_O
        }

    }

    //PotionExpiryEvent is called BEFORE removing the potion
    @SubscribeEvent
    public static void onExpired(PotionEvent.PotionExpiryEvent event) {

        LivingEntity entity = event.getEntityLiving();

        if (entity != null) {
            EntityCap.UnitData data = Load.Unit(entity);
            data.setEquipsChanged(true);
            //data.tryRecalculateStats(entity);
        }

    }

}