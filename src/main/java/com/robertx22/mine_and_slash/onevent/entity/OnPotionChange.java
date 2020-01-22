package com.robertx22.mine_and_slash.onevent.entity;

import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnPotionChange {

    @SubscribeEvent
    public static void onAdded(PotionEvent.PotionAddedEvent event) {

        LivingEntity entity = event.getEntityLiving();

        if (entity != null) {
            EntityCap.UnitData data = Load.Unit(entity);
            data.setEquipsChanged(true);
            data.recalculateStats(entity);
        }

    }
    
    @SubscribeEvent
    public static void onExpired(PotionEvent.PotionExpiryEvent event) {

        LivingEntity entity = event.getEntityLiving();

        if (entity != null) {
            EntityCap.UnitData data = Load.Unit(entity);
            data.setEquipsChanged(true);
            data.recalculateStats(entity);
        }

    }

}