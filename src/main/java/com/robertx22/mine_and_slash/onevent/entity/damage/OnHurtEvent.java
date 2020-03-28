package com.robertx22.mine_and_slash.onevent.entity.damage;

import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnHurtEvent {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onHurtEvent(LivingHurtEvent event) {

        if (event.getEntity().world.isRemote) {
            return;
        }

        if (DamageEventData.isValidEntityDamage(event)) {

            LivingHurtUtils.onAttack(event);
            LivingHurtUtils.modifyDamage(event);
            LivingHurtUtils.onHurtRecordNonPlayerDmg(event);
            LivingHurtUtils.damageCurioItems(event.getEntityLiving());
            LivingHurtUtils.onBossHurt(event.getEntityLiving());
            LivingHurtUtils.stopMobInWallDamageInMaps(event);

        }
    }

    @SubscribeEvent
    public static void onPlayerAttackRecordCooldown(AttackEntityEvent event) {
        try {
            Load.Unit(event.getPlayer())
                .setAttackCooldown(event.getPlayer());
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
