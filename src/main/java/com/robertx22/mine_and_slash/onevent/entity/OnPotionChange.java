package com.robertx22.mine_and_slash.onevent.entity;

import com.robertx22.mine_and_slash.potion_effects.bases.IOneOfATypePotion;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;
import java.util.stream.Collectors;

public class OnPotionChange {

    //PotionAddedEvent is called BEFORE adding the potion
    @SubscribeEvent
    public static void onAdded(PotionEvent.PotionAddedEvent event) {

        if (event.getEntityLiving().world.isRemote) {
            return;
        }

        try {
            LivingEntity entity = event.getEntityLiving();

            if (event.getPotionEffect()
                .getPotion() instanceof IOneOfATypePotion) {
                IOneOfATypePotion one = (IOneOfATypePotion) event.getPotionEffect()
                    .getPotion();

                List<Effect> sames = entity.getActivePotionEffects()
                    .stream()
                    .filter(x -> {
                        if (x.getPotion() instanceof IOneOfATypePotion) {
                            IOneOfATypePotion ot = (IOneOfATypePotion) x.getPotion();

                            if (x.equals(event.getPotionEffect())) {
                                return false;
                            }

                            if (ot.getOneOfATypeType()
                                .equals(one.getOneOfATypeType())) {
                                return true;
                            }
                        }

                        return false;
                    })
                    .map(x -> x.getPotion())
                    .collect(Collectors.toList());

                sames.forEach(x -> entity.removePotionEffect(x));
            }

            if (entity != null) {
                EntityCap.UnitData data = Load.Unit(entity);
                data.setEquipsChanged(true);
                //data.tryRecalculateStats(entity); dont calc stats, PotionAddedEvent is called BEFORE adding the potion O_O
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //PotionExpiryEvent is called BEFORE removing the potion
    @SubscribeEvent
    public static void onExpired(PotionEvent.PotionExpiryEvent event) {

        LivingEntity entity = event.getEntityLiving();

        if (entity != null && !entity.world.isRemote) {
            EntityCap.UnitData data = Load.Unit(entity);
            data.setEquipsChanged(true);
            //data.tryRecalculateStats(entity);
        }

    }

}