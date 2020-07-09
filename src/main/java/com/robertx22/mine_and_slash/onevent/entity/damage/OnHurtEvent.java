package com.robertx22.mine_and_slash.onevent.entity.damage;

import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnHurtEvent {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onHurtEvent(LivingHurtEvent event) {

        if (event.getEntity().world.isRemote) {
            return;
        }

        try {

            // order matters here
            LivingHurtUtils.onAttack(event);
            LivingHurtUtils.modifyDamage(event);
            LivingHurtUtils.onHurtRecordNonPlayerDmg(event);
            // order matters here

            LivingHurtUtils.stopMobInWallDamageInMaps(event);
            LivingHurtUtils.damageCurioItems(event.getEntityLiving());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SubscribeEvent
    public static void attack(LivingAttackEvent event) {
        try {

            // all entity dmg should bypass armor and be unblockable with normal shields except mine
            // so my block stat actually has meaning
            if (event.getSource()
                .getTrueSource() instanceof LivingEntity) {
                event.getSource()
                    .setDamageBypassesArmor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
